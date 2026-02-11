package com.jrkg.jrkgbites.services;

/**
 * An enum to represent the availability of biometric authentication on the device.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jrkg/jrkgbites/services/BiometricAuthStatus;", "", "<init>", "(Ljava/lang/String;I)V", "READY", "NOT_AVAILABLE", "NO_FINGERPRINT_ENROLLED", "app_debug"})
public enum BiometricAuthStatus {
    /*public static final*/ READY /* = new READY() */,
    /*public static final*/ NOT_AVAILABLE /* = new NOT_AVAILABLE() */,
    /*public static final*/ NO_FINGERPRINT_ENROLLED /* = new NO_FINGERPRINT_ENROLLED() */;
    
    BiometricAuthStatus() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.jrkg.jrkgbites.services.BiometricAuthStatus> getEntries() {
        return null;
    }
}