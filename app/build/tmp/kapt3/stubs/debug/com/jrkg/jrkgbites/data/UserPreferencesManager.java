package com.jrkg.jrkgbites.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Manages saving and retrieving user-specific preferences using SharedPreferences.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\t\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bJ\u0016\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\u000fJ\u0006\u0010\u0012\u001a\u00020\u000fJ\u000e\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\u0014\u001a\u00020\u000bJ\u000e\u0010\u0015\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\u0016\u001a\u00020\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/jrkg/jrkgbites/data/UserPreferencesManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "setProximityFilterEnabled", "", "isEnabled", "", "isProximityFilterEnabled", "saveLastLocation", "lat", "", "lng", "getLastLat", "getLastLng", "setBiometricAuthEnabled", "isBiometricAuthEnabled", "setKeepLoggedIn", "isKeepLoggedIn", "Companion", "app_debug"})
public final class UserPreferencesManager {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFERENCES_FILE_NAME = "JRKGBitesUserPrefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_BIOMETRIC_AUTH_ENABLED = "biometricAuthEnabled";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_KEEP_LOGGED_IN = "keepLoggedIn";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PROXIMITY_FILTER_ENABLED = "proximityFilterEnabled";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LAST_LAT = "lastLat";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LAST_LNG = "lastLng";
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.jrkg.jrkgbites.data.UserPreferencesManager.Companion Companion = null;
    
    public UserPreferencesManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void setProximityFilterEnabled(boolean isEnabled) {
    }
    
    public final boolean isProximityFilterEnabled() {
        return false;
    }
    
    public final void saveLastLocation(double lat, double lng) {
    }
    
    public final double getLastLat() {
        return 0.0;
    }
    
    public final double getLastLng() {
        return 0.0;
    }
    
    /**
     * Saves the user's choice for enabling or disabling biometric authentication.
     *
     * @param isEnabled True to enable biometric auth, false to disable.
     */
    public final void setBiometricAuthEnabled(boolean isEnabled) {
    }
    
    /**
     * Retrieves the user's saved preference for using biometric authentication.
     *
     * @return True if the user has enabled biometric auth, false otherwise. Defaults to false.
     */
    public final boolean isBiometricAuthEnabled() {
        return false;
    }
    
    /**
     * Stores the user's preference for keeping the session active across app launches.
     */
    public final void setKeepLoggedIn(boolean isEnabled) {
    }
    
    /**
     * Returns whether the user has requested to stay logged in.
     */
    public final boolean isKeepLoggedIn() {
        return false;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/jrkg/jrkgbites/data/UserPreferencesManager$Companion;", "", "<init>", "()V", "PREFERENCES_FILE_NAME", "", "KEY_BIOMETRIC_AUTH_ENABLED", "KEY_KEEP_LOGGED_IN", "KEY_PROXIMITY_FILTER_ENABLED", "KEY_LAST_LAT", "KEY_LAST_LNG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}