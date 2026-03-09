package com.jrkg.jrkgbites.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "never_again_restaurants")
data class NeverAgainRestaurantId(
    @PrimaryKey
    @ColumnInfo(name = "never_again_restaurant")
    @SerializedName("neverAgainRestaurantId") val neverAgainRestaurantId: String
)