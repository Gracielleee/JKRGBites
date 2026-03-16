package com.jrkg.jrkgbites.data

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.GeoPoint
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jrkg.jrkgbites.R
import com.jrkg.jrkgbites.model.FavoriteRestaurantId
import com.jrkg.jrkgbites.model.NeverAgainRestaurantId
import com.jrkg.jrkgbites.model.Restaurant
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import java.io.InputStreamReader

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

        fun generateCompositeKey(restaurantId: String, userId: String): String {
            return "${restaurantId}_${userId}"
        }
    }

    // --- REMOTE MANAGEMENT (Firestore) ---

    suspend fun syncRestaurants(userId: String) = coroutineScope {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection(RESTAURANTS_COLLECTION)
        val userRef = db.collection(USER_COLLECTION).document(userId)

        try {
            Log.d(TAG, "Starting sync for user: $userId")

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
                        tags = (doc.get("tags") as? List<*>)?.mapNotNull { it as? String },
                        addedBy = doc.getDocumentReference("added_by")?.id,
                        isPublic = doc.getBoolean("is_public") ?: false
                    )
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing document ${doc.id}", e)
                    null
                }
            }

            // Using insertAll to merge Firestore data with existing local JSON data
            restaurantDao.insertAll(firebaseData)
            Log.d(TAG, "Successfully synced ${firebaseData.size} restaurants from Firestore")
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing restaurants", e)
        }
    }

    suspend fun syncFavoriteRestaurants(userId: String) = coroutineScope {
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection(USER_COLLECTION).document(userId)

        try {
            val querySnapshot = db.collection(FAVORITE_RESTAURANTS_COLLECTION)
                .whereEqualTo("user_id", userRef).get().await()

            val firebaseData = querySnapshot.documents.mapNotNull { doc ->
                val restaurantRef = doc.getDocumentReference("restaurant_id")
                restaurantRef?.let { FavoriteRestaurantId(it.id) }
            }

            favoriteRestaurantDao.clearAndInsert(firebaseData)
            Log.d(TAG, "Synced ${firebaseData.size} favorites")
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing favorites", e)
        }
    }

    suspend fun syncNeverAgainRestaurants(userId: String) = coroutineScope {
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection(USER_COLLECTION).document(userId)

        try {
            val querySnapshot = db.collection(NEVER_AGAIN_RESTAURANTS_COLLECTION)
                .whereEqualTo("user_id", userRef).get().await()

            val firebaseData = querySnapshot.documents.mapNotNull { doc ->
                val restaurantRef = doc.getDocumentReference("restaurant_id")
                restaurantRef?.let { NeverAgainRestaurantId(it.id) }
            }

            neverAgainRestaurantDao.clearAndInsert(firebaseData)
            Log.d(TAG, "Synced ${firebaseData.size} never-again items")
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing never-again", e)
        }
    }

    // --- CREATE / UPDATE (Firestore + Local) ---

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
        } catch (e: Exception) {
            Log.e(TAG, "Firestore create failed, rolling back local", e)
            restaurantDao.delete(localRestaurant)
        }
    }

    suspend fun addToFavorites(restaurantId: String, userId: String) {
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection(USER_COLLECTION).document(userId)
        val restaurantRef = db.collection(RESTAURANTS_COLLECTION).document(restaurantId)
        val compositeKey = generateCompositeKey(restaurantId, userId)

        val data = hashMapOf("user_id" to userRef, "restaurant_id" to restaurantRef)

        addToFavoritesLocal(restaurantId)
        try {
            db.collection(FAVORITE_RESTAURANTS_COLLECTION).document(compositeKey).set(data).await()
        } catch (e: Exception) {
            removeFromFavoritesLocal(restaurantId)
        }
    }

    suspend fun removeFromFavorites(restaurantId: String, userId: String) {
        val db = FirebaseFirestore.getInstance()
        val compositeKey = generateCompositeKey(restaurantId, userId)

        removeFromFavoritesLocal(restaurantId)
        try {
            db.collection(FAVORITE_RESTAURANTS_COLLECTION).document(compositeKey).delete().await()
        } catch (e: Exception) {
            addToFavoritesLocal(restaurantId)
        }
    }

    suspend fun addToNeverAgain(restaurantId: String, userId: String) {
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection(USER_COLLECTION).document(userId)
        val restaurantRef = db.collection(RESTAURANTS_COLLECTION).document(restaurantId)
        val compositeKey = generateCompositeKey(restaurantId, userId)

        val data = hashMapOf("user_id" to userRef, "restaurant_id" to restaurantRef)

        addToNeverAgainLocal(restaurantId)
        try {
            db.collection(NEVER_AGAIN_RESTAURANTS_COLLECTION).document(compositeKey).set(data).await()
        } catch (e: Exception) {
            removeFromNeverAgainLocal(restaurantId)
        }
    }

    suspend fun removeFromNeverAgain(restaurantId: String, userId: String) {
        val db = FirebaseFirestore.getInstance()
        val compositeKey = generateCompositeKey(restaurantId, userId)

        removeFromNeverAgainLocal(restaurantId)
        try {
            db.collection(NEVER_AGAIN_RESTAURANTS_COLLECTION).document(compositeKey).delete().await()
        } catch (e: Exception) {
            addToNeverAgainLocal(restaurantId)
        }
    }

    // --- LOCAL MANAGEMENT (Room & Assets) ---

    fun getRestaurantsLocal(): Flow<List<Restaurant>> = restaurantDao.getAllRestaurants()
    
    fun getFavoriteRestaurantsFlow(): Flow<List<Restaurant>> = restaurantDao.getFavoriteRestaurantsFlow()
    
    fun getNeverAgainRestaurantsFlow(): Flow<List<Restaurant>> = restaurantDao.getNeverAgainRestaurantsFlow()

    suspend fun ensureLocalDataFromJsonIfEmpty(context: Context) {
        if (restaurantDao.getRestaurantCount() == 0) {
            val restaurants = loadRestaurantsFromAsset(context)
            restaurantDao.insertAll(restaurants)
            Log.d(TAG, "Initialized local DB from JSON")
        }
    }

    private fun loadRestaurantsFromAsset(context: Context): List<Restaurant> {
        val inputStream = context.resources.openRawResource(R.raw.restaurants)
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<Restaurant>>() {}.type
        return Gson().fromJson(reader, type)
    }

    suspend fun searchRestaurantsByName(query: String): List<Restaurant> {
        return getRestaurantsLocal().first().filter { 
            it.name?.contains(query, ignoreCase = true) == true 
        }
    }

    // --- REUSE LOCAL DAO WRAPPERS ---
    suspend fun addToFavoritesLocal(id: String) = favoriteRestaurantDao.insert(FavoriteRestaurantId(id))
    suspend fun removeFromFavoritesLocal(id: String) = favoriteRestaurantDao.deleteById(id)
    suspend fun addToNeverAgainLocal(id: String) = neverAgainRestaurantDao.insert(NeverAgainRestaurantId(id))
    suspend fun removeFromNeverAgainLocal(id: String) = neverAgainRestaurantDao.deleteById(id)
    suspend fun isFavoritedInLocal(id: String) = favoriteRestaurantDao.isFavorited(id)
    suspend fun isNeverAgainInLocal(id: String) = neverAgainRestaurantDao.isNeverAgain(id)

    /**
     * Checks if a restaurant is favorited for the given user.
     *
     * Currently this relies on the local favorites table which is expected
     * to be kept in sync from Firestore.
     */
    suspend fun isFavorited(restaurantId: String, userId: String): Boolean {
        return isFavoritedInLocal(restaurantId)
    }

    /**
     * Checks if a restaurant is marked as "never again" for the given user.
     *
     * Currently this relies on the local never-again table which is expected
     * to be kept in sync from Firestore.
     */
    suspend fun isNeverAgain(restaurantId: String, userId: String): Boolean {
        return isNeverAgainInLocal(restaurantId)
    }

    fun getRestaurantById(id: String): Flow<Restaurant?> = restaurantDao.getRestaurantById(id)

    /**
     * Clears all local restaurant and preference data and reloads from the bundled JSON.
     */
    suspend fun pullFreshFromJSON(context: Context) {
        val restaurants = loadRestaurantsFromAsset(context)
        restaurantDao.clearAndInsert(restaurants)
    }

    /**
     * Deletes all local restaurant, favorites, and never-again data.
     */
    suspend fun deleteAllLocal() {
        restaurantDao.deleteAll()
        favoriteRestaurantDao.deleteAll()
        neverAgainRestaurantDao.deleteAll()
    }
}