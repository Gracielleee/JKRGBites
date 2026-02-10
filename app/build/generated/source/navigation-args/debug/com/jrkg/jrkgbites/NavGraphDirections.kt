package com.jrkg.jrkgbites

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class NavGraphDirections private constructor() {
  public companion object {
    public fun actionLoginFragmentToNavHome(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_nav_home)

    public fun toLoginFragment(): NavDirections = ActionOnlyNavDirections(R.id.to_loginFragment)

    public fun toRegisterFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.to_registerFragment)

    public fun toForgotPasswordDialog(): NavDirections =
        ActionOnlyNavDirections(R.id.to_forgotPasswordDialog)
  }
}
