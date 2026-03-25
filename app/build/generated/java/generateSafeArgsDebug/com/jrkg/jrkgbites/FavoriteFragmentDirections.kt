package com.jrkg.jrkgbites

import androidx.`annotation`.CheckResult
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kotlin.String

public class FavoriteFragmentDirections private constructor() {
  public companion object {
    @CheckResult
    public fun actionFavoriteFragmentToRouletteFragment(): NavDirections = ActionOnlyNavDirections(R.id.action_favoriteFragment_to_rouletteFragment)

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
