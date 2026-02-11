package com.jrkg.jrkgbites.domain;

/**
 * Manages the user session by orchestrating the AuthService.
 * This class is the single entry point for the UI/ViewModel layer to interact
 * with the authentication system.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eJ$\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eJ\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lcom/jrkg/jrkgbites/domain/SessionManager;", "", "authService", "Lcom/jrkg/jrkgbites/domain/service/AuthService;", "<init>", "(Lcom/jrkg/jrkgbites/domain/service/AuthService;)V", "sessionState", "Lkotlinx/coroutines/flow/Flow;", "Lcom/jrkg/jrkgbites/model/User;", "getSessionState", "()Lkotlinx/coroutines/flow/Flow;", "login", "Lcom/jrkg/jrkgbites/domain/service/AuthResult;", "email", "", "password", "signUp", "preferredName", "logout", "", "app_debug"})
public final class SessionManager {
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.domain.service.AuthService authService = null;
    
    /**
     * A flow that emits the current user when the session state changes, or null for logged-out state.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.model.User> sessionState = null;
    
    public SessionManager(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.service.AuthService authService) {
        super();
    }
    
    /**
     * A flow that emits the current user when the session state changes, or null for logged-out state.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.model.User> getSessionState() {
        return null;
    }
    
    /**
     * Delegates the login request to the underlying auth service.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.domain.service.AuthResult> login(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return null;
    }
    
    /**
     * Delegates the sign-up request to the underlying auth service.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.domain.service.AuthResult> signUp(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String preferredName) {
        return null;
    }
    
    /**
     * Delegates the logout request to the underlying auth service.
     */
    public final void logout() {
    }
}