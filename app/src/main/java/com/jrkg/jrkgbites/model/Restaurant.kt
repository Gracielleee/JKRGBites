package com.jrkg.jrkgbites.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "restaurants")
data class Restaurant(
    @PrimaryKey @SerializedName("ID") val id: Int, // ID must be non-null for PrimaryKey
    @SerializedName("Name") val name: String?,
    @SerializedName("Category") val category: String?,
    @SerializedName("Cuisine") val cuisine: String?,
    @SerializedName("Level") val level: String?,
    @SerializedName("Lat") val lat: String?,
    @SerializedName("Lng") val lng: String?,
    @SerializedName("tags") val tags: List<String>?,

    // This is the key for your UI to work!
    var isFavorite: Boolean = false
)