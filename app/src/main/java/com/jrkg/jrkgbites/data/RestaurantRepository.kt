package com.jrkg.jrkgbites.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jrkg.jrkgbites.model.Restaurant
import java.io.IOException

class RestaurantRepository(private val context: Context) {

    fun getRestaurants(): List<Restaurant> {
        val jsonString: String
        try {
            jsonString = context.assets.open("JRKGBites.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val listRestaurantType = object : TypeToken<List<Restaurant>>() {}.type
        return Gson().fromJson(jsonString, listRestaurantType)
    }
}
