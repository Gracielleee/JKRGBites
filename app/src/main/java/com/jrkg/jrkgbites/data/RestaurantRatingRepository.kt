package com.jrkg.jrkgbites.data

import com.google.firebase.firestore.FirebaseFirestore
import com.jrkg.jrkgbites.model.RestaurantRating
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class RestaurantRatingRepository(
    private val restaurantRatingDao: RestaurantRatingDao,
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    companion object {
        private const val RATINGS_COLLECTION = "restaurant_ratings"
    }

    private val ratingsCollection = firestore.collection(RATINGS_COLLECTION)

    // LOCAL MANAGEMENT
    fun getRatings(): Flow<List<RestaurantRating>> {
        return restaurantRatingDao.getAllRatings()
    }

    fun getRatingForRestaurant(restaurantId: String): Flow<RestaurantRating?> {
        return restaurantRatingDao.getLatestRatingForRestaurant(restaurantId)
    }

    /**
     * Inserts or updates a rating in the local Room database and mirrors the change to Firestore.
     */
    suspend fun insertRating(rating: RestaurantRating) {
        // 1. Local write for immediate UI reactivity
        restaurantRatingDao.insert(rating)

        // 2. Remote write for persistence and cross-device access
        syncRatingToRemote(rating)
    }

    /**
     * Writes the given rating to Firestore using the restaurantId as the document key.
     */
    private suspend fun syncRatingToRemote(rating: RestaurantRating) {
        try {
            val data = hashMapOf(
                "restaurant_id" to rating.restaurantId,
                "rating" to rating.rating,
                "comment" to rating.comment,
                "timestamp" to rating.timestamp
            )

            ratingsCollection
                .document(rating.restaurantId)
                .set(data)
                .await()
        } catch (_: Exception) {
            // Swallow remote errors for now; local data remains the single source of truth.
        }
    }
}