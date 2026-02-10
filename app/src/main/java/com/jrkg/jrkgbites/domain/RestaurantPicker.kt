package com.jrkg.jrkgbites.domain

import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.model.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RestaurantPicker(
    private val restaurantRepository: RestaurantRepository
) {
    private lateinit var scope: CoroutineScope // ADDED: Will be initialized by init()

    private val _allRestaurants = MutableStateFlow<List<Restaurant>>(emptyList())

    // New initialization method to pass the CoroutineScope
    fun init(scope: CoroutineScope) {
        this.scope = scope
        scope.launch {
            restaurantRepository.getRestaurants().collect { restaurants ->
                _allRestaurants.value = restaurants
            }
        }
    }

    /**
     * Picks a random item based on the specified filters.
     *
     * @param filterType The type of item to pick ("Resto", "Cuisine", "Category").
     * @param mallFilter An optional filter to only consider restaurants from a specific mall.
     * @return A string representing the randomly picked item, or null if no item could be picked.
     */
    fun pickRandom(filterType: String, mallFilter: String? = null): String? {
        if (_allRestaurants.value.isEmpty()) {
            return null
        }

        // 1. Apply the filter.
        // Note: Since 'Level' or 'tags' usually contains location info in your JSON,
        // we check if the tags contain the mall name.
        val availableRestaurants = if (mallFilter != null) {
            _allRestaurants.value.filter { it.tags?.contains(mallFilter) == true }
        } else {
            _allRestaurants.value
        }

        if (availableRestaurants.isEmpty()) {
            return null
        }

        // 2. Updated to match your new Restaurant.kt field names (Name, Cuisine, Category)
        return when (filterType) {
            "Cuisine" -> availableRestaurants.mapNotNull { it.cuisine }.distinct().randomOrNull()
            "Category" -> availableRestaurants.mapNotNull { it.category }.distinct().randomOrNull()
            "Resto" -> availableRestaurants.randomOrNull()?.name
            else -> null
        }
    }
}