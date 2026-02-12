package com.jrkg.jrkgbites.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jrkg.jrkgbites.R
import com.jrkg.jrkgbites.data.RestaurantDao
import com.jrkg.jrkgbites.model.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.io.InputStreamReader

class RestaurantRepository(private val restaurantDao: RestaurantDao) {

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