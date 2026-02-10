package com.jrkg.jrkgbites

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class RestaurantDetailsFragmentArgs(
  public val restaurantId: Int,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("restaurantId", this.restaurantId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("restaurantId", this.restaurantId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): RestaurantDetailsFragmentArgs {
      bundle.setClassLoader(RestaurantDetailsFragmentArgs::class.java.classLoader)
      val __restaurantId : Int
      if (bundle.containsKey("restaurantId")) {
        __restaurantId = bundle.getInt("restaurantId")
      } else {
        throw IllegalArgumentException("Required argument \"restaurantId\" is missing and does not have an android:defaultValue")
      }
      return RestaurantDetailsFragmentArgs(__restaurantId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        RestaurantDetailsFragmentArgs {
      val __restaurantId : Int?
      if (savedStateHandle.contains("restaurantId")) {
        __restaurantId = savedStateHandle["restaurantId"]
        if (__restaurantId == null) {
          throw IllegalArgumentException("Argument \"restaurantId\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"restaurantId\" is missing and does not have an android:defaultValue")
      }
      return RestaurantDetailsFragmentArgs(__restaurantId)
    }
  }
}
