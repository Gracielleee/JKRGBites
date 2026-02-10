package com.jrkg.jrkgbites.services

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

/**
 * An enum to represent the availability of biometric authentication on the device.
 */
enum class BiometricAuthStatus {
    READY,
    NOT_AVAILABLE,
    NO_FINGERPRINT_ENROLLED
}

/**
 * A service class to handle all interactions with the Android Biometric/Fingerprint APIs.
 */
class BiometricService(private val context: Context) {

    /**
     * Checks the device for biometric capabilities, focusing on strong methods like fingerprint.
     * @return A [BiometricAuthStatus] indicating if authentication is ready to be used.
     */
    fun getAuthStatus(): BiometricAuthStatus {
        val biometricManager = BiometricManager.from(context)
        
        // BIOMETRIC_STRONG is the modern standard, primarily representing fingerprint scanners.
        val authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG

        return when (biometricManager.canAuthenticate(authenticators)) {
            BiometricManager.BIOMETRIC_SUCCESS -> BiometricAuthStatus.READY
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> BiometricAuthStatus.NOT_AVAILABLE
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> BiometricAuthStatus.NOT_AVAILABLE
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> BiometricAuthStatus.NO_FINGERPRINT_ENROLLED
            else -> BiometricAuthStatus.NOT_AVAILABLE
        }
    }

    /**
     * Displays the fingerprint authentication prompt to the user.
     *
     * @param activity The activity that is hosting the prompt.
     * @param onSuccess A callback function to be invoked upon successful authentication.
     * @param onError A callback function for when an unrecoverable error occurs.
     * @param onFailed A callback for when the fingerprint is not recognized.
     */
    fun authenticate(
        activity: FragmentActivity,
        onSuccess: () -> Unit,
        onError: (errorCode: Int, errString: CharSequence) -> Unit,
        onFailed: () -> Unit
    ) {
        val executor = ContextCompat.getMainExecutor(context)
        val biometricPrompt = BiometricPrompt(activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    // Called when an unrecoverable error has occurred, e.g., user cancels.
                    onError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    // Called when the fingerprint is recognized.
                    onSuccess()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    // Called when a fingerprint is presented but not recognized.
                    onFailed()
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Fingerprint Authentication")
            .setSubtitle("Log in using your fingerprint")
            .setNegativeButtonText("Use account password")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}
