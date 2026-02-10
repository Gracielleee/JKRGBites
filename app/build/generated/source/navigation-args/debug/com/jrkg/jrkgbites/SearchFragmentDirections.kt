package com.jrkg.jrkgbites

import androidx.navigation.NavDirections

public class SearchFragmentDirections private constructor() {
  public companion object {
    public fun actionLoginFragmentToNavHome(): NavDirections =
        NavGraphDirections.actionLoginFragmentToNavHome()

    public fun toLoginFragment(): NavDirections = NavGraphDirections.toLoginFragment()

    public fun toRegisterFragment(): NavDirections = NavGraphDirections.toRegisterFragment()

    public fun toForgotPasswordDialog(): NavDirections = NavGraphDirections.toForgotPasswordDialog()
  }
}
