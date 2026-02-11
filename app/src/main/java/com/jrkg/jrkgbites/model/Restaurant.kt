package com.jrkg.jrkgbites.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "restaurants")
data class Restaurant(
    @PrimaryKey @SerializedName("ID") val id: String, // ID must be non-null for PrimaryKey
    @SerializedName("Name") val name: String?,
    @SerializedName("Category") val category: String?,
    @SerializedName("Cuisine") val cuisine: String?,
    @SerializedName("Level") val level: String?,
    @SerializedName("Location") val location: String?,
    @SerializedName("Lat") val lat: String?,
    @SerializedName("Lng") val lng: String?,
    @SerializedName("logoResourceName") val logoResourceName: String?,
    @SerializedName("tags") val tags: List<String>?,

    var isFavorite: Boolean = false
)
