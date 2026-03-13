package com.jrkg.jrkgbites.data.api
import retrofit2.http.*

interface RestaurantRatingApiService {

    @GET("restaurants/ratings")
    suspend fun getRestaurantRatings(): List<RestaurantRatingDto>

    @POST("restaurants/ratings")
    suspend fun createRestaurantRating(@Body rating: RestaurantRatingDto): RestaurantRatingDto

    @POST("restaurants/ratings")
    suspend fun createRestaurantRatings(@Body ratings: List<RestaurantRatingDto>): List<RestaurantRatingDto>

    @DELETE("restaurants/ratings/{id}")
    suspend fun deleteRestaurantRating(@Path("id") id: String): Unit

//    @DELETE("restaurants/ratings/")
//    suspend fun deleteRestaurantRatings(@Query "WHERE userId == `userId`", userId: String): Unit
}