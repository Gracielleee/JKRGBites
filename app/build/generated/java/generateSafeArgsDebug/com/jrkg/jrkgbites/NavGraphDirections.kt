package com.jrkg.jrkgbites

import androidx.`annotation`.CheckResult
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class NavGraphDirections private constructor() {
  public companion object {
    @CheckResult
    public fun actionLoginFragmentToNavHome(): NavDirections = ActionOnlyNavDirections(R.id.action_loginFragment_to_nav_home)

    @CheckResult
    public fun toLoginFragment(): NavDirections = ActionOnlyNavDirections(R.id.to_loginFragment)

    @CheckResult
    public fun toRegisterFragment(): NavDirections = ActionOnlyNavDirections(R.id.to_registerFragment)

    @CheckResult
    public fun toForgotPasswordDialog(): NavDirections = ActionOnlyNavDirections(R.id.to_forgotPasswordDialog)
  }
}
