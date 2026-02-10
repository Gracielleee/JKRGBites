package com.jrkg.jrkgbites;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0002J\u001a\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0014H\u0002J\u0010\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/jrkg/jrkgbites/LoginFragment;", "Landroidx/fragment/app/Fragment;", "()V", "bioAuthButton", "Landroid/widget/Button;", "checkBoxKeepMeLoggedIn", "Landroid/widget/CheckBox;", "etEmail", "Lcom/google/android/material/textfield/TextInputEditText;", "etPassword", "goToForgotPassword", "Landroid/widget/TextView;", "gotoRegister", "isAutoLoginEnabled", "", "isValidEmailFormat", "loginButton", "viewModel", "Lcom/jrkg/jrkgbites/viewmodel/MainViewModel;", "onBioAuthButtonPressed", "", "onGoToForgotPasswordPressed", "onGoToRegisterPressed", "onLoginButtonPressed", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupListeners", "updateAutoLoginStatus", "isChecked", "app_debug"})
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