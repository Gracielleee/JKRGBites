package com.jrkg.jrkgbites.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.model.RestaurantRating
import com.jrkg.jrkgbites.model.FavoriteRestaurantId
import com.jrkg.jrkgbites.model.NeverAgainRestaurantId
import com.jrkg.jrkgbites.utils.Converters

@Database(
    entities = [
        Restaurant::class,
        RestaurantRating::class,
        FavoriteRestaurantId::class,
        NeverAgainRestaurantId::class
    ],
    version = 5,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao
    abstract fun restaurantRatingDao(): RestaurantRatingDao
    abstract fun favoriteRestaurantDao(): FavoriteRestaurantDao
    abstract fun neverAgainRestaurantDao(): NeverAgainRestaurantDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: android.content.Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
