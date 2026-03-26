package com.jrkg.jrkgbites.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Manages saving and retrieving user-specific preferences using SharedPreferences.
 */
class UserPreferencesManager(context: Context) {

    companion object {
        private const val PREFERENCES_FILE_NAME = "JRKGBitesUserPrefs"
        private const val KEY_BIOMETRIC_AUTH_ENABLED = "biometricAuthEnabled"
        private const val KEY_KEEP_LOGGED_IN = "keepLoggedIn"
        private const val KEY_PROXIMITY_FILTER_ENABLED = "proximityFilterEnabled"
        private const val KEY_LAST_LAT = "lastLat"
        private const val KEY_LAST_LNG = "lastLng"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)

    // Flows for reactive updates
    private val _isProximityFilterEnabled = MutableStateFlow(
        sharedPreferences.getBoolean(KEY_PROXIMITY_FILTER_ENABLED, false)
    )
    val isProximityFilterEnabledFlow: StateFlow<Boolean> = _isProximityFilterEnabled.asStateFlow()

    private val _lastLat = MutableStateFlow(
        sharedPreferences.getFloat(KEY_LAST_LAT, 0f).toDouble()
    )
    val lastLatFlow: StateFlow<Double> = _lastLat.asStateFlow()

    private val _lastLng = MutableStateFlow(
        sharedPreferences.getFloat(KEY_LAST_LNG, 0f).toDouble()
    )
    val lastLngFlow: StateFlow<Double> = _lastLng.asStateFlow()


    fun setProximityFilterEnabled(isEnabled: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_PROXIMITY_FILTER_ENABLED, isEnabled).apply()
        _isProximityFilterEnabled.value = isEnabled // Update the flow
    }

    fun isProximityFilterEnabled(): Boolean {
        return _isProximityFilterEnabled.value // Return from flow for consistency
    }

    fun saveLastLocation(lat: Double, lng: Double) {
        sharedPreferences.edit().apply {
            putFloat(KEY_LAST_LAT, lat.toFloat())
            putFloat(KEY_LAST_LNG, lng.toFloat())
            apply()
        }
        _lastLat.value = lat // Update the flow
        _lastLng.value = lng // Update the flow
    }

    fun getLastLat(): Double {
        return _lastLat.value // Return from flow for consistency
    }

    fun getLastLng(): Double {
        return _lastLng.value // Return from flow for consistency
    }

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

    /**
     * Stores the user's preference for keeping the session active across app launches.
     */
    fun setKeepLoggedIn(isEnabled: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(KEY_KEEP_LOGGED_IN, isEnabled)
            apply()
        }
    }

    /**
     * Returns whether the user has requested to stay logged in.
     */
    fun isKeepLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_KEEP_LOGGED_IN, false)
    }
}
