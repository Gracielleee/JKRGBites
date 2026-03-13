
package com.jrkg.jrkgbites.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object ImageStorageUtils {

    private const val LOGO_PREFIX = "restaurant_logo_"

    fun generateLogoFileName(restaurantId: String): String {
        return "$LOGO_PREFIX$restaurantId.png"
    }

    fun generateDrawableLogoName(restaurantName: String): String {
        // Automatically generate logoResourceName from name
        // Matches "Ajisen Ramen" to "ajisenramen"
        val generatedLogoName = restaurantName?.lowercase()
            ?.replace(Regex("[^a-z0-9]"), "") ?: ""

        return generatedLogoName
    }

    fun getLogo(context: Context, restaurantId: String?, restaurantName: String?): Any? {
        // 1. Try to find local image file first
        val fileName = restaurantId?.let { generateLogoFileName(it) }
        val localLogo = getLocalImageFile(context, fileName)
        if (localLogo != null)
            return localLogo

        // 2. Fallback to drawable resource
        val resName = restaurantName?.let { generateDrawableLogoName(it) }
        val resId = if (!resName.isNullOrEmpty()) {
            context.resources.getIdentifier(resName, "drawable", context.packageName)
        } else 0

        return if (resId != 0) resId
            else null
    }
    fun saveImageToInternalStorage(context: Context, uri: Uri, restaurantId: String): String? {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream) ?: return null

        val fileName = generateLogoFileName(restaurantId)
        val file = File(context.filesDir, fileName)

        return try {
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.close()
            fileName
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getLocalImageFile(context: Context, fileName: String?): File? {
        if (fileName == null) return null
        val file = File(context.filesDir, fileName)
        return if (file.exists()) file else null
    }
}