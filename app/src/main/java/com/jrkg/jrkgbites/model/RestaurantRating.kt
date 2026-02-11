package com.jrkg.jrkgbites.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant_ratings")
data class RestaurantRating(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Auto-generated primary key
    val restaurantId: String,
    val rating: Int,
    val comment: String,
    val timestamp: Long = 0L // Will be set programmatically, not as default
)