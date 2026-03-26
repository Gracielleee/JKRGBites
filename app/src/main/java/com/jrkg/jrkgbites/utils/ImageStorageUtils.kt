
package com.jrkg.jrkgbites.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object ImageStorageUtils {

    private const val LOGO_PREFIX = "restaurant_logo_"

    fun generateLogoFileName(restaurantId: String): String {
        return "$LOGO_PREFIX$restaurantId.png"
    }

    /**
     * Tries to find a matching drawable resource for a given restaurant name.
     * It tries several common naming patterns to increase the chance of a match.
     */
    fun getDrawableResourceByName(context: Context, name: String?): Int {
        if (name.isNullOrEmpty()) return 0

        val res = context.resources
        val pkg = context.packageName

        // 1. Try stripping all non-alphanumeric (Original logic)
        // e.g., "Mama Lou's Italian Kitchen" -> "mamalousitaliankitchen"
        val fullyStripped = name.lowercase().replace(Regex("[^a-z0-9]"), "")
        var resId = res.getIdentifier(fullyStripped, "drawable", pkg)
        if (resId != 0) return resId

        // 2. Try just the first word (Common for brand names)
        // e.g., "Mama Lou's Italian Kitchen" -> "mamalous"
        val firstWord = name.split(" ")[0].lowercase().replace(Regex("[^a-z0-9]"), "")
        resId = res.getIdentifier(firstWord, "drawable", pkg)
        if (resId != 0) return resId

        // 3. Try removing common suffixes like "Restaurant", "Cafe", etc.
        val cleaned = name.lowercase()
            .replace("restaurant", "")
            .replace("cafe", "")
            .replace("café", "")
            .replace("kitchen", "")
            .replace("izakaya", "")
            .replace("bar", "")
            .replace("grill", "")
            .replace("bakery", "")
            .replace("bakeshop", "")
            .replace("house of", "")
            .replace("home of", "")
            .replace(Regex("[^a-z0-9 ]"), "")
            .trim()
            .replace(" ", "")

        resId = res.getIdentifier(cleaned, "drawable", pkg)
        if (resId != 0) return resId

        // 4. Specific manual mappings for known mismatches if patterns fail
        val manualMap = mapOf(
            "mantraindiancuisine" to "mantraindiankitchen",
            "marygracecafe" to "marygrace",
            "mamalousitaliankitchen" to "mamalous",
            "gerrysrestaurantbar" to "gerrysgrill",
            "nordbreadhub" to "nordspastry",
            "mangan" to "manganrestaurant",
            "storyakitchenbar" to "storyakitchen",
            "kettlekorn" to "kettlecorn",
            "100gadzparathas" to "gadz100",
            "thematchatokyo" to "thematchatokyo",
            "yellowcabpizzaco" to "yellowcab",
            "cocofreshteajuice" to "coco",
            "buffaloswingsnthings" to "buffalowingsthings",
            "pineapplehillcuisine" to "pineapplehillskitchen"
        )

        val mappedName = manualMap[fullyStripped]
        if (mappedName != null) {
            resId = res.getIdentifier(mappedName, "drawable", pkg)
            if (resId != 0) return resId
        }

        return 0
    }

    fun getLogo(context: Context, restaurantId: String?, restaurantName: String?): Any? {
        // 1. Try to find local image file first (User uploaded)
        val fileName = restaurantId?.let { generateLogoFileName(it) }
        val localLogo = getLocalImageFile(context, fileName)
        if (localLogo != null) return localLogo

        // 2. Fallback to drawable resource using enhanced fuzzy matching
        val resId = getDrawableResourceByName(context, restaurantName)

        return if (resId != 0) resId else null
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