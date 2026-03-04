package com.jrkg.jrkgbites.data.api

data class RestaurantDto(
    val id: String,
    val addedBy: String, //userId
    val public: Boolean,

    val name: String,
    val category: String,
    val cuisine: String,
    val level: String,
    val location: String,
    val lat: String,
    val lng: String,
    val logoResourceName: String,
    val tags: List<String>
)