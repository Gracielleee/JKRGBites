package com.jrkg.jrkgbites
import com.jrkg.jrkgbites.data.api.RestaurantApiService
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jrkg.jrkgbites.data.api.RestaurantDto
import com.jrkg.jrkgbites.data.api.UserApiService
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class RestaurantApiInstrumentedTest {
    private lateinit var userApi: UserApiService

    @Before
    fun setup() {
        userApi = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)
    }

    @Test
    fun testGetUsers() = runBlocking {
        val response = userApi.getUsers()
        assertNotNull(response)
        assertTrue(response.isNotEmpty())
    }

    @Test
    fun testGetUserById() = runBlocking {
        val userId = "1"
        val response = userApi.getUserById(userId)
        assertNotNull(response)
        assertTrue(response.id == userId)
    }

}
