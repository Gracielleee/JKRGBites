package com.jrkg.jrkgbites

import android.os.Bundle
import androidx.`annotation`.CheckResult
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

public class RestaurantDetailsFragmentDirections private constructor() {
  private data class ActionRestaurantDetailsFragmentToUpdateRestaurantFragment(
    public val restaurantId: String,
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_restaurantDetailsFragment_to_updateRestaurantFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("restaurantId", this.restaurantId)
        return result
      }
  }

  public companion object {
    @CheckResult
    public fun actionRestaurantDetailsFragmentToUpdateRestaurantFragment(restaurantId: String): NavDirections = ActionRestaurantDetailsFragmentToUpdateRestaurantFragment(restaurantId)

    @CheckResult
    public fun actionLoginFragmentToNavHome(): NavDirections = NavGraphDirections.actionLoginFragmentToNavHome()

    @CheckResult
    public fun toLoginFragment(): NavDirections = NavGraphDirections.toLoginFragment()

    @CheckResult
    public fun toRegisterFragment(): NavDirections = NavGraphDirections.toRegisterFragment()

    @CheckResult
    public fun toForgotPasswordDialog(): NavDirections = NavGraphDirections.toForgotPasswordDialog()

    @CheckResult
    public fun toRestaurantDetailsFragment(restaurantId: String): NavDirections = NavGraphDirections.toRestaurantDetailsFragment(restaurantId)

    @CheckResult
    public fun toAddRestaurant(): NavDirections = NavGraphDirections.toAddRestaurant()
  }
}
