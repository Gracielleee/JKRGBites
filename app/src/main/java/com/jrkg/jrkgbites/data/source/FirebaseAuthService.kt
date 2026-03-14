package com.jrkg.jrkgbites.data.source

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.jrkg.jrkgbites.domain.service.AuthResult
import com.jrkg.jrkgbites.domain.service.AuthService
import com.jrkg.jrkgbites.model.User
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await

class FirebaseAuthService(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) : AuthService {

    private val _sessionState = MutableStateFlow<User?>(null)

    init {
        firebaseAuth.addAuthStateListener { auth ->
            val firebaseUser = auth.currentUser
            _sessionState.value = firebaseUser?.let {
                User(
                    id = it.uid,
                    email = it.email ?: "",
                    preferredName = it.displayName ?: "User"
                )
            }
        }
    }

    override fun login(email: String, password: String): Flow<AuthResult> = flow {
        emit(AuthResult.Loading)
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user
            if (firebaseUser != null) {
                firebaseUser.reload().await()
                val user = User(
                    id = firebaseUser.uid,
                    email = firebaseUser.email ?: "",
                    preferredName = firebaseUser.displayName ?: "User"
                )
                _sessionState.value = user
                emit(AuthResult.Success(user))
            } else {
                emit(AuthResult.Error("Login failed: User not found"))
            }
        } catch (e: Exception) {
            emit(AuthResult.Error(e.localizedMessage ?: "Unknown login error"))
        }
    }

    override fun signUp(email: String, password: String, preferredName: String): Flow<AuthResult> = flow {
        emit(AuthResult.Loading)
        try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user
            
            if (firebaseUser != null) {
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(preferredName)
                    .build()
                firebaseUser.updateProfile(profileUpdates).await()
                firebaseUser.reload().await()

                val updatedUser = User(
                    id = firebaseUser.uid,
                    email = firebaseUser.email ?: "",
                    preferredName = firebaseUser.displayName ?: preferredName
                )
                
                _sessionState.value = updatedUser
                emit(AuthResult.Success(updatedUser))
            } else {
                emit(AuthResult.Error("Sign up failed: User creation failed"))
            }
        } catch (e: Exception) {
            emit(AuthResult.Error(e.localizedMessage ?: "Unknown sign up error"))
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
        _sessionState.value = null
    }

    override fun getSessionState(): Flow<User?> = _sessionState.asStateFlow()

    override fun sendPasswordResetEmail(email: String): Flow<Boolean> = flow {
        emit(false)
        try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            emit(true)
        } catch (_: Exception) {
            emit(false)
        }
    }
}
