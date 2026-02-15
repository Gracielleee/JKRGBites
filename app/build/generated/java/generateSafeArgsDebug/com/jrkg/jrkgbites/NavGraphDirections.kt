package com.jrkg.jrkgbites

import android.os.Bundle
import androidx.`annotation`.CheckResult
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

public class NavGraphDirections private constructor() {
  private data class ToRestaurantDetailsFragment(
    public val restaurantId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.to_restaurantDetailsFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("restaurantId", this.restaurantId)
        return result
      }
  }

  public companion object {
    @CheckResult
    public fun actionLoginFragmentToNavHome(): NavDirections = ActionOnlyNavDirections(R.id.action_loginFragment_to_nav_home)

    @CheckResult
    public fun toLoginFragment(): NavDirections = ActionOnlyNavDirections(R.id.to_loginFragment)

    @CheckResult
    public fun toRegisterFragment(): NavDirections = ActionOnlyNavDirections(R.id.to_registerFragment)

    @CheckResult
    public fun toForgotPasswordDialog(): NavDirections = ActionOnlyNavDirections(R.id.to_forgotPasswordDialog)

    @CheckResult
    public fun toRestaurantDetailsFragment(restaurantId: String): NavDirections = ToRestaurantDetailsFragment(restaurantId)
  }
}
