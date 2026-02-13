package com.jrkg.jrkgbites

import androidx.`annotation`.CheckResult
import androidx.navigation.NavDirections
import kotlin.String

public class HomeFragmentDirections private constructor() {
  public companion object {
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
