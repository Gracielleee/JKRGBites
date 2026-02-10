package com.jrkg.jrkgbites.domain.service;

/**
 * Defines the contract for an authentication service.
 * This allows for swapping implementations (e.g., Fake vs. Firebase) easily.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H&J\u001e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH&J\b\u0010\n\u001a\u00020\u000bH&J&\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH&\u00a8\u0006\u000e"}, d2 = {"Lcom/jrkg/jrkgbites/domain/service/AuthService;", "", "getSessionState", "Lkotlinx/coroutines/flow/Flow;", "Lcom/jrkg/jrkgbites/model/User;", "login", "Lcom/jrkg/jrkgbites/domain/service/AuthResult;", "email", "", "password", "logout", "", "signUp", "preferredName", "app_debug"})
public abstract interface AuthService {
    
    /**
     * Attempts to log in a user with the given credentials.
     * @return A Flow that emits the result of the operation.
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.domain.service.AuthResult> login(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password);
    
    /**
     * Attempts to sign up a new user with the given details.
     * @return A Flow that emits the result of the operation.
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.domain.service.AuthResult> signUp(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String preferredName);
    
    /**
     * Logs out the current user.
     */
    public abstract void logout();
    
    /**
     * Gets a real-time flow of the current user session state.
     * Emits the User object if logged in, or null if logged out.
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.model.User> getSessionState();
}