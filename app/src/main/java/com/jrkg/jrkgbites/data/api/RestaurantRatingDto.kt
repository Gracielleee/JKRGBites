package com.jrkg.jrkgbites.data.api

data class RestaurantRatingDto(
    val userId: String,
    val id: Int,
    val restaurantId: String,
    val rating: Int,
    val comment: String,
    val timestamp: Long
)

