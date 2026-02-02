package com.jrkg.jrkgbites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jrkg.jrkgbites.data.UserPreferencesManager
import com.jrkg.jrkgbites.domain.*
import com.jrkg.jrkgbites.domain.service.AuthResult
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    private val restaurantPicker: RestaurantPicker,
    private val swipeManager: SwipeManager,
    private val searchManager: SearchManager,
    private val ratingManager: RatingManager,
    private val authManager: AuthManager,
    private val prefsManager: UserPreferencesManager,
    private val sessionManager: SessionManager
) : ViewModel() {

    // --- Session Manager State ---
    val sessionState: StateFlow<User?> = sessionManager.sessionState
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), // Keep it running for 5 seconds after last subscriber
            null // Initial value for the StateFlow
        )

    // --- Auth Manager State ---
    private val _requiredAuthMethod = MutableStateFlow(authManager.getRequiredAuthMethod())
    val requiredAuthMethod: StateFlow<AuthMethod> = _requiredAuthMethod.asStateFlow()

    private val _isBiometricPreferenceEnabled = MutableStateFlow(prefsManager.isBiometricAuthEnabled())
    val isBiometricPreferenceEnabled: StateFlow<Boolean> = _isBiometricPreferenceEnabled.asStateFlow()

    // --- Shake Picker State ---
    private val _pickedResult = MutableStateFlow<String?>(null)
    val pickedResult: StateFlow<String?> = _pickedResult.asStateFlow()

    // --- (Other states remain the same) ---
    val deck: StateFlow<List<Restaurant>> = swipeManager.deck
    private val _searchResults = MutableStateFlow<List<Restaurant>>(emptyList())
    val searchResults: StateFlow<List<Restaurant>> = _searchResults.asStateFlow()


    // --- Session Manager Actions ---
    fun login(email: String, password: String): Flow<AuthResult> {
        return sessionManager.login(email, password)
    }

    fun signUp(email: String, password: String, preferredName: String): Flow<AuthResult> {
        return sessionManager.signUp(email, password, preferredName)
    }

    fun logout() {
        sessionManager.logout()
    }

    // --- Auth Actions ---
    fun setBiometricPreference(isEnabled: Boolean) {
        prefsManager.setBiometricAuthEnabled(isEnabled)
        _isBiometricPreferenceEnabled.update { isEnabled }
        _requiredAuthMethod.update { authManager.getRequiredAuthMethod() }
    }

    // --- (Other actions remain the same) ---
    fun onShakeDetected() {
        val result = restaurantPicker.pickRandom(
            filterType = "Resto",
            mallFilter = "SM North EDSA"
        )
        _pickedResult.update { result }
    }

    fun swipe(restaurant: Restaurant, direction: SwipeDirection) {
        swipeManager.swipe(restaurant, direction)
    }

    fun search(query: String) {
        val results = searchManager.searchAndSort(query)
        _searchResults.update { results }
    }

    fun submitRating(restaurantId: String, rating: Int) {
        ratingManager.submitRating(restaurantId, rating)
    }
}

/**
 * Factory for creating a MainViewModel with its dependencies.
 */
class MainViewModelFactory(
    private val restaurantPicker: RestaurantPicker,
    private val swipeManager: SwipeManager,
    private val searchManager: SearchManager,
    private val ratingManager: RatingManager,
    private val authManager: AuthManager,
    private val prefsManager: UserPreferencesManager,
    private val sessionManager: SessionManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                restaurantPicker,
                swipeManager,
                searchManager,
                ratingManager,
                authManager,
                prefsManager,
                sessionManager
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
