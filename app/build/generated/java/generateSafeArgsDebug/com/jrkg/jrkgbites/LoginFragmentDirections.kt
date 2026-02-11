package com.jrkg.jrkgbites

import androidx.`annotation`.CheckResult
import androidx.navigation.NavDirections

public class LoginFragmentDirections private constructor() {
  public companion object {
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
