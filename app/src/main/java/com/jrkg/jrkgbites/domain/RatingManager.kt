package com.jrkg.jrkgbites.domain

/**
 * Manages the logic related to submitting restaurant ratings.
 *
 * @param swipeManager The manager for user lists (favorites, never again), used to
 * add restaurants to the "Never Again" list based on rating.
 */
class RatingManager(private val swipeManager: SwipeManager) {

    companion object {
        /**
         * The star rating below which a restaurant is automatically disliked.
         */
        const val RATING_THRESHOLD = 3
    }

    /**
     * Submits a rating for a given restaurant and applies rules based on the rating.
     *
     * @param restaurantId The ID of the restaurant being rated.
     * @param rating The star rating given by the user (e.g., 1, 2, 3, 4, 5).
     */
    fun submitRating(restaurantId: String, rating: Int) {
        // As per the project plan, if the rating is below the threshold,
        // the restaurant is automatically added to the "Never Again" list.
        if (rating < RATING_THRESHOLD) {
            swipeManager.addToNeverAgain(restaurantId)
        }

        // In a future step, this is where you would also save the actual rating value
        // to a local database or send it to a remote server.
    }
}
