package com.jrkg.jrkgbites.domain

import android.content.Context
import android.graphics.Bitmap
import com.jrkg.jrkgbites.data.RestaurantDao
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.model.RestaurantRating
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import io.viascom.nanoid.NanoId
import java.io.File

class RestaurantManager(
    private val restaurantDao: RestaurantDao
) {

    suspend fun addRestaurant(name: String,
                              category: String,
                              cuisine: String,
                              level: String,
                              location: String,
                              lat: String,
                              lng: String,
                              photoBitmap: Bitmap,
                              tags: List<String>,
                              context: Context) {

        val newRestaurantId = generateStringId()
        val logoResourceName = "restaurant_${newRestaurantId}.jpg"
        savePhotoToInternalStorage(context, photoBitmap, logoResourceName)

        val newRestaurant = Restaurant(newRestaurantId,
                                        name,
                                        category,
                                        cuisine,
                                        level,
                                        location,
                                        lat,
                                        lng,
                                        logoResourceName,
                                        tags
        )
        restaurantDao.insert(newRestaurant)
    }

    private fun generateStringId(): String {
        return NanoId.generate()
    }

    private fun savePhotoToInternalStorage(context: Context, bitmap: Bitmap, filename: String) {
        val file = File(context.filesDir, filename)
        file.outputStream().use { stream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
        }
    }

}