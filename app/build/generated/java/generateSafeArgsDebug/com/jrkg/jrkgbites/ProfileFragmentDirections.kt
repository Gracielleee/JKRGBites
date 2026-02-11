package com.jrkg.jrkgbites

import androidx.`annotation`.CheckResult
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class ProfileFragmentDirections private constructor() {
  public companion object {
    @CheckResult
    public fun actionNavProfileToRestaurantRatingFragment(): NavDirections = ActionOnlyNavDirections(R.id.action_nav_profile_to_restaurantRatingFragment)

    @CheckResult
    public fun actionLoginFragmentToNavHome(): NavDirections = NavGraphDirections.actionLoginFragmentToNavHome()

    @CheckResult
    public fun toLoginFragment(): NavDirections = NavGraphDirections.toLoginFragment()

    @CheckResult
    public fun toRegisterFragment(): NavDirections = NavGraphDirections.toRegisterFragment()

    @CheckResult
    public fun toForgotPasswordDialog(): NavDirections = NavGraphDirections.toForgotPasswordDialog()
  }
}
