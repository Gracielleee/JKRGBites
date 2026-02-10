package com.jrkg.jrkgbites

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class ProfileFragmentDirections private constructor() {
  public companion object {
    public fun actionNavProfileToRestaurantRatingFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_nav_profile_to_restaurantRatingFragment)

    public fun actionLoginFragmentToNavHome(): NavDirections =
        NavGraphDirections.actionLoginFragmentToNavHome()

    public fun toLoginFragment(): NavDirections = NavGraphDirections.toLoginFragment()

    public fun toRegisterFragment(): NavDirections = NavGraphDirections.toRegisterFragment()

    public fun toForgotPasswordDialog(): NavDirections = NavGraphDirections.toForgotPasswordDialog()
  }
}
