package com.jrkg.jrkgbites;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0015H\u0002J\b\u0010\u001b\u001a\u00020\u0015H\u0002J\b\u0010\u001c\u001a\u00020\u0015H\u0002J\u0010\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u0010H\u0002J\b\u0010\u001f\u001a\u00020\u0015H\u0002J\b\u0010 \u001a\u00020\u0015H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/jrkg/jrkgbites/LoginFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "etEmail", "Lcom/google/android/material/textfield/TextInputEditText;", "etPassword", "checkBoxKeepMeLoggedIn", "Landroid/widget/CheckBox;", "loginButton", "Landroid/widget/Button;", "bioAuthButton", "goToForgotPassword", "Landroid/widget/TextView;", "gotoRegister", "isValidEmailFormat", "", "isAutoLoginEnabled", "viewModel", "Lcom/jrkg/jrkgbites/viewmodel/MainViewModel;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupListeners", "onLoginButtonPressed", "onBioAuthButtonPressed", "updateAutoLoginStatus", "isChecked", "onGoToRegisterPressed", "onGoToForgotPasswordPressed", "app_debug"})
public final class LoginFragment extends androidx.fragment.app.Fragment {
    private com.google.android.material.textfield.TextInputEditText etEmail;
    private com.google.android.material.textfield.TextInputEditText etPassword;
    private android.widget.CheckBox checkBoxKeepMeLoggedIn;
    private android.widget.Button loginButton;
    private android.widget.Button bioAuthButton;
    private android.widget.TextView goToForgotPassword;
    private android.widget.TextView gotoRegister;
    private boolean isValidEmailFormat = false;
    private boolean isAutoLoginEnabled = false;
    private com.jrkg.jrkgbites.viewmodel.MainViewModel viewModel;
    
    public LoginFragment() {
        super();
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupListeners() {
    }
    
    private final void onLoginButtonPressed() {
    }
    
    private final void onBioAuthButtonPressed() {
    }
    
    private final void updateAutoLoginStatus(boolean isChecked) {
    }
    
    private final void onGoToRegisterPressed() {
    }
    
    private final void onGoToForgotPasswordPressed() {
    }
}