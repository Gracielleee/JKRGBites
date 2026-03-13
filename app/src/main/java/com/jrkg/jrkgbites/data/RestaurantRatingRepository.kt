package com.jrkg.jrkgbites.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jrkg.jrkgbites.data.api.RestaurantRatingDto
import com.jrkg.jrkgbites.data.api.RetrofitInstance
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.model.RestaurantRating
import com.jrkg.jrkgbites.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RestaurantRatingRepository(
    private val restaurantRatingDao: RestaurantRatingDao,
//    private val user: User
) {

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