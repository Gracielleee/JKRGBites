package com.jrkg.jrkgbites.domain

import com.jrkg.jrkgbites.data.RestaurantRatingDao
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
    private val swipeManager: SwipeManager,
    private val restaurantRatingDao: RestaurantRatingDao // Inject RestaurantRatingDao
) {

    companion object {
        /**
         * The star rating below which a restaurant is automatically disliked.
         */
        const val RATING_THRESHOLD = 3
    }

    // This Flow now comes directly from the database
    val allRatings: Flow<List<RestaurantRating>> = restaurantRatingDao.getAllRatings()

    /**
     * Submits a rating for a given restaurant and applies rules based on the rating.
     *
     * @param restaurantId The ID of the restaurant being rated.
     * @param rating The star rating given by the user (e.g., 1, 2, 3, 4, 5).
     * @param comment The user's comment for the rating.
     */
    suspend fun submitRating(restaurantId: String, rating: Int, comment: String) { // Made suspend
        val existingRating = restaurantRatingDao.getLatestRatingForRestaurant(restaurantId).first()

        val newRating = if (existingRating != null) {
            existingRating.copy(rating = rating, comment = comment, timestamp = System.currentTimeMillis())
        } else {
            RestaurantRating(restaurantId = restaurantId, rating = rating, comment = comment, timestamp = System.currentTimeMillis())
        }


        restaurantRatingDao.insert(newRating) // Insert (or update due to REPLACE strategy)

        // As per the project plan, if the rating is below the threshold,
        // the restaurant is automatically added to the "Never Again" list.
        if (rating < RATING_THRESHOLD) {
            swipeManager.addToNeverAgain(restaurantId)
        }
    }

    /**
     * Retrieves the latest stored rating for a specific restaurant.
     * @param restaurantId The ID of the restaurant.
     * @return A Flow emitting the latest RestaurantRating or null if not found.
     */
    fun getRatingForRestaurant(restaurantId: String): Flow<RestaurantRating?> {
        return restaurantRatingDao.getLatestRatingForRestaurant(restaurantId)
    }
}