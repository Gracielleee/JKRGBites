package com.jrkg.jrkgbites.utils;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\'\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\f\"\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/jrkg/jrkgbites/utils/ValidationUtils;", "", "<init>", "()V", "passwordPattern", "Lkotlin/text/Regex;", "usernamePattern", "highlightErrorFields", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "layouts", "", "Lcom/google/android/material/textfield/TextInputLayout;", "(Lkotlinx/coroutines/CoroutineScope;[Lcom/google/android/material/textfield/TextInputLayout;)V", "validateUsernameFormat", "", "editText", "Landroid/widget/EditText;", "validateEmailFormat", "validatePasswordFormat", "app_debug"})
public final class ValidationUtils {
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.text.Regex passwordPattern = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.text.Regex usernamePattern = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.jrkg.jrkgbites.utils.ValidationUtils INSTANCE = null;
    
    private ValidationUtils() {
        super();
    }
    
    public final void highlightErrorFields(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope, @org.jetbrains.annotations.NotNull()
    com.google.android.material.textfield.TextInputLayout... layouts) {
    }
    
    public final boolean validateUsernameFormat(@org.jetbrains.annotations.NotNull()
    android.widget.EditText editText) {
        return false;
    }
    
    public final boolean validateEmailFormat(@org.jetbrains.annotations.NotNull()
    android.widget.EditText editText) {
        return false;
    }
    
    public final boolean validatePasswordFormat(@org.jetbrains.annotations.NotNull()
    android.widget.EditText editText) {
        return false;
    }
}