package com.jrkg.jrkgbites.domain

import com.jrkg.jrkgbites.data.UserPreferencesManager
import com.jrkg.jrkgbites.services.BiometricAuthStatus
import com.jrkg.jrkgbites.services.BiometricService

/**
 * Represents the required authentication method upon app start.
 */
enum class AuthMethod {
    BIOMETRIC,
    PASSWORD
}

/**
 * Manages the business logic for making authentication decisions.
 */
class AuthManager(
    private val biometricService: BiometricService,
    private val userPreferencesManager: UserPreferencesManager
) {

    /**
     * Determines which authentication method should be used based on device capabilities
     * and the user's saved preferences.
     *
     * @return The [AuthMethod] that the app should use.
     */
    fun getRequiredAuthMethod(): AuthMethod {
        val isUserPreferenceEnabled = userPreferencesManager.isBiometricAuthEnabled()
        val deviceAuthStatus = biometricService.getAuthStatus()

        // To use biometrics, the user must have enabled the preference AND
        // the device hardware must be fully ready.
        if (isUserPreferenceEnabled && deviceAuthStatus == BiometricAuthStatus.READY) {
            return AuthMethod.BIOMETRIC
        }

        // In all other cases (preference is off, no hardware, or no fingerprint enrolled),
        // the app must fall back to password authentication.
        return AuthMethod.PASSWORD
    }
}
