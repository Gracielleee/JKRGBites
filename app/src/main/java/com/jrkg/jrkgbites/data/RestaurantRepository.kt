package com.jrkg.jrkgbites.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jrkg.jrkgbites.R
import com.jrkg.jrkgbites.model.FavoriteRestaurantId
import com.jrkg.jrkgbites.model.NeverAgainRestaurantId
import com.jrkg.jrkgbites.model.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.io.InputStreamReader
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await

class RestaurantRepository(
    private val restaurantDao: RestaurantDao,
    private val favoriteRestaurantDao: FavoriteRestaurantDao,
    private val neverAgainRestaurantDao: NeverAgainRestaurantDao,
) {
    companion object {
        private const val TAG = "RestaurantRepository"
        private const val RESTAURANTS_COLLECTION = "restaurants"
        private const val FAVORITE_RESTAURANTS_COLLECTION = "favoriteRestaurants"
        private const val NEVER_AGAIN_RESTAURANTS_COLLECTION = "neverAgainRestaurants"
        private const val USER_COLLECTION = "users"

        fun generateCompositeKey(restaurantId: String, userId: String): String{
            return "${restaurantId}_${userId}"
        }
    }

    // --- REMOTE MANAGEMENT (Firestore) ---

    /**
     * Syncs restaurants from Firestore.
     * We use insertAll (UPSERT) to merge remote changes without losing local JSON baseline.
     */
    suspend fun syncRestaurants(userId: String) = coroutineScope {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection(RESTAURANTS_COLLECTION)
        val userRef = db.collection(USER_COLLECTION).document(userId)

        try {
            Log.d(TAG, "Starting sync for user: $userId")

            // Fetch restaurants relevant to the user (Public or User-Added)
            val querySnapshot = collection.where(
                Filter.or(
                    Filter.equalTo("is_public", true),
                    Filter.equalTo("added_by", userRef)
                )
            ).get().await()

            val firebaseData = querySnapshot.documents.mapNotNull { doc ->
                try {
                    val geoPoint = doc.getGeoPoint("geopoint")
                    Restaurant(
                        id = doc.id,
                        name = doc.getString("name"),
                        category = doc.getString("category"),
                        cuisine = doc.getString("cuisine"),
                        level = doc.getString("level"),
                        location = doc.getString("location"),
                        lat = geoPoint?.latitude?.toString(),
                        lng = geoPoint?.longitude?.toString(),
                        logoResourceName = doc.getString("logo_filename"),
                        tags = doc.get("tags") as? List<String>,
                        addedBy = doc.getDocumentReference("added_by")?.id,
                        isPublic = doc.getBoolean("is_public") ?: false
                    )
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing document ${doc.id}", e)
                    null
                }
            }

            if (firebaseData.isNotEmpty()) {
                // UPSERT strategy: replaces duplicates by ID, adds new ones.
                restaurantDao.insertAll(firebaseData)
                Log.d(TAG, "Successfully merged ${firebaseData.size} restaurants from Firestore")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing restaurants", e)
        }
    }

    suspend fun syncFavoriteRestaurants(userId: String) = coroutineScope {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection(FAVORITE_RESTAURANTS_COLLECTION)
        val userRef = db.collection(USER_COLLECTION).document(userId)

        try {
            val querySnapshot = collection.whereEqualTo("user_id", userRef).get().await()

            val firebaseData = querySnapshot.documents.mapNotNull { doc ->
                try {
                    val restaurantRef = doc.getDocumentReference("restaurant_id")
                    FavoriteRestaurantId(
                        favoriteRestaurantId = restaurantRef?.id ?: ""
                    )
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing favorite restaurant document ${doc.id}", e)
                    null
                }
            }

            favoriteRestaurantDao.clearAndInsert(firebaseData)
            Log.d(TAG, "Successfully synced ${firebaseData.size} favorite restaurants")
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing favorite restaurants", e)
        }
    }

    suspend fun syncNeverAgainRestaurants(userId: String) = coroutineScope {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection(NEVER_AGAIN_RESTAURANTS_COLLECTION)
        val userRef = db.collection(USER_COLLECTION).document(userId)

        try {
            val querySnapshot = collection.whereEqualTo("user_id", userRef).get().await()

            val firebaseData = querySnapshot.documents.mapNotNull { doc ->
                try {
                    val restaurantRef = doc.getDocumentReference("restaurant_id")
                    NeverAgainRestaurantId(
                        neverAgainRestaurantId = restaurantRef?.id ?: ""
                    )
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing never again restaurant document ${doc.id}", e)
                    null
                }
            }

            neverAgainRestaurantDao.clearAndInsert(firebaseData)
            Log.d(TAG, "Successfully synced ${firebaseData.size} never again restaurants")
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing never again restaurants", e)
        }
    }

    suspend fun createRestaurant(restaurant: Restaurant, userId: String) {
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection(USER_COLLECTION).document(userId)

        val restaurantData = hashMapOf(
            "name" to restaurant.name,
            "category" to restaurant.category,
            "cuisine" to restaurant.cuisine,
            "level" to restaurant.level,
            "is_public" to false,
            "added_by" to userRef,
            "location" to restaurant.location,
            "geopoint" to GeoPoint(
                restaurant.lat?.toDoubleOrNull() ?: 0.0,
                restaurant.lng?.toDoubleOrNull() ?: 0.0
            ),
            "logo_filename" to restaurant.logoResourceName,
            "tags" to (restaurant.tags ?: emptyList<String>())
        )

        val localRestaurant = restaurant.copy(addedBy = userId)
        restaurantDao.insert(localRestaurant)

        try {
            db.collection(RESTAURANTS_COLLECTION).document(restaurant.id).set(restaurantData).await()
            Log.d(TAG, "Successfully saved to Firestore: ${restaurant.id}")
        } catch (e: Exception) {
            Log.e(TAG, "Firestore create failed, rolling back local", e)
            restaurantDao.delete(localRestaurant)
        }
    }

    suspend fun updateRestaurant(restaurant: Restaurant, userId: String): Int {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection(RESTAURANTS_COLLECTION)

        if (!isUserOwner(restaurant.addedBy ?: "", userId)) {
            Log.e(TAG, "Unauthorized update attempt")
            return 401
        }

        val originalRestaurant = restaurantDao.getRestaurantById(restaurant.id).first()
        val userRef = restaurant.addedBy?.let { db.collection(USER_COLLECTION).document(it) }

        val restaurantData = hashMapOf(
            "name" to restaurant.name,
            "category" to restaurant.category,
            "cuisine" to restaurant.cuisine,
            "level" to restaurant.level,
            "is_public" to (restaurant.isPublic ?: false),
            "added_by" to userRef,
            "location" to restaurant.location,
            "geopoint" to GeoPoint(
                restaurant.lat?.toDoubleOrNull() ?: 0.0,
                restaurant.lng?.toDoubleOrNull() ?: 0.0
            ),
            "logo_filename" to restaurant.logoResourceName,
            "tags" to (restaurant.tags ?: emptyList<String>())
        )

        updateRestaurant(restaurant)

        try {
            collection.document(restaurant.id)
                .set(restaurantData, SetOptions.merge())
                .await()
            return 200
        } catch (e: Exception) {
            Log.e(TAG, "Error updating restaurant in Firestore", e)
            originalRestaurant?.let { updateRestaurant(it) }
            return 500
        }
    }

    suspend fun deleteRestaurant(restaurant: Restaurant, userId: String): Int {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection(RESTAURANTS_COLLECTION)

        if (!isUserOwner(restaurant.addedBy ?: "", userId)) {
            Log.e(TAG, "Unauthorized delete attempt")
            return 401
        }

        deleteRestaurantLocal(restaurant)

        try {
            collection.document(restaurant.id).delete().await()
            Log.d(TAG, "Successfully deleted from restaurants: ${restaurant.id}")
            return 200
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting Restaurant from Firestore", e)
            insertRestaurantLocal(restaurant)
            return 500
        }
    }

    private fun isUserOwner(addedBy: String, userId: String): Boolean{
        return addedBy == userId
    }


    suspend fun addToFavorites(restaurantId: String, userId: String) {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("favoriteRestaurants")
        val userRef = db.collection("users").document(userId)
        val restaurantRef = db.collection("restaurants").document(restaurantId)
        val compositeKey = generateCompositeKey(restaurantId, userId)

        val favoriteRestaurantData = hashMapOf(
            "user_id" to userRef,
            "restaurant_id" to restaurantRef
        )

        addToFavoritesLocal(restaurantId)
        try {
            collection.document(compositeKey).set(favoriteRestaurantData).await()
            Log.d(TAG, "Successfully added to favorites: $restaurantId")
        } catch (e: Exception) {
            Log.e(TAG, "Error saving Favorite to Firestore", e)
            removeFromFavoritesLocal(restaurantId)
        }
    }

    suspend fun addToNeverAgain(restaurantId: String, userId: String) {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("neverAgainRestaurants")
        val userRef = db.collection("users").document(userId)
        val restaurantRef = db.collection("restaurants").document(restaurantId)
        val compositeKey = generateCompositeKey(restaurantId, userId)

        val neverAgainRestaurantData = hashMapOf(
            "user_id" to userRef,
            "restaurant_id" to restaurantRef
        )

        addToNeverAgainLocal(restaurantId)
        try {
            collection.document(compositeKey).set(neverAgainRestaurantData).await()
            Log.d(TAG, "Successfully added to never again: $restaurantId")
        } catch (e: Exception) {
            Log.e(TAG, "Error saving Never Again to Firestore", e)
            removeFromNeverAgainLocal(restaurantId)
        }
    }

    suspend fun removeFromFavorites(restaurantId: String, userId: String) {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("favoriteRestaurants")
        val compositeKey = generateCompositeKey(restaurantId, userId)

        removeFromFavoritesLocal(restaurantId)
        try {
            collection.document(compositeKey).delete().await()
            Log.d(TAG, "Successfully deleted from favorites: $restaurantId")
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting Favorite from Firestore", e)
            addToFavoritesLocal(restaurantId)
        }
    }

    suspend fun removeFromNeverAgain(restaurantId: String, userId: String) {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("neverAgainRestaurants")
        val compositeKey = generateCompositeKey(restaurantId, userId)

        removeFromNeverAgainLocal(restaurantId)
        try {
            collection.document(compositeKey).delete().await()
            Log.d(TAG, "Successfully deleted from never again: $restaurantId")
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting Never Again from Firestore", e)
            addToNeverAgainLocal(restaurantId)
        }
    }

    suspend fun isFavorited(restaurantId: String, userId: String): Boolean {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection(FAVORITE_RESTAURANTS_COLLECTION)
        val compositeKey = generateCompositeKey(restaurantId, userId)

        return try {
            val document = collection.document(compositeKey).get().await()
            document.exists()
        } catch (e: Exception) {
            Log.e(TAG, "Error checking favorited status", e)
            false
        }
    }

    suspend fun isNeverAgain(restaurantId: String, userId: String): Boolean {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection(NEVER_AGAIN_RESTAURANTS_COLLECTION)
        val compositeKey = generateCompositeKey(restaurantId, userId)

        return try {
            val document = collection.document(compositeKey).get().await()
            document.exists()
        } catch (e: Exception) {
            Log.e(TAG, "Error checking never again status", e)
            false
        }
    }


    //LOCAL MANAGEMENT
    fun getRestaurantsLocal(): Flow<List<Restaurant>> {
        return restaurantDao.getAllRestaurants()
    }

    suspend fun insertRestaurantLocal(restaurant: Restaurant) {
        restaurantDao.insert(restaurant)
    }

    suspend fun deleteRestaurantLocal(restaurant: Restaurant) {
        restaurantDao.delete(restaurant)
    }

    suspend fun deleteAllLocal() {
        restaurantDao.deleteAll()
    }

    fun getRestaurantById(id: String): Flow<Restaurant?> {
        return restaurantDao.getRestaurantById(id)
    }

    fun getFavoriteRestaurantsFlow(): Flow<List<Restaurant>> {
        return restaurantDao.getFavoriteRestaurantsFlow()
    }

    fun getNeverAgainRestaurantsFlow(): Flow<List<Restaurant>> {
        return restaurantDao.getNeverAgainRestaurantsFlow()
    }

    private suspend fun hasData(): Boolean {
        return restaurantDao.getRestaurantCount() > 0
    }

    /**
     * Ensures that the local database always contains the baseline restaurants from JSON.
     * Unlike previous versions, we don't skip this if data exists, to ensure sync doesn't
     * permanently remove JSON entries.
     */
    suspend fun refreshRestaurants(context: Context) {
        val restaurants = loadRestaurantsFromAsset(context)
        restaurantDao.insertAll(restaurants)
        Log.d(TAG, "Refreshed/Merged ${restaurants.size} restaurants from JSON baseline")
    }

    suspend fun pullFreshFromJSON(context: Context) {
        restaurantDao.deleteAll()
        val restaurants = loadRestaurantsFromAsset(context)
        restaurantDao.insertAll(restaurants)
    }

    private fun loadRestaurantsFromAsset(context: Context): List<Restaurant> {
        val inputStream = context.resources.openRawResource(R.raw.restaurants)
        val reader = InputStreamReader(inputStream)
        val restaurantListType = object : TypeToken<List<Restaurant>>() {}.type
        return Gson().fromJson(reader, restaurantListType)
    }

    suspend fun searchRestaurantsByName(query: String): List<Restaurant> {
        return getRestaurantsLocal().first().filter { it.name?.contains(query, ignoreCase = true) == true }
    }

    suspend fun updateRestaurant(restaurant: Restaurant) {
        restaurantDao.update(restaurant)
    }

    suspend fun addToFavoritesLocal(restaurantId: String) {
        favoriteRestaurantDao.insert(FavoriteRestaurantId(restaurantId))
    }

    suspend fun removeFromFavoritesLocal(restaurantId: String) {
        favoriteRestaurantDao.deleteById(restaurantId)
    }

    suspend fun addToNeverAgainLocal(restaurantId: String) {
        neverAgainRestaurantDao.insert(NeverAgainRestaurantId(restaurantId))
    }

    suspend fun removeFromNeverAgainLocal(restaurantId: String) {
        neverAgainRestaurantDao.deleteById(restaurantId)
    }

    suspend fun isFavoritedInLocal(restaurantId: String): Boolean {
        return favoriteRestaurantDao.isFavorited(restaurantId)
    }

    suspend fun isNeverAgainInLocal(restaurantId: String): Boolean {
        return neverAgainRestaurantDao.isNeverAgain(restaurantId)
    }
}
