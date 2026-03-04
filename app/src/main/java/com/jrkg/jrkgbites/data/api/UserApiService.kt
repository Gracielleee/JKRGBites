package com.jrkg.jrkgbites.data.api
import retrofit2.http.*

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDto>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: String): UserDto
}
