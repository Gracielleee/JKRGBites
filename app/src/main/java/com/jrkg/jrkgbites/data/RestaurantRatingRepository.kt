package com.jrkg.jrkgbites.data

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.jrkg.jrkgbites.model.RestaurantRating
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.tasks.await

class RestaurantRatingRepository(
    private val restaurantRatingDao: RestaurantRatingDao,
) {

    companion object {
        private const val TAG = "RestaurantRatingRepository"
        private const val RESTAURANTS_COLLECTION = "restaurants"
        private const val RATINGS_COLLECTION = "restaurantRatings"
        private const val USER_COLLECTION = "users"

        fun generateCompositeKey(restaurantId: String, userId: String): String{
            return "${restaurantId}_${userId}"
        }
    }

    // REMOTE MANAGEMENT
    suspend fun syncRatings(userId: String) = coroutineScope {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection(RATINGS_COLLECTION)
        val userRef = db.collection(USER_COLLECTION).document(userId)

        try {
            val querySnapshot = collection.whereEqualTo("user_id", userRef).get().await() //Fetch only current user ratings

            val firebaseData = querySnapshot.documents.mapNotNull { doc ->
                try {
                    val restaurantRef = doc.getDocumentReference("restaurant_id")
                    val timestampLong = when (val ts = doc.get("timestamp")) {
                        is Long -> ts
                        is com.google.firebase.Timestamp -> ts.toDate().time
                        else -> 0L
                    }

                    RestaurantRating(
                        id = doc.id,
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

            Log.d(TAG, "Successfully synced ${firebaseData.size} ratings")
            Log.d(TAG, "Ratings syncing complete for user: $userId")

        } catch (e: Exception) {
            Log.e(TAG, "Error syncing ratings", e)
        }
    }

    suspend fun submitRating(restaurantRating: RestaurantRating, userId: String) {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection(RATINGS_COLLECTION)
        val userRef = db.collection(USER_COLLECTION).document(userId)
        val restaurantRef = db.collection(RESTAURANTS_COLLECTION).document(restaurantRating.restaurantId)
        val compositeKey = RestaurantRepository.generateCompositeKey(restaurantRating.restaurantId, userId)

        val ratingData = hashMapOf(
            "user_id" to userRef,
            "restaurant_id" to restaurantRef,
            "rating" to restaurantRating.rating,
            "comment" to restaurantRating.comment,
            "timestamp" to Timestamp.now(),
        )

        //Add to RoomDB first for instant UI updates
        insertRatingLocal(restaurantRating)
        Log.d(TAG, "Attempting to add to ratings: ${restaurantRating.restaurantId}")
        try {
            collection
                .document(compositeKey)
                .set(ratingData, SetOptions.merge()) //For replacing/updating existing ratings
                .await()
            Log.d(TAG, "Successfully added to ratings: ${restaurantRating.restaurantId}")
        } catch (e: Exception) {
            Log.e(TAG, "Error saving rating to Firestore", e)
            removeRatingLocal(restaurantRating)
        }
    }

    suspend fun deleteRestaurantRatingByCascade(restaurantId: String, userId: String): Int {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection(RATINGS_COLLECTION)
        val compositeKey = RestaurantRepository.generateCompositeKey(restaurantId, userId)

        val originalRating = getRatingForRestaurantLocal(restaurantId).firstOrNull()

        originalRating?.let { removeRatingLocal(it) }

        return try {
            // Delete from Firestore using composite key
            collection.document(compositeKey).delete().await()
            Log.d(TAG, "Successfully deleted rating: $compositeKey")
            200
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting rating from Firestore", e)
            // Rollback if Firestore fails
            originalRating?.let { insertRatingLocal(it) }
            500
        }
    }

    // LOCAL MANAGEMENT
    fun getRatingsLocal(): Flow<List<RestaurantRating>> {
        return restaurantRatingDao.getAllRatings()
    }

    fun getRatingForRestaurantLocal(restaurantId: String): Flow<RestaurantRating?> {
        return restaurantRatingDao.getLatestRatingForRestaurant(restaurantId)
    }

    suspend fun insertRatingLocal(rating: RestaurantRating) {
        restaurantRatingDao.insert(rating)
    }

    suspend fun removeRatingLocal(rating: RestaurantRating) {
        restaurantRatingDao.delete(rating)
    }

}
