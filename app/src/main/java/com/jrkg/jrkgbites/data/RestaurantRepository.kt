package com.jrkg.jrkgbites.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jrkg.jrkgbites.R
// Added these imports for the new API connection
import com.jrkg.jrkgbites.data.api.RetrofitInstance
import com.jrkg.jrkgbites.model.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.io.InputStreamReader

class RestaurantRepository(private val restaurantDao: RestaurantDao) {

    // --- New Function for Task A: Remote Database Sync ---
    suspend fun syncWithRemote() {
        try {
            // Fetch from the API found in RestaurantApiService
            val remoteRestaurants = RetrofitInstance.restaurantApi.getRestaurants()

            // Mapping DTO to your local Model
            val localRestaurants = remoteRestaurants.map { dto ->
                Restaurant(
                    id = dto.id,
                    name = dto.name,
                    category = dto.category,
                    cuisine = dto.cuisine,
                    level = dto.level,
                    location = dto.location,
                    lat = dto.lat,
                    lng = dto.lng,
                    logoResourceName = dto.logoResourceName,
                    tags = dto.tags,
                    isFavorite = false,
                    isNeverAgain = false
                )
            }
            restaurantDao.insertAll(localRestaurants)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Get restaurants from the database
    fun getRestaurants(): Flow<List<Restaurant>> {
        return restaurantDao.getAllRestaurants()
    }

    // Get a specific restaurant by its ID
    fun getRestaurantById(id: String): Flow<Restaurant?> {
        return restaurantDao.getRestaurantById(id)
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
        // Task A: After checking local, sync with cloud data
        syncWithRemote()
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
        return getRestaurants().first().filter { it.name?.contains(query, ignoreCase = true) == true }
    }

    // For updating restaurant status (Favorite, Never Again, etc.)
    suspend fun updateRestaurantStatus(restaurant: Restaurant) {
        restaurantDao.update(restaurant)
    }
}