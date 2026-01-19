package com.jrkg.jrkgbites.data

import android.content.Context
import android.content.SharedPreferences

/**
 * Manages saving and retrieving user-specific preferences using SharedPreferences.
 */
class UserPreferencesManager(context: Context) {

    companion object {
        private const val PREFERENCES_FILE_NAME = "JRKGBitesUserPrefs"
        private const val KEY_BIOMETRIC_AUTH_ENABLED = "biometricAuthEnabled"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)

    /**
     * Saves the user's choice for enabling or disabling biometric authentication.
     *
     * @param isEnabled True to enable biometric auth, false to disable.
     */
    fun setBiometricAuthEnabled(isEnabled: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(KEY_BIOMETRIC_AUTH_ENABLED, isEnabled)
            apply()
        }
    }

    /**
     * Retrieves the user's saved preference for using biometric authentication.
     *
     * @return True if the user has enabled biometric auth, false otherwise. Defaults to false.
     */
    fun isBiometricAuthEnabled(): Boolean {
        return sharedPreferences.getBoolean(KEY_BIOMETRIC_AUTH_ENABLED, false)
    }
}
