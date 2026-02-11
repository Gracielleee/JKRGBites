package com.jrkg.jrkgbites.services;

/**
 * A service class to handle all interactions with the Android Biometric/Fingerprint APIs.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007Jb\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r26\u0010\u000e\u001a2\u0012\u0013\u0012\u00110\u0010\u00a2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0014\u00a2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\t0\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/jrkg/jrkgbites/services/BiometricService;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getAuthStatus", "Lcom/jrkg/jrkgbites/services/BiometricAuthStatus;", "authenticate", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "errorCode", "", "errString", "onFailed", "app_debug"})
public final class BiometricService {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public BiometricService(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Checks the device for biometric capabilities, focusing on strong methods like fingerprint.
     * @return A [BiometricAuthStatus] indicating if authentication is ready to be used.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.jrkg.jrkgbites.services.BiometricAuthStatus getAuthStatus() {
        return null;
    }
    
    /**
     * Displays the fingerprint authentication prompt to the user.
     *
     * @param activity The activity that is hosting the prompt.
     * @param onSuccess A callback function to be invoked upon successful authentication.
     * @param onError A callback function for when an unrecoverable error occurs.
     * @param onFailed A callback for when the fingerprint is not recognized.
     */
    public final void authenticate(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentActivity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super java.lang.CharSequence, kotlin.Unit> onError, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onFailed) {
    }
}