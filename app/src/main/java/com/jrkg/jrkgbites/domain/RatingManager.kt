package com.jrkg.jrkgbites.domain

import com.jrkg.jrkgbites.data.repository.RestaurantRatingRepository
import com.jrkg.jrkgbites.model.RestaurantRating
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

/**
 * Manages the logic related to submitting restaurant ratings.
 *
 */
class RatingManager(
    private val restaurantRatingRepository: RestaurantRatingRepository,
) {

    companion object {
        /**
         * The star rating below which a restaurant is prompted to be added to never again.
         */
        const val RATING_THRESHOLD = 2
    }

    // This Flow comes directly from the database
    val allRatings: Flow<List<RestaurantRating>> = restaurantRatingRepository.getRatingsLocal()

    /**
     * Submits a rating for a given restaurant and returns whether it is considered "low".
     *
     * @param restaurantId The ID of the restaurant being rated.
     * @param rating The star rating given by the user (e.g., 1, 2, 3, 4, 5).
     * @param comment The user's comment for the rating.
     * @return true if the rating is below [RATING_THRESHOLD], false otherwise.
     */
    suspend fun submitRating(restaurantId: String, rating: Int, comment: String, userId: String): Boolean { // Made suspend
        val existingRating = restaurantRatingRepository.getRatingForRestaurantLocal(restaurantId).first()
        val id = RestaurantRatingRepository.generateCompositeKey(restaurantId, userId)

        val newRating = if (existingRating != null) {
            existingRating.copy(id = existingRating.id, rating = rating, comment = comment, timestamp = System.currentTimeMillis())
        } else {
            RestaurantRating(id = id, restaurantId = restaurantId, rating = rating, comment = comment, timestamp = System.currentTimeMillis())
        }

        restaurantRatingRepository.submitRating(newRating, userId)

        // The caller decides what to do with low ratings
        return rating < RATING_THRESHOLD
    }

    /**
     * Retrieves the latest stored rating for a specific restaurant.
     * @param restaurantId The ID of the restaurant.
     * @return A Flow emitting the latest RestaurantRating or null if not found.
     */
    fun getRatingForRestaurant(restaurantId: String): Flow<RestaurantRating?> {
        return restaurantRatingRepository.getRatingForRestaurantLocal(restaurantId)
    }
}
