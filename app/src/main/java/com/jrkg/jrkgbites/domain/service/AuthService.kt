package com.jrkg.jrkgbites.domain.service

import com.jrkg.jrkgbites.model.User
import kotlinx.coroutines.flow.Flow

/**
 * A wrapper class to represent the result of an authentication operation,
 * which can be in a Success, Error, or Loading state.
 */
sealed class AuthResult {
    data class Success(val user: User) : AuthResult()
    data class Error(val message: String) : AuthResult()
    object Loading : AuthResult()
}

/**
 * Defines the contract for an authentication service.
 * This allows for swapping implementations (e.g., Fake vs. Firebase) easily.
 */
interface AuthService {
    /**
     * Attempts to log in a user with the given credentials.
     * @return A Flow that emits the result of the operation.
     */
    fun login(email: String, password: String): Flow<AuthResult>

    /**
     * Attempts to sign up a new user with the given details.
     * @return A Flow that emits the result of the operation.
     */
    fun signUp(email: String, password: String, preferredName: String): Flow<AuthResult>

    /**
     * Logs out the current user.
     */
    fun logout()

    /**
     * Gets a real-time flow of the current user session state.
     * Emits the User object if logged in, or null if logged out.
     */
    fun getSessionState(): Flow<User?>
}
