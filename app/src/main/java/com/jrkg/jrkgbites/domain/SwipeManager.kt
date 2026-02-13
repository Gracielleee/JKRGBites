package com.jrkg.jrkgbites.domain

import android.util.Log
import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.model.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Defines the possible directions for a swipe action.
 */
enum class SwipeDirection {
    UP, DOWN, LEFT, RIGHT
}

/**
 * Manages the state and logic for the swipeable "Tinder-style" card picker.
 */
class SwipeManager(
    private val restaurantRepository: RestaurantRepository
) {
    private lateinit var scope: CoroutineScope

    private val _allRestaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val allRestaurants: StateFlow<List<Restaurant>> = _allRestaurants.asStateFlow()

    private val _sessionSwipedRestaurants = MutableStateFlow<Set<String>>(emptySet())

    private val _swipeHistory = mutableListOf<Pair<Restaurant, SwipeDirection>>()
    private val MAX_SWIPE_HISTORY_SIZE = 8
    private val _deck = MutableStateFlow<List<Restaurant>>(emptyList())
    val deck: StateFlow<List<Restaurant>> = _deck.asStateFlow()

    private val _favorites = MutableStateFlow<Set<String>>(emptySet())
    val favorites: StateFlow<Set<String>> = _favorites.asStateFlow()

    private val _neverAgain = MutableStateFlow<Set<String>>(emptySet())
    val neverAgain: StateFlow<Set<String>> = _neverAgain.asStateFlow()

    private val _selectedRestaurant = MutableStateFlow<Restaurant?>(null)
    val selectedRestaurant: StateFlow<Restaurant?> = _selectedRestaurant.asStateFlow()

    fun init(scope: CoroutineScope) {
        this.scope = scope
        scope.launch {
            restaurantRepository.getRestaurants().collect { restaurants ->
                _allRestaurants.value = restaurants
                // Update helper state flows for favorites and never again
                _favorites.value = restaurants.filter { it.isFavorite }.map { it.id }.toSet()
                _neverAgain.value = restaurants.filter { it.isNeverAgain }.map { it.id }.toSet()
                updateDeck()
            }
        }
    }

    /**
     * Updates the deck by applying current filters.
     */
    fun updateDeck() {
        _deck.update {
            filterDeck()
        }
    }

    /**
     * Filters the deck based on flags and the current session's swipes.
     */
    fun filterDeck(): List<Restaurant> {
        return _allRestaurants.value.filterNot { restaurant ->
            restaurant.isNeverAgain || 
            restaurant.isFavorite || 
            _sessionSwipedRestaurants.value.contains(restaurant.id)
        }
    }

    /**
     * Processes a swipe action on a restaurant.
     * @param restaurant The restaurant that was swiped.
     * @param direction The direction of the swipe.
     */
    fun onSwipe(restaurant: Restaurant, direction: SwipeDirection) {
        // Track swiped restaurants in session
        _sessionSwipedRestaurants.update { it + restaurant.id }
        _deck.update { currentDeck -> currentDeck.filterNot { it.id == restaurant.id } }

        //To track for undo
        if (_swipeHistory.size >= MAX_SWIPE_HISTORY_SIZE) {
            _swipeHistory.removeAt(0) // Remove the oldest entry
        }
        _swipeHistory.add(Pair(restaurant, direction))

        scope.launch {
            when (direction) {
                SwipeDirection.UP -> { // Add to Favorites
                    restaurant.isFavorite = true
                    restaurantRepository.updateRestaurantStatus(restaurant)
                }
                SwipeDirection.DOWN -> { // Add to "Never Again"
                    restaurant.isNeverAgain = true
                    restaurantRepository.updateRestaurantStatus(restaurant)
                }
                SwipeDirection.LEFT -> {
                    // Just removed from deck session
                }
                SwipeDirection.RIGHT -> {
                    _selectedRestaurant.update { restaurant }
                }
            }
        }
    }

    /**
     * Adds a restaurant to the "Never Again" list by its ID.
     */
    suspend fun addToNeverAgain(restaurantId: String) {
        val restaurant = restaurantRepository.getRestaurantById(restaurantId).first()
        restaurant?.let {
            it.isNeverAgain = true
            restaurantRepository.updateRestaurantStatus(it)
            // Also ensure it is tracked in the session so it doesn't reappear in the deck
            _sessionSwipedRestaurants.update { swiped -> swiped + restaurantId }
            _deck.update { currentDeck -> currentDeck.filterNot { d -> d.id == restaurantId } }
        }
    }

    /**
     * Method to clear session-level swipes (when user wants to reset the deck during the session)
     */
    fun clearSessionSwipes() {
        _sessionSwipedRestaurants.value = emptySet()
        updateDeck()
        clearSwipeHistory()
    }

    fun undoLastSwipe() {
        if (_swipeHistory.isNotEmpty()) {
            val (lastRestaurant, lastDirection) = _swipeHistory.removeAt(_swipeHistory.lastIndex)
            scope.launch {
                when (lastDirection) {
                    SwipeDirection.UP -> {
                        lastRestaurant.isFavorite = false
                        restaurantRepository.updateRestaurantStatus(lastRestaurant)
                    }
                    SwipeDirection.DOWN -> {
                        lastRestaurant.isNeverAgain = false
                        restaurantRepository.updateRestaurantStatus(lastRestaurant)
                    }
                    SwipeDirection.LEFT, SwipeDirection.RIGHT -> {
                        // No action
                    }
                }
                // Restore the restaurant to the deck and session
                _sessionSwipedRestaurants.update { it - lastRestaurant.id }
                _deck.update { currentDeck -> currentDeck + lastRestaurant }
                updateDeck() // Refresh the deck after modifications
            }
        } else {
            Log.d("SwipeManager", "No swipes to undo.")
        }
    }

    /**
     * Shuffles the current filtered deck.
     */
    fun shuffleDeck() {
        _deck.update {
            val shuffled = filterDeck().shuffled()
            shuffled
        }
    }

    /**
     * Clears the selected restaurant.
     */
    fun clearSelectedRestaurant() {
        _selectedRestaurant.update { null }
    }

    fun clearSwipeHistory() {
        _swipeHistory.clear()
    }
}
