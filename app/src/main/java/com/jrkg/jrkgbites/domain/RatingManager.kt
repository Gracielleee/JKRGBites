package com.jrkg.jrkgbites.domain

import com.jrkg.jrkgbites.data.RestaurantRatingRepository
import com.jrkg.jrkgbites.model.RestaurantRating
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

/**
 * Manages the logic related to submitting restaurant ratings and identifying poor experiences.
 */
class RatingManager(
    private val restaurantRatingRepository: RestaurantRatingRepository,
    private val restaurantManager: RestaurantManager
) {

    companion object {
        /**
         * The star rating below which a restaurant is considered a poor experience (candidate for Never Again).
         */
        const val RATING_THRESHOLD = 3
    }

    // This Flow comes directly from the database
    val allRatings: Flow<List<RestaurantRating>> = restaurantRatingRepository.getRatingsLocal()

    /**
     * Submits a rating for a given restaurant and applies rules based on the rating.
     *
     * @param restaurantId The ID of the restaurant being rated.
     * @param rating The star rating given by the user (e.g., 1, 2, 3, 4, 5).
     * @param comment The user's comment for the rating.
     */
    suspend fun submitRating(
        restaurantId: String,
        rating: Int,
        comment: String,
        userId: String
    ): Boolean {
        // 1. Check for existing local rating
        val existingRating = restaurantRatingRepository.getRatingForRestaurantLocal(restaurantId).first()

        val newRating = existingRating?.copy(
            rating = rating,
            comment = comment,
            timestamp = System.currentTimeMillis()
        ) ?: RestaurantRating(
            restaurantId = restaurantId,
            rating = rating,
            comment = comment,
            timestamp = System.currentTimeMillis()
        )

        // 2. Submit via repository (handles both Room and Firestore)
        restaurantRatingRepository.submitRating(newRating, userId)

        // 3. Logic check: if you want it to be automatic like Version 1,
        // you can uncomment the line below. Otherwise, let the ViewModel handle it.
        // if (rating < RATING_THRESHOLD) restaurantManager.addToNeverAgain(restaurantId, userId)

        return rating < RATING_THRESHOLD
    }

    /**
     * Retrieves the latest stored rating for a specific restaurant.
     */
    fun getRatingForRestaurant(restaurantId: String): Flow<RestaurantRating?> {
        return restaurantRatingRepository.getRatingForRestaurantLocal(restaurantId)
    }
}