package com.jrkg.jrkgbites.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jrkg.jrkgbites.data.api.RetrofitInstance
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.model.RestaurantRating
import kotlinx.coroutines.flow.Flow

class RestaurantRatingRepository(
    private val restaurantRatingDao: RestaurantRatingDao
) {

    // REMOTE MANAGEMENT
    suspend fun getRatingsFromRemote() {
        try {
            // Fetch from the API found in RestaurantRatingApiService
            val remoteRatings = RetrofitInstance.ratingApi.getRestaurantRatings()

            // Mapping DTO to local Model
            val localRatings = remoteRatings.map { dto ->
                RestaurantRating(
                    id = dto.id,
                    restaurantId = dto.restaurantId,
                    rating = dto.rating,
                    comment = dto.comment,
                    timestamp = dto.timestamp
                )
            }
            restaurantRatingDao.insertAll(localRatings)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // LOCAL MANAGEMENT
    fun getRatings(): Flow<List<RestaurantRating>> {
        return restaurantRatingDao.getAllRatings()
    }

    fun getRatingForRestaurant(restaurantId: String): Flow<RestaurantRating?> {
        return restaurantRatingDao.getLatestRatingForRestaurant(restaurantId)
    }

    suspend fun insertRating(rating: RestaurantRating) {
        restaurantRatingDao.insert(rating)
    }

}