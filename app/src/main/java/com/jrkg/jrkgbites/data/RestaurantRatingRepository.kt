package com.jrkg.jrkgbites.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.jrkg.jrkgbites.model.RestaurantRating
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class RestaurantRatingRepository(
    private val restaurantRatingDao: RestaurantRatingDao,
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    companion object {
        private const val TAG = "RestaurantRatingRepo"
        private const val RESTAURANTS_COLLECTION = "restaurants"
        private const val RATINGS_COLLECTION = "restaurant_ratings"
        private const val USER_COLLECTION = "users"
    }

    // --- LOCAL MANAGEMENT (Room) ---

    fun getRatingsLocal(): Flow<List<RestaurantRating>> = 
        restaurantRatingDao.getAllRatings()

    fun getRatingForRestaurantLocal(restaurantId: String): Flow<RestaurantRating?> = 
        restaurantRatingDao.getLatestRatingForRestaurant(restaurantId)

    // --- REMOTE SYNCING (Firestore) ---

    /**
     * Fetches all ratings for a specific user from Firestore and updates the local database.
     */
    suspend fun syncRatingsFromRemote(userId: String) = coroutineScope {
        val userRef = firestore.collection(USER_COLLECTION).document(userId)

        try {
            val querySnapshot = firestore.collection(RATINGS_COLLECTION)
                .whereEqualTo("user_id", userRef)
                .get()
                .await()

            val firebaseData = querySnapshot.documents.mapNotNull { doc ->
                try {
                    val restaurantRef = doc.getDocumentReference("restaurant_id")
                    val timestamp = doc.getTimestamp("timestamp")
                    val timestampLong = timestamp?.toDate()?.time ?: 0L

                    RestaurantRating(
                        id = doc.id.hashCode(), // Using hashCode if your Room ID is an Int
                        restaurantId = restaurantRef?.id ?: "",
                        rating = doc.getLong("rating")?.toInt() ?: 0,
                        comment = doc.getString("comment") ?: "",
                        timestamp = timestampLong
                    )
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing rating document ${doc.id}", e)
                    null
                }
            }

            restaurantRatingDao.clearAndInsert(firebaseData)
            Log.d(TAG, "Successfully synced ${firebaseData.size} ratings for user: $userId")

        } catch (e: Exception) {
            Log.e(TAG, "Error syncing ratings from remote", e)
        }
    }

    /**
     * Inserts locally first for instant UI response, then attempts to push to Firestore.
     */
    suspend fun submitRating(rating: RestaurantRating, userId: String) {
        // 1. Local write first
        restaurantRatingDao.insert(rating)

        // 2. Prepare remote data
        val userRef = firestore.collection(USER_COLLECTION).document(userId)
        val restaurantRef = firestore.collection(RESTAURANTS_COLLECTION).document(rating.restaurantId)
        
        // Use a composite key (userId + restaurantId) to prevent duplicate ratings per restaurant
        val compositeKey = "${userId}_${rating.restaurantId}"

        val ratingData = hashMapOf(
            "user_id" to userRef,
            "restaurant_id" to restaurantRef,
            "rating" to rating.rating,
            "comment" to rating.comment,
            "timestamp" to System.currentTimeMillis()
        )

        try {
            firestore.collection(RATINGS_COLLECTION)
                .document(compositeKey)
                .set(ratingData)
                .await()
            Log.d(TAG, "Successfully synced rating to Firestore")
        } catch (e: Exception) {
            Log.e(TAG, "Firestore sync failed", e)
            // Optional: If sync fails, you could choose to delete the local record 
            // or leave it to be synced later.
        }
    }

    suspend fun removeRating(rating: RestaurantRating) {
        restaurantRatingDao.delete(rating)
        // Add Firestore delete logic here if needed
    }
}