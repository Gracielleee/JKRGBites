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

    private val _sessionSwipedRestaurants = MutableStateFlow<Set<String>>(emptySet())

    private val _deck = MutableStateFlow<List<Restaurant>>(emptyList())
    val deck: StateFlow<List<Restaurant>> = _deck.asStateFlow()

    private val _favorites = MutableStateFlow<Set<String>>(emptySet())
    val favorites: StateFlow<Set<String>> = _favorites.asStateFlow()

    private val _neverAgain = MutableStateFlow<Set<String>>(emptySet())
    val neverAgain: StateFlow<Set<String>> = _neverAgain.asStateFlow()
    private val _selectedRestaurant = MutableStateFlow<Restaurant?>(null)
    val selectedRestaurant: StateFlow<Restaurant?> = _selectedRestaurant.asStateFlow()

    // init block to perform initial setup that doesn't require scope
    init {
        // We will call resetDeck() once scope is initialized
    }

    fun init(scope: CoroutineScope) {
        this.scope = scope
        scope.launch {
            restaurantRepository.getRestaurants().collect { restaurants ->
                _allRestaurants.value = restaurants
                updateDeck()
            }
        }
    }


    fun updateDeck() {
        _deck.update {
            filterDeck()
        }
    }

    /**
     * Filters the deck based on the current session's swipes and never again status.
     */
    fun filterDeck(): List<Restaurant> {
        return _allRestaurants.value.filterNot { restaurant ->
            restaurant.isNeverAgain || _sessionSwipedRestaurants.value.contains(restaurant.id)
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

        // Immediately update deck in memory for UI responsiveness
        _deck.update { currentDeck -> currentDeck.filterNot { it.id == restaurant.id } }

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
            // Also remove from deck if present
            _deck.update { currentDeck -> currentDeck.filterNot { d -> d.id == restaurantId } }
        }
    }

    /**
     * Method to clear session-level swipes (when user wants to reset the deck during the session)
     */
    fun clearSessionSwipes() {
        _sessionSwipedRestaurants.value = emptySet()
        updateDeck()
    }

    fun shuffleDeck() {
        val filteredRestaurants = _deck.value.size

        val originalDeckSize = _allRestaurants.value

        _deck.update {
            filterDeck().shuffled()

            Log.d("SwipeManager", """
            Deck Shuffled:
            - Original Deck Size: ${originalDeckSize.size}
            - Filtered Restaurants: $filteredRestaurants
            - Swiped Restaurant IDs: ${_sessionSwipedRestaurants.value}
        """.trimIndent())

            originalDeckSize
        }
    }


    /**
     * Clears the selected restaurant.
     */
    fun clearSelectedRestaurant() {
        _selectedRestaurant.update { null }
    }
}
