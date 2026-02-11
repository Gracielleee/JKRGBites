package com.jrkg.jrkgbites

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class RestaurantDetailsFragmentArgs(
  public val restaurantId: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("restaurantId", this.restaurantId)
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
      val __restaurantId : String?
      if (bundle.containsKey("restaurantId")) {
        __restaurantId = bundle.getString("restaurantId")
        if (__restaurantId == null) {
          throw IllegalArgumentException("Argument \"restaurantId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"restaurantId\" is missing and does not have an android:defaultValue")
      }
      return RestaurantDetailsFragmentArgs(__restaurantId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        RestaurantDetailsFragmentArgs {
      val __restaurantId : String?
      if (savedStateHandle.contains("restaurantId")) {
        __restaurantId = savedStateHandle["restaurantId"]
        if (__restaurantId == null) {
          throw IllegalArgumentException("Argument \"restaurantId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"restaurantId\" is missing and does not have an android:defaultValue")
      }
      return RestaurantDetailsFragmentArgs(__restaurantId)
    }
  }
}
