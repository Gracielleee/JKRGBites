package com.jrkg.jrkgbites.domain

import com.jrkg.jrkgbites.data.RestaurantRatingRepository
import com.jrkg.jrkgbites.model.RestaurantRating
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

/**
 * Manages the logic related to submitting restaurant ratings.
 *
 * @param swipeManager The manager for user lists (favorites, never again), used to
 * add restaurants to the "Never Again" list based on rating.
 * @param restaurantRatingDao The DAO for accessing restaurant rating data in the database.
 */
class RatingManager(
    private val restaurantRatingRepository: RestaurantRatingRepository,
    private val restaurantManager: RestaurantManager
) {

    companion object {
        /**
         * The star rating below which a restaurant is automatically disliked.
         */
        const val RATING_THRESHOLD = 2
    }

    // This Flow comes directly from the database
    val allRatings: Flow<List<RestaurantRating>> = restaurantRatingRepository.getRatingsLocal()

    /**
     * Submits a rating for a given restaurant and returns whether it is considered "low".
     *
     * Domain layer no longer decides on "Never Again" side effects. Instead, callers can
     * use the returned flag to drive UI (e.g., showing a confirmation dialog) and call
     * the appropriate RestaurantManager method explicitly.
     * @param restaurantId The ID of the restaurant being rated.
     * @param rating The star rating given by the user (e.g., 1, 2, 3, 4, 5).
     * @param comment The user's comment for the rating.
     * @return true if the rating is below [RATING_THRESHOLD], false otherwise.
     */
    suspend fun submitRating(restaurantId: String, rating: Int, comment: String): Boolean { // Made suspend
        val existingRating = restaurantRatingRepository.getRatingForRestaurantLocal(restaurantId).first()

        val newRating = if (existingRating != null) {
            existingRating.copy(rating = rating, comment = comment, timestamp = System.currentTimeMillis())
        } else {
            RestaurantRating(restaurantId = restaurantId, rating = rating, comment = comment, timestamp = System.currentTimeMillis())
        }

        restaurantRatingRepository.insertRatingLocal(newRating) // Insert (or update due to REPLACE strategy)

        // The caller decides what to do with low ratings (e.g., prompt for "Never Again").
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