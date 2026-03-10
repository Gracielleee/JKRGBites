package com.jrkg.jrkgbites.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jrkg.jrkgbites.model.RestaurantRating
import kotlinx.coroutines.flow.Flow

class RestaurantRatingRepository(
    private val restaurantRatingDao: RestaurantRatingDao
) {
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