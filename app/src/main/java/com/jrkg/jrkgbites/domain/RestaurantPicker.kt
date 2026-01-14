package com.jrkg.jrkgbites.domain

import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.model.Restaurant

class RestaurantPicker(private val restaurantRepository: RestaurantRepository) {

    // Lazily load the restaurants from the repository on first access.
    private val allRestaurants: List<Restaurant> by lazy {
        restaurantRepository.getRestaurants()
    }

    /**
     * Picks a random item based on the specified filters.
     *
     * @param filterType The type of item to pick ("Resto", "Cuisine", "Category").
     * @param mallFilter An optional filter to only consider restaurants from a specific mall.
     * @return A string representing the randomly picked item, or null if no item could be picked.
     */
    fun pickRandom(filterType: String, mallFilter: String? = null): String? {
        if (allRestaurants.isEmpty()) {
            return null
        }

        // 1. Apply the mall filter if provided.
        val availableRestaurants = if (mallFilter != null) {
            allRestaurants.filter { it.location.mall.equals(mallFilter, ignoreCase = true) }
        } else {
            allRestaurants
        }

        if (availableRestaurants.isEmpty()) {
            return null
        }

        // 2. Pick a result based on the filterType, as described in the idea file.
        return when (filterType) {
            "Cuisine" -> availableRestaurants.map { it.cuisine }.distinct().randomOrNull()
            "Category" -> availableRestaurants.map { it.category }.distinct().randomOrNull()
            "Resto" -> availableRestaurants.randomOrNull()?.name
            else -> null // Return null for an unsupported filterType
        }
    }
}
