package com.jrkg.jrkgbites;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0017H\u0002J\b\u0010\u001d\u001a\u00020\u0017H\u0002J\b\u0010\u001e\u001a\u00020\u0017H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/jrkg/jrkgbites/RegisterFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "etUsername", "Lcom/google/android/material/textfield/TextInputEditText;", "etEmail", "etPassword", "etConfirmPassword", "checkBoxTermsAgreement", "Landroid/widget/CheckBox;", "registerButton", "Landroid/widget/Button;", "backButton", "Landroid/widget/ImageButton;", "gotoLogin", "Landroid/widget/TextView;", "isValidUsernameFormat", "", "isValidEmailFormat", "isValidPasswordFormat", "isTermsAndConditionsAccepted", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupListeners", "onRegisterButtonPressed", "onGoToLoginPressed", "app_debug"})
public final class RegisterFragment extends androidx.fragment.app.Fragment {
    private com.google.android.material.textfield.TextInputEditText etUsername;
    private com.google.android.material.textfield.TextInputEditText etEmail;
    private com.google.android.material.textfield.TextInputEditText etPassword;
    private com.google.android.material.textfield.TextInputEditText etConfirmPassword;
    private android.widget.CheckBox checkBoxTermsAgreement;
    private android.widget.Button registerButton;
    private android.widget.ImageButton backButton;
    private android.widget.TextView gotoLogin;
    private boolean isValidUsernameFormat = false;
    private boolean isValidEmailFormat = false;
    private boolean isValidPasswordFormat = false;
    private boolean isTermsAndConditionsAccepted = false;
    
    public RegisterFragment() {
        super();
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupListeners() {
    }
    
    private final void onRegisterButtonPressed() {
    }
    
    private final void onGoToLoginPressed() {
    }
}