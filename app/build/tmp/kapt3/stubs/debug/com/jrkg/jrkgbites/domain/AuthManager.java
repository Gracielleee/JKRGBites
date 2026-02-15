package com.jrkg.jrkgbites.domain;

/**
 * Manages the business logic for making authentication decisions.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/jrkg/jrkgbites/domain/AuthManager;", "", "biometricService", "Lcom/jrkg/jrkgbites/services/BiometricService;", "userPreferencesManager", "Lcom/jrkg/jrkgbites/data/UserPreferencesManager;", "<init>", "(Lcom/jrkg/jrkgbites/services/BiometricService;Lcom/jrkg/jrkgbites/data/UserPreferencesManager;)V", "getRequiredAuthMethod", "Lcom/jrkg/jrkgbites/domain/AuthMethod;", "app_debug"})
public final class AuthManager {
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.services.BiometricService biometricService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.UserPreferencesManager userPreferencesManager = null;
    
    public AuthManager(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.services.BiometricService biometricService, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.UserPreferencesManager userPreferencesManager) {
        super();
    }
    
    /**
     * Determines which authentication method should be used based on device capabilities
     * and the user's saved preferences.
     *
     * @return The [AuthMethod] that the app should use.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.jrkg.jrkgbites.domain.AuthMethod getRequiredAuthMethod() {
        return null;
    }
}