package com.jrkg.jrkgbites.domain

import android.util.Log
import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.model.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
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
    private val restaurantRepository: RestaurantRepository,

    private val restaurantManager: RestaurantManager
) {
    private lateinit var scope: CoroutineScope

    private val _allRestaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val allRestaurants: StateFlow<List<Restaurant>> = _allRestaurants.asStateFlow()

    private val _displayOrder = MutableStateFlow<List<Restaurant>>(emptyList())
    private val _sessionSwipedRestaurants = MutableStateFlow<Set<String>>(emptySet())
    private val _swipeHistory = mutableListOf<Pair<Restaurant, SwipeDirection>>()
    private val MAX_SWIPE_HISTORY_SIZE = 8

    private val _selectedRestaurant = MutableStateFlow<Restaurant?>(null)
    val selectedRestaurant: StateFlow<Restaurant?> = _selectedRestaurant.asStateFlow()

    // Reactive lists from Repository
    val favoritesList: StateFlow<List<Restaurant>> by lazy {
        restaurantRepository.getFavoriteRestaurantsFlow()
            .stateIn(scope, SharingStarted.WhileSubscribed(5000), emptyList())
    }

    val neverAgainList: StateFlow<List<Restaurant>> by lazy {
        restaurantRepository.getNeverAgainRestaurantsFlow()
            .stateIn(scope, SharingStarted.WhileSubscribed(5000), emptyList())
    }

    // The single source of truth for the deck, automatically combining all state
    val deck: StateFlow<List<Restaurant>> by lazy {
        combine(
            _allRestaurants,
            favoritesList,
            neverAgainList,
            _sessionSwipedRestaurants,
            _displayOrder
        ) { all, favorites, neverAgain, swipedIds, order ->
            val excludedIds = (favorites.map { it.id } + neverAgain.map { it.id } + swipedIds).toSet()
            val source = if (order.isNotEmpty()) order else all
            source.filterNot { excludedIds.contains(it.id) }
        }.stateIn(scope, SharingStarted.WhileSubscribed(5000), emptyList())
    }

    fun init(scope: CoroutineScope) {
        this.scope = scope
        scope.launch {
            restaurantRepository.getRestaurants().collect { restaurants ->
                _allRestaurants.value = restaurants
                if (_displayOrder.value.isEmpty()) {
                    _displayOrder.value = restaurants
                }
            }
        }
    }

    fun onSwipe(restaurant: Restaurant, direction: SwipeDirection) {
        _sessionSwipedRestaurants.update { it + restaurant.id }

        if (_swipeHistory.size >= MAX_SWIPE_HISTORY_SIZE) {
            _swipeHistory.removeAt(0)
        }
        _swipeHistory.add(Pair(restaurant, direction))

        scope.launch {
            when (direction) {
                SwipeDirection.UP -> toggleFavorite(restaurant.id)
                SwipeDirection.DOWN -> toggleNeverAgain(restaurant.id)
                SwipeDirection.RIGHT -> _selectedRestaurant.update { restaurant }
                else -> {}
            }
        }
    }

    fun undoLastSwipe() {
        if (_swipeHistory.isNotEmpty()) {
            val (lastRestaurant, lastDirection) = _swipeHistory.removeAt(_swipeHistory.lastIndex)
            scope.launch {
                when (lastDirection) {
                    SwipeDirection.UP -> toggleFavorite(lastRestaurant.id)
                    SwipeDirection.DOWN -> toggleNeverAgain(lastRestaurant.id)
                    else -> {}
                }
                _sessionSwipedRestaurants.update { it - lastRestaurant.id }
            }
        }
    }

    fun toggleFavorite(restaurantId: String) {
        scope.launch {
            restaurantManager.toggleFavorite(restaurantId)
        }
    }

    fun toggleNeverAgain(restaurantId: String) {
        scope.launch {
            restaurantManager.toggleNeverAgain(restaurantId)
        }
    }


    fun shuffleDeck() {
        val currentOrder = if (_displayOrder.value.isNotEmpty()) _displayOrder.value else _allRestaurants.value
        _displayOrder.value = currentOrder.shuffled()
    }

    fun updateDeck() {
        // No action because the deck is a combined flow,
        // Kept for dependency purposes
    }

    fun clearSessionSwipes() {
        _sessionSwipedRestaurants.value = emptySet()
        // Reset the display order to the original (all restaurants) order
        _displayOrder.value = _allRestaurants.value
        clearSwipeHistory()
    }

    fun clearSelectedRestaurant() {
        _selectedRestaurant.update { null }
    }

    fun clearSwipeHistory() {
        _swipeHistory.clear()
    }
}
