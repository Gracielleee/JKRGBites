package com.jrkg.jrkgbites.model

import com.google.gson.annotations.SerializedName

data class Restaurant(
    val id: String,
    val name: String,
    val category: String,
    val cuisine: String,
    val location: Location,
    val rating: Double,
    @SerializedName("imageUrl") val imageUrl: String,
    val description: String
)

data class Location(
    val mall: String,
    val lat: Double,
    val lng: Double,
    val address: String
)
