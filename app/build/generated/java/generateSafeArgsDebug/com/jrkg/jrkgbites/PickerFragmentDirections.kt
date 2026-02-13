package com.jrkg.jrkgbites

import android.os.Bundle
import androidx.`annotation`.CheckResult
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

public class PickerFragmentDirections private constructor() {
  private data class ActionNavPickerToRestaurantDetailsFragment(
    public val restaurantId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_nav_picker_to_restaurantDetailsFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("restaurantId", this.restaurantId)
        return result
      }
  }

  public companion object {
    @CheckResult
    public fun actionNavPickerToRestaurantDetailsFragment(restaurantId: String): NavDirections = ActionNavPickerToRestaurantDetailsFragment(restaurantId)

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
  }
}
