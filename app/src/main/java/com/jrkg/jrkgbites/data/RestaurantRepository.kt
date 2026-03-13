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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await

class RestaurantRepository(
    private val restaurantDao: RestaurantDao,
    private val favoriteRestaurantDao: FavoriteRestaurantDao,
    private val neverAgainRestaurantDao: NeverAgainRestaurantDao,
) {
    companion object {
        private const val TAG = "RestaurantRepository"
    }


    // REMOTE MANAGEMENT
    suspend fun syncRestaurants(userId: String) = coroutineScope {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("restaurants")
        val userRef = db.collection("users").document(userId)

        try {
            Log.d(TAG, "Starting sync for user: $userId")

            // Fetch only restaurants that are public OR added by the current user
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

            restaurantDao.clearAndInsert(firebaseData)
            Log.d(TAG, "Successfully synced ${firebaseData.size} restaurants")
            Log.d(TAG, "Syncing complete for user: $userId")
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing restaurants", e)
        }
    }

    suspend fun syncFavoriteRestaurants(userId: String) = coroutineScope {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("favoriteRestaurants")
        val userRef = db.collection("users").document(userId)

        try {
            val querySnapshot = collection.whereEqualTo("user_id", userRef).get().await() //Fetch only current user favorites

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
            Log.d(TAG, "Favorites syncing complete for user: $userId")

        } catch (e: Exception) {
            Log.e(TAG, "Error syncing favorite restaurants", e)
        }
    }

    suspend fun syncNeverAgainRestaurants(userId: String) = coroutineScope {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("neverAgainRestaurants")
        val userRef = db.collection("users").document(userId)

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
            Log.d(TAG, "Never Again syncing complete for user: $userId")

        } catch (e: Exception) {
            Log.e(TAG, "Error syncing never again restaurants", e)
        }
    }

    suspend fun createRestaurant(restaurant: Restaurant, userId: String) {
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(userId)

        val restaurantData = hashMapOf(
            "name" to restaurant.name,
            "category" to restaurant.category,
            "cuisine" to restaurant.cuisine,
            "level" to restaurant.level,
            "is_public" to false, //Always false
            "added_by" to userRef,
            "location" to restaurant.location,
            "geopoint" to GeoPoint(
                restaurant.lat?.toDoubleOrNull() ?: 0.0,
                restaurant.lng?.toDoubleOrNull() ?: 0.0
            ),
            "logo_filename" to restaurant.logoResourceName,
            "tags" to (restaurant.tags ?: emptyList<String>())
        )

        // 1. Save to local RoomDB immediately for instant UI updates
        val localRestaurant = restaurant.copy(addedBy = userId)
        restaurantDao.insert(localRestaurant)
        Log.d(TAG, "Inserted restaurant locally: ${restaurant.id}")

        // 2. Save to Firebase
        try {
            db.collection("restaurants").document(restaurant.id).set(restaurantData).await()
            Log.d(TAG, "Successfully saved to Firestore: ${restaurant.id}")
        } catch (e: Exception) {
            Log.e(TAG, "Error saving to Firestore", e)
            restaurantDao.delete(localRestaurant) // Rollback if Firestore fails
        }
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

        //Add to RoomDB first for instant UI updates
        addToFavoritesLocal(restaurantId)
        Log.d(TAG, "Attempting to add to favorites: $restaurantId")
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
        Log.d(TAG, "Attempting to add to never again: $restaurantId")
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
            // Delete the document based on the composite key
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
            // Delete the document based on the composite key
            collection.document(compositeKey).delete().await()
            Log.d(TAG, "Successfully deleted from never again: $restaurantId")
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting Never Again from Firestore", e)
            addToNeverAgainLocal(restaurantId)
        }
    }

    suspend fun isFavorited(restaurantId: String, userId: String): Boolean {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("favoriteRestaurants")
        val compositeKey = generateCompositeKey(restaurantId, userId)

        return try {
            // Attempt to get the document with the composite key
            val document = collection.document(compositeKey).get().await()
            if (document.exists()){
                true
            } else {
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error checking if restaurant is favorited", e)
            false
        }
    }

    suspend fun isNeverAgain(restaurantId: String, userId: String): Boolean {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("neverAgainRestaurants")
        val compositeKey = generateCompositeKey(restaurantId, userId)

        return try {
            // Attempt to get the document with the composite key
            val document = collection.document(compositeKey).get().await()
            if (document.exists()){
                true
            } else {
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error checking if restaurant is never again", e)
            false
        }
    }



    private fun generateCompositeKey(restaurantId: String, userId: String): String{
        return "${restaurantId}_${userId}"
    }


    //LOCAL MANAGEMENT
    // Get restaurants from the database
    fun getRestaurantsLocal(): Flow<List<Restaurant>> {
        return restaurantDao.getAllRestaurants()
    }

    suspend fun deleteAllLocal(): Unit {
        return restaurantDao.deleteAll()

    }

    // Get a specific restaurant by its ID
    fun getRestaurantById(id: String): Flow<Restaurant?> {
        return restaurantDao.getRestaurantById(id)
    }

    fun getFavoriteRestaurantsFlow(): Flow<List<Restaurant>> {
        return restaurantDao.getFavoriteRestaurantsFlow()
    }

    fun getNeverAgainRestaurantsFlow(): Flow<List<Restaurant>> {
        return restaurantDao.getNeverAgainRestaurantsFlow()
    }

    // Check if the database has any restaurants
    private suspend fun hasData(): Boolean {
        return restaurantDao.getRestaurantCount() > 0
    }

    // Refresh restaurants from JSON if the database is empty
    suspend fun refreshRestaurants(context: Context) {
        if (!hasData()) {
            val restaurants = loadRestaurantsFromAsset(context)
            restaurantDao.insertAll(restaurants)
        }
    }

    suspend fun pullFreshFromJSON(context: Context) {
        restaurantDao.deleteAll()
        val restaurants = loadRestaurantsFromAsset(context)
        restaurantDao.insertAll(restaurants)
    }

    // Load restaurants from a JSON file in the assets folder
    private fun loadRestaurantsFromAsset(context: Context): List<Restaurant> {
        val inputStream = context.resources.openRawResource(R.raw.restaurants)
        val reader = InputStreamReader(inputStream)
        val restaurantListType = object : TypeToken<List<Restaurant>>() {}.type
        val restaurants: List<Restaurant> = Gson().fromJson(reader, restaurantListType)
        return restaurants
    }

    // Search restaurants by name
    suspend fun searchRestaurantsByName(query: String): List<Restaurant> {
        return getRestaurantsLocal().first().filter { it.name?.contains(query, ignoreCase = true) == true }
    }

    // For updating restaurant status
    suspend fun updateRestaurantStatus(restaurant: Restaurant) {
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
