package com.jrkg.jrkgbites

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jrkg.jrkgbites.data.RestaurantDao
import com.jrkg.jrkgbites.data.RestaurantRatingDao
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.model.RestaurantRating
import com.jrkg.jrkgbites.utils.Converters

@Database(entities = [Restaurant::class, RestaurantRating::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao
    abstract fun restaurantRatingDao(): RestaurantRatingDao

    // Singleton pattern to ensure only one instance of the database is created
    companion object {
        @Volatile // Make sure this instance is always up-to-date
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: android.content.Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}