package com.jrkg.jrkgbites

import android.os.Bundle
import androidx.`annotation`.CheckResult
import androidx.navigation.NavDirections
import kotlin.Boolean
import kotlin.Int
import kotlin.String

public class FavoriteFragmentDirections private constructor() {
  private data class ActionFavoriteFragmentToRouletteFragment(
    public val shouldSpin: Boolean = false,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_favoriteFragment_to_rouletteFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putBoolean("shouldSpin", this.shouldSpin)
        return result
      }
  }

  public companion object {
    @CheckResult
    public fun actionFavoriteFragmentToRouletteFragment(shouldSpin: Boolean = false): NavDirections = ActionFavoriteFragmentToRouletteFragment(shouldSpin)

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
