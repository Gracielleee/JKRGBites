package com.jrkg.jrkgbites.domain

import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.model.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private lateinit var scope: CoroutineScope // ADDED: Will be initialized by init()

    private val _allRestaurants = MutableStateFlow<List<Restaurant>>(emptyList())

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

    // New initialization method to pass the CoroutineScope
    fun init(scope: CoroutineScope) {
        this.scope = scope
        scope.launch {
            restaurantRepository.getRestaurants().collect { restaurants ->
                _allRestaurants.value = restaurants
                resetDeck() // Reset deck whenever allRestaurants updates
            }
        }
        resetDeck() // Initial call to reset deck
    }

    /**
     * Processes a swipe action on a restaurant.
     * @param restaurant The restaurant that was swiped.
     * @param direction The direction of the swipe.
     */
    fun onSwipe(restaurant: Restaurant, direction: SwipeDirection) {
        _deck.update { currentDeck -> currentDeck.filterNot { it.id == restaurant.id } }

        when (direction) {
            SwipeDirection.UP -> { // Add to Favorites
                _favorites.update { it + restaurant.id }
            }
            SwipeDirection.DOWN -> { // Add to "Never Again"
                addToNeverAgain(restaurant.id)
            }
            SwipeDirection.LEFT -> { // Temporarily discard for this session
                // The restaurant is just removed from the deck.
            }
            SwipeDirection.RIGHT -> { // Select the restaurant
                _selectedRestaurant.update { restaurant }
            }
        }
    }

    /**
     * Adds a restaurant to the "Never Again" list.
     * This can be called from a swipe-down action or after a low rating.
     */
    fun addToNeverAgain(restaurantId: String) {
        _neverAgain.update { it + restaurantId }
    }

    /**
     * Resets the deck to its initial state, filtering out any restaurants in the "Never Again" list.
     * This can be called on app start or when the user wants to restart their session.
     */
    fun resetDeck() {
        _deck.update {
            _allRestaurants.value.filterNot { restaurant ->
                _neverAgain.value.contains(restaurant.id)
            }
        }
    }

    /**
     * Allows a restaurant to be removed from the "Never Again" list.
     * This would be used if the user re-adds a restaurant from the search page.
     */
    fun removeFromNeverAgain(restaurantId: String) {
        _neverAgain.update { it - restaurantId }
    }

    /**
     * Clears the currently selected restaurant.
     * This would be called after a selected restaurant has been navigated to.
     */
    fun clearSelectedRestaurant() {
        _selectedRestaurant.update { null }
    }
}