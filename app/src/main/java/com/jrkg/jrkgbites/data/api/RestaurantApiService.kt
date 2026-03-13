package com.jrkg.jrkgbites.data.api
import com.jrkg.jrkgbites.model.Restaurant
import retrofit2.http.*

interface RestaurantApiService {

    @GET("restaurants")
    suspend fun getRestaurants(): List<RestaurantDto>

    @GET("restaurants/{id}")
    suspend fun getRestaurant(@Path("id") id: String): Restaurant

    @POST("restaurants")
    suspend fun createRestaurant(@Body restaurant: RestaurantDto): RestaurantDto

    @DELETE("restaurants/{id}")
    suspend fun deleteRestaurant(@Path("id") id: String): Unit


    // ----- Favorites ------
    @GET("restaurants/favorites")
    suspend fun getFavoriteRestaurants(): List<FavoriteRestaurantDto>

    @POST("restaurants/favorite")
    suspend fun addFavorite(@Body restaurant: FavoriteRestaurantDto): FavoriteRestaurantDto

    @DELETE("restaurants/favorite/{id}")
    suspend fun removeFavorite(@Path("id") id: String): Unit



    // ----- Never Again ------
    @GET("restaurants/neveragains")
    suspend fun getNeverAgainRestaurants(): List<NeverAgainRestaurantDto>

    @POST("restaurants/neveragain")
    suspend fun addNeverAgain(@Body restaurant: NeverAgainRestaurantDto): NeverAgainRestaurantDto

    @DELETE("restaurants/neveragain/{id}")
    suspend fun removeNeverAgain(@Path("id") id: String): Unit

}