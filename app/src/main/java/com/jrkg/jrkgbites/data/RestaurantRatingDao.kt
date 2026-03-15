package com.jrkg.jrkgbites.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jrkg.jrkgbites.model.NeverAgainRestaurantId
import com.jrkg.jrkgbites.model.RestaurantRating
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantRatingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rating: RestaurantRating)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(ratings: List<RestaurantRating>)

    @Update
    suspend fun update(rating: RestaurantRating)

    @Delete
    suspend fun delete(rating: RestaurantRating)

    @Query("DELETE FROM restaurant_ratings")
    suspend fun deleteAll()

    @Query("SELECT * FROM restaurant_ratings")
    fun getAllRatings(): Flow<List<RestaurantRating>>

    @Query("SELECT * FROM restaurant_ratings WHERE restaurantId = :restaurantId ORDER BY timestamp DESC LIMIT 1")
    fun getLatestRatingForRestaurant(restaurantId: String): Flow<RestaurantRating?>

    suspend fun clearAndInsert(ratings: List<RestaurantRating>) {
        deleteAll()
        insertAll(ratings)
    }
}