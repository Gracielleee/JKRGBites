package com.jrkg.jrkgbites.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jrkg.jrkgbites.R
import com.jrkg.jrkgbites.data.api.RetrofitInstance
import com.jrkg.jrkgbites.model.FavoriteRestaurantId
import com.jrkg.jrkgbites.model.NeverAgainRestaurantId
import com.jrkg.jrkgbites.model.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.io.InputStreamReader

class RestaurantRepository(
    private val restaurantDao: RestaurantDao,
    private val favoriteRestaurantDao: FavoriteRestaurantDao,
    private val neverAgainRestaurantDao: NeverAgainRestaurantDao,
) {

  //REMOTE MANAGEMENT
    suspend fun getRestaurantsFromRemote() {
        try {
            // Fetch from the API found in RestaurantApiService
            val remoteRestaurants = RetrofitInstance.restaurantApi.getRestaurants()

            // Mapping DTO to local Model
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
                    addedBy = dto.addedBy,
                    isPublic = dto.isPublic
                )
            }
            restaurantDao.insertAll(localRestaurants)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getFavoritesFromRemote() {
        try {
            // Fetch from the API found in RestaurantApiService
            val remoteRestaurants = RetrofitInstance.restaurantApi.getFavoriteRestaurants()

            // Mapping DTO to local Model
            val localRestaurants = remoteRestaurants.map { dto ->
                FavoriteRestaurantId(
                    favoriteRestaurantId = dto.restaurantId
                )
            }
            favoriteRestaurantDao.insertAll(localRestaurants)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getNeverAgainFromRemote() {
        try {
            // Fetch from the API found in RestaurantApiService
            val remoteRestaurants = RetrofitInstance.restaurantApi.getNeverAgainRestaurants()

            // Mapping DTO to local Model
            val localRestaurants = remoteRestaurants.map { dto ->
                NeverAgainRestaurantId(
                    neverAgainRestaurantId = dto.restaurantId
                )
            }
            neverAgainRestaurantDao.insertAll(localRestaurants)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

 //LOCAL MANAGEMENT
    // Get restaurants from the database
    fun getRestaurants(): Flow<List<Restaurant>> {
        return restaurantDao.getAllRestaurants()
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

    suspend fun fullFreshFromJSON(context: Context) {
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
        return getRestaurants().first().filter { it.name?.contains(query, ignoreCase = true) == true }
    }

    // For updating restaurant status
    suspend fun updateRestaurantStatus(restaurant: Restaurant) {
        restaurantDao.update(restaurant)
    }

    suspend fun addToFavorites(restaurantId: String) {
        favoriteRestaurantDao.insert(FavoriteRestaurantId(restaurantId))
    }

    suspend fun removeFromFavorites(restaurantId: String) {
        favoriteRestaurantDao.deleteById(restaurantId)
    }

    suspend fun addToNeverAgain(restaurantId: String) {
        neverAgainRestaurantDao.insert(NeverAgainRestaurantId(restaurantId))
    }

    suspend fun removeFromNeverAgain(restaurantId: String) {
        neverAgainRestaurantDao.deleteById(restaurantId)
    }

    suspend fun isFavorited(restaurantId: String): Boolean {
        return favoriteRestaurantDao.isFavorited(restaurantId)
    }

    suspend fun isNeverAgain(restaurantId: String): Boolean {
        return neverAgainRestaurantDao.isNeverAgain(restaurantId)
    }



}