package com.jrkg.jrkgbites.domain

import com.jrkg.jrkgbites.domain.service.AuthResult
import com.jrkg.jrkgbites.domain.service.AuthService
import com.jrkg.jrkgbites.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Manages the user session by orchestrating the AuthService.
 * This class is the single entry point for the UI/ViewModel layer to interact
 * with the authentication system.
 */
class SessionManager(private val authService: AuthService) {

    /**
     * A flow that emits the current user when the session state changes, or null for logged-out state.
     */
    val sessionState: Flow<User?> = authService.getSessionState()

    /**
     * Delegates the login request to the underlying auth service.
     */
    fun login(email: String, password: String): Flow<AuthResult> {
        return authService.login(email, password)
    }

    /**
     * Delegates the sign-up request to the underlying auth service.
     */
    fun signUp(email: String, password: String, preferredName: String): Flow<AuthResult> {
        return authService.signUp(email, password, preferredName)
    }

    /**
     * Delegates the logout request to the underlying auth service.
     */
    fun logout() {
        authService.logout()
    }
}
