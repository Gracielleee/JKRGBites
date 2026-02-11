package com.jrkg.jrkgbites.data;

/**
 * Manages saving and retrieving user-specific preferences using SharedPreferences.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/jrkg/jrkgbites/data/UserPreferencesManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "setBiometricAuthEnabled", "", "isEnabled", "", "isBiometricAuthEnabled", "Companion", "app_debug"})
public final class UserPreferencesManager {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFERENCES_FILE_NAME = "JRKGBitesUserPrefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_BIOMETRIC_AUTH_ENABLED = "biometricAuthEnabled";
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.jrkg.jrkgbites.data.UserPreferencesManager.Companion Companion = null;
    
    public UserPreferencesManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
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
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/jrkg/jrkgbites/data/UserPreferencesManager$Companion;", "", "<init>", "()V", "PREFERENCES_FILE_NAME", "", "KEY_BIOMETRIC_AUTH_ENABLED", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}