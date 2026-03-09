package com.jrkg.jrkgbites.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_restaurants")
data class FavoriteRestaurantId(
    @PrimaryKey
    @ColumnInfo(name = "favorite_restaurant")
    @SerializedName("favorite_restaurant") val favoriteRestaurantId: String
)
