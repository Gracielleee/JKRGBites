package com.jrkg.jrkgbites.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.jvm.JvmStatic

object Converters {
    private val gson = Gson()
    
    @TypeConverter
    @JvmStatic
    fun fromStringList(list: List<String>?): String? {
        return list?.let { gson.toJson(it) }
    }

    @TypeConverter
    @JvmStatic
    fun toStringList(json: String?): List<String>? {
        return json?.let {
            gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
        }
    }
}