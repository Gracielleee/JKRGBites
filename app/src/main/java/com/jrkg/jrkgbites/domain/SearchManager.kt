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

    fun searchAndSort(
        query: String,
        sortBy: SortOption = SortOption.BY_NAME,
        userLocation: Pair<Double, Double>? = null,
        radiusInMeters: Double? = null
    ): List<Restaurant> {
        // 1. Text-based search (Updated to handle null-safe names/categories)
        val textResults = if (query.isBlank()) {
            allRestaurants
        } else {
            allRestaurants.filter {
                val queryLower = query.lowercase()
                (it.name?.lowercase()?.contains(queryLower) == true) ||
                        (it.category?.lowercase()?.contains(queryLower) == true) ||
                        (it.cuisine?.lowercase()?.contains(queryLower) == true)
            }
        }

        // 2. Location-based filtering (Updated to convert String Lat/Lng to Double)
        val locationResults = if (userLocation != null && radiusInMeters != null) {
            textResults.filter { restaurant ->
                val rLat = restaurant.lat?.toDoubleOrNull()
                val rLng = restaurant.lng?.toDoubleOrNull()

                if (rLat != null && rLng != null) {
                    val distance = calculateDistance(
                        startLat = userLocation.first,
                        startLon = userLocation.second,
                        endLat = rLat,
                        endLon = rLng
                    )
                    distance <= radiusInMeters
                } else {
                    false
                }
            }
        } else {
            textResults
        }

        // 3. Sorting
        return sort(locationResults, sortBy)
    }

    private fun sort(restaurants: List<Restaurant>, option: SortOption): List<Restaurant> {
        return when (option) {
            SortOption.BY_NAME -> restaurants.sortedBy { it.name }
            // Using 'level' as the rating since that's what is in your JSON
            SortOption.BY_RATING_DESC -> restaurants.sortedByDescending { it.level?.toDoubleOrNull() ?: 0.0 }
            SortOption.BY_RATING_ASC -> restaurants.sortedBy { it.level?.toDoubleOrNull() ?: 0.0 }
        }
    }
}