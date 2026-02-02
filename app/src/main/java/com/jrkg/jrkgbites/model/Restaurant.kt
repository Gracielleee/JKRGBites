package com.jrkg.jrkgbites.model

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("ID") val id: String?,
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