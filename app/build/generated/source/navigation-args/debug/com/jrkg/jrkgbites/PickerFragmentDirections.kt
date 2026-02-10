package com.jrkg.jrkgbites

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Int

public class PickerFragmentDirections private constructor() {
  private data class ActionNavPickerToRestaurantDetailsFragment(
    public val restaurantId: Int,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_nav_picker_to_restaurantDetailsFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("restaurantId", this.restaurantId)
        return result
      }
  }

  public companion object {
    public fun actionNavPickerToRestaurantDetailsFragment(restaurantId: Int): NavDirections =
        ActionNavPickerToRestaurantDetailsFragment(restaurantId)

    public fun actionLoginFragmentToNavHome(): NavDirections =
        NavGraphDirections.actionLoginFragmentToNavHome()

    public fun toLoginFragment(): NavDirections = NavGraphDirections.toLoginFragment()

    public fun toRegisterFragment(): NavDirections = NavGraphDirections.toRegisterFragment()

    public fun toForgotPasswordDialog(): NavDirections = NavGraphDirections.toForgotPasswordDialog()
  }
}
