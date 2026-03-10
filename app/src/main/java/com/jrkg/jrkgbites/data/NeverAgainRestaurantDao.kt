package com.jrkg.jrkgbites.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jrkg.jrkgbites.model.FavoriteRestaurantId
import com.jrkg.jrkgbites.model.NeverAgainRestaurantId
import kotlinx.coroutines.flow.Flow

@Dao
interface NeverAgainRestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(neverAgainRestaurant: NeverAgainRestaurantId)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(neverAgainRestaurants: List<NeverAgainRestaurantId>)

    @Query("SELECT never_again_restaurant FROM never_again_restaurants")
    fun getAllNeverAgainRestaurantIdsFlow(): Flow<List<String>>

    @Query("SELECT never_again_restaurant FROM never_again_restaurants")
    suspend fun getAllNeverAgainRestaurantIds(): List<String>

    @Query("SELECT EXISTS(SELECT 1 FROM never_again_restaurants WHERE never_again_restaurant = :restaurantId)")
    suspend fun isNeverAgain(restaurantId: String): Boolean

    @Delete
    suspend fun delete(neverAgainRestaurant: NeverAgainRestaurantId)

    @Query("DELETE FROM never_again_restaurants WHERE never_again_restaurant = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM never_again_restaurants")
    suspend fun deleteAll()
}
