package com.jrkg.jrkgbites.domain

import com.google.firebase.Timestamp
import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.domain.service.AuthResult
import com.jrkg.jrkgbites.domain.service.AuthService
import com.jrkg.jrkgbites.model.SubscriptionStatus
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

    /**
     * Delegates sending a password reset email to the underlying auth service.
     */
    fun sendPasswordResetEmail(email: String): Flow<Boolean> {
        return authService.sendPasswordResetEmail(email)
    }

    /**
     * Updates the user's subscription status in the backend.
     */
    suspend fun updateSubscription(userId: String, status: SubscriptionStatus, trialStart: Timestamp?) {
        authService.updateSubscription(userId, status, trialStart)
    }
}
