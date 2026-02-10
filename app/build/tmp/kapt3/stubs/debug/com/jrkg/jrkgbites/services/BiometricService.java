package com.jrkg.jrkgbites.services;

/**
 * A service class to handle all interactions with the Android Biometric/Fingerprint APIs.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004Jb\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n26\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00060\f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\nJ\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/jrkg/jrkgbites/services/BiometricService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "authenticate", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "errorCode", "", "errString", "onFailed", "getAuthStatus", "Lcom/jrkg/jrkgbites/services/BiometricAuthStatus;", "app_debug"})
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