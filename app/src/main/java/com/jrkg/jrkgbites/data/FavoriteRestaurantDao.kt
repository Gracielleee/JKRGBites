package com.jrkg.jrkgbites.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jrkg.jrkgbites.model.FavoriteRestaurantId
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteRestaurant: FavoriteRestaurantId)

    @Query("SELECT favorite_restaurant FROM favorite_restaurants")
    fun getAllFavoriteRestaurantIdsFlow(): Flow<List<String>>

    @Query("SELECT favorite_restaurant FROM favorite_restaurants")
    suspend fun getAllFavoriteRestaurantIds(): List<String>

    @Delete
    suspend fun delete(favoriteRestaurant: FavoriteRestaurantId)

    @Query("DELETE FROM favorite_restaurants WHERE favorite_restaurant = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM favorite_restaurants")
    suspend fun deleteAll()
}
