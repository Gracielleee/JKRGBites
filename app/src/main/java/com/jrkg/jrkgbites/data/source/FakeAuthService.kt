package com.jrkg.jrkgbites.data.source

import com.jrkg.jrkgbites.domain.service.AuthResult
import com.jrkg.jrkgbites.domain.service.AuthService
import com.jrkg.jrkgbites.model.User
import com.jrkg.jrkgbites.model.UserPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import java.util.UUID

/**
 * A fake implementation of AuthService for development and testing.
 * It simulates network delays and uses a simple in-memory session.
 */
class FakeAuthService : AuthService {

    // Holds the current user session in memory.
    private val sessionState = MutableStateFlow<User?>(null)

    override fun login(email: String, password: String): Flow<AuthResult> = flow {
        emit(AuthResult.Loading)
        delay(1000) // Simulate network delay

        if (email.equals("test@jrkg.com", ignoreCase = true) && password == "password123") {
            val dummyUser = User(
                userId = "user-123",
                email = email,
                preferredName = "Test User",
                preferences = UserPreferences(maxDistance = 5000)
            )
            sessionState.value = dummyUser
            emit(AuthResult.Success(dummyUser))
        } else {
            emit(AuthResult.Error("Invalid email or password."))
        }
    }

    override fun signUp(email: String, password: String, preferredName: String): Flow<AuthResult> = flow {
        emit(AuthResult.Loading)
        delay(1500) // Simulate network delay

        // In a real app, you would check if the email is already taken.
        val newUser = User(
            userId = UUID.randomUUID().toString(),
            email = email,
            preferredName = preferredName,
            preferences = UserPreferences(maxDistance = 5000) // Default preferences
        )
        sessionState.value = newUser
        emit(AuthResult.Success(newUser))
    }

    override fun logout() {
        sessionState.value = null
    }

    override fun getSessionState(): Flow<User?> {
        return sessionState.asStateFlow()
    }
}
