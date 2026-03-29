package com.jrkg.jrkgbites.data.source

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.jrkg.jrkgbites.domain.service.AuthResult
import com.jrkg.jrkgbites.domain.service.AuthService
import com.jrkg.jrkgbites.model.SubscriptionStatus
import com.jrkg.jrkgbites.model.User
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await

class FirebaseAuthService(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) : AuthService {

    private val _sessionState = MutableStateFlow<User?>(null)

    init {
        firebaseAuth.addAuthStateListener { auth ->
            val firebaseUser = auth.currentUser
            if (firebaseUser != null) {
                // Fetch extra user data from Firestore
                fetchUserData(firebaseUser.uid, firebaseUser.email ?: "", firebaseUser.displayName ?: "User")
            } else {
                _sessionState.value = null
            }
        }
    }

    private fun fetchUserData(uid: String, email: String, name: String) {
        firestore.collection("users").document(uid).addSnapshotListener { snapshot, _ ->
            if (snapshot != null && snapshot.exists()) {
                val statusStr = snapshot.getString("subscriptionStatus") ?: "NONE"
                val trialStart = snapshot.getTimestamp("trialStartedAt")
                
                _sessionState.value = User(
                    id = uid,
                    email = email,
                    preferredName = name,
                    subscriptionStatus = SubscriptionStatus.valueOf(statusStr),
                    trialStartedAt = trialStart
                )
            } else {
                // Initialize default user in Firestore if not exists
                val defaultUser = mapOf(
                    "email" to email,
                    "preferredName" to name,
                    "subscriptionStatus" to "NONE"
                )
                firestore.collection("users").document(uid).set(defaultUser)
                _sessionState.value = User(id = uid, email = email, preferredName = name)
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

    override fun signInWithGoogle(idToken: String): Flow<AuthResult> = flow {
        emit(AuthResult.Loading)
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = firebaseAuth.signInWithCredential(credential).await()
            val firebaseUser = result.user
            if (firebaseUser != null) {
                val user = User(
                    id = firebaseUser.uid,
                    email = firebaseUser.email ?: "",
                    preferredName = firebaseUser.displayName ?: "User"
                )
                _sessionState.value = user
                emit(AuthResult.Success(user))
            } else {
                emit(AuthResult.Error("Google Sign-In failed: User not found"))
            }
        } catch (e: Exception) {
            emit(AuthResult.Error(e.localizedMessage ?: "Unknown Google Sign-In error"))
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
        _sessionState.value = null
    }

    override fun getSessionState(): Flow<User?> = _sessionState.asStateFlow()

    override fun sendPasswordResetEmail(email: String): Flow<Boolean> = flow {
        try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            emit(true)
        } catch (_: Exception) {
            emit(false)
        }
    }

    override suspend fun updateSubscription(userId: String, status: SubscriptionStatus, trialStart: Timestamp?) {
        val data = mutableMapOf<String, Any>(
            "subscriptionStatus" to status.name
        )
        trialStart?.let { data["trialStartedAt"] = it }
        
        firestore.collection("users").document(userId).update(data).await()
    }
}
