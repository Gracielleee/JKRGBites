package com.jrkg.jrkgbites.domain

import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.utils.calculateDistance

enum class SortOption {
    BY_NAME,
    BY_RATING_DESC,
    BY_RATING_ASC
}

class SearchManager(restaurantRepository: RestaurantRepository) {

    private val allRestaurants: List<Restaurant> by lazy {
        restaurantRepository.getRestaurants()
    }

    /**
     * Searches and filters restaurants based on a query and optional location, then sorts the result.
     *
     * @param query The text to search for in restaurant name, category, or cuisine. Case-insensitive.
     * @param sortBy The sorting order for the results.
     * @param userLocation A Pair of the user's (Latitude, Longitude). If provided with radius, enables distance filtering.
     * @param radiusInMeters The search radius in meters. If provided with userLocation, enables distance filtering.
     * @return A sorted list of matching restaurants.
     */
    fun searchAndSort(
        query: String,
        sortBy: SortOption = SortOption.BY_NAME,
        userLocation: Pair<Double, Double>? = null,
        radiusInMeters: Double? = null
    ): List<Restaurant> {
        // 1. Text-based search
        val textResults = if (query.isBlank()) {
            allRestaurants
        } else {
            allRestaurants.filter {
                val queryLower = query.lowercase()
                it.name.lowercase().contains(queryLower) ||
                it.category.lowercase().contains(queryLower) ||
                it.cuisine.lowercase().contains(queryLower)
            }
        }

        // 2. Location-based filtering
        val locationResults = if (userLocation != null && radiusInMeters != null) {
            textResults.filter { restaurant ->
                val distance = calculateDistance(
                    startLat = userLocation.first,
                    startLon = userLocation.second,
                    endLat = restaurant.location.lat,
                    endLon = restaurant.location.lng
                )
                distance <= radiusInMeters
            }
        } else {
            textResults
        }

        // 3. Sorting
        return sort(locationResults, sortBy)
    }

    /**
     * Sorts a given list of restaurants.
     */
    private fun sort(restaurants: List<Restaurant>, option: SortOption): List<Restaurant> {
        return when (option) {
            SortOption.BY_NAME -> restaurants.sortedBy { it.name }
            SortOption.BY_RATING_DESC -> restaurants.sortedByDescending { it.rating }
            SortOption.BY_RATING_ASC -> restaurants.sortedBy { it.rating }
        }
    }
}
