package com.jrkg.jrkgbites.domain.service;

import com.google.firebase.Timestamp;
import com.jrkg.jrkgbites.model.SubscriptionStatus;
import com.jrkg.jrkgbites.model.User;
import kotlinx.coroutines.flow.Flow;

/**
 * Defines the contract for an authentication service.
 * This allows for swapping implementations (e.g., Fake vs. Firebase) easily.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J&\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H&J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u000b\u001a\u00020\u0006H&J\b\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0003H&J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J(\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u00a6@\u00a2\u0006\u0002\u0010\u0018\u00a8\u0006\u0019\u00c0\u0006\u0003"}, d2 = {"Lcom/jrkg/jrkgbites/domain/service/AuthService;", "", "login", "Lkotlinx/coroutines/flow/Flow;", "Lcom/jrkg/jrkgbites/domain/service/AuthResult;", "email", "", "password", "signUp", "preferredName", "signInWithGoogle", "idToken", "logout", "", "getSessionState", "Lcom/jrkg/jrkgbites/model/User;", "sendPasswordResetEmail", "", "updateSubscription", "userId", "status", "Lcom/jrkg/jrkgbites/model/SubscriptionStatus;", "trialStart", "Lcom/google/firebase/Timestamp;", "(Ljava/lang/String;Lcom/jrkg/jrkgbites/model/SubscriptionStatus;Lcom/google/firebase/Timestamp;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
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
     * Attempts to log in or sign up a user using a Google ID token.
     * @return A Flow that emits the result of the operation.
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.domain.service.AuthResult> signInWithGoogle(@org.jetbrains.annotations.NotNull()
    java.lang.String idToken);
    
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
    
    /**
     * Sends a password reset email to the given address.
     * @return A Flow emitting true on success, false on failure.
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> sendPasswordResetEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email);
    
    /**
     * Updates user subscription status.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateSubscription(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.SubscriptionStatus status, @org.jetbrains.annotations.Nullable()
    com.google.firebase.Timestamp trialStart, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}