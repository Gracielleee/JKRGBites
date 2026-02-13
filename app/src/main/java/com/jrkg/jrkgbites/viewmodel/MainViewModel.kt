package com.jrkg.jrkgbites.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jrkg.jrkgbites.AppDatabase
import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.data.UserPreferencesManager
import com.jrkg.jrkgbites.data.source.FakeAuthService
import com.jrkg.jrkgbites.domain.*
import com.jrkg.jrkgbites.domain.service.AuthResult
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.model.RestaurantRating
import com.jrkg.jrkgbites.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import androidx.lifecycle.viewModelScope
import com.jrkg.jrkgbites.services.BiometricService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val application: Application,
    private val restaurantPicker: RestaurantPicker,
    private val swipeManager: SwipeManager,
    private val searchManager: SearchManager,
    private val ratingManager: RatingManager,
    private val authManager: AuthManager,
    private val prefsManager: UserPreferencesManager,
    private val sessionManager: SessionManager,
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {

    // --- Session Manager State ---
    val sessionState: StateFlow<User?> = sessionManager.sessionState
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    // --- Auth Manager State ---
    private val _requiredAuthMethod = MutableStateFlow(authManager.getRequiredAuthMethod())
    val requiredAuthMethod: StateFlow<AuthMethod> = _requiredAuthMethod.asStateFlow()

    private val _isBiometricPreferenceEnabled =
        MutableStateFlow(prefsManager.isBiometricAuthEnabled())
    val isBiometricPreferenceEnabled: StateFlow<Boolean> =
        _isBiometricPreferenceEnabled.asStateFlow()

    // --- Shake Picker State ---
    private val _pickedResult = MutableStateFlow<String?>(null)
    val pickedResult: StateFlow<String?> = _pickedResult.asStateFlow()

    // --- Swipe Manager State ---
    val deck: StateFlow<List<Restaurant>> = swipeManager.deck

    val allRestaurants: StateFlow<List<Restaurant>> = swipeManager.allRestaurants

    val favorites: StateFlow<Set<String>> = swipeManager.favorites
    val neverAgain: StateFlow<Set<String>> = swipeManager.neverAgain
    
    val selectedRestaurant: StateFlow<Restaurant?> = swipeManager.selectedRestaurant
    val allRestaurantRatings: StateFlow<List<RestaurantRating>> = ratingManager.allRatings
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    private val _searchResults = MutableStateFlow<List<Restaurant>>(emptyList())
    val searchResults: StateFlow<List<Restaurant>> = _searchResults.asStateFlow()

    init {
        // Initialize managers that collect from Flows
        swipeManager.init(viewModelScope)
        restaurantPicker.init(viewModelScope)
        searchManager.init(viewModelScope)

        // Initial data loading for RoomDB
        viewModelScope.launch {
            restaurantRepository.refreshRestaurants(application)
        }
    }

    fun login(email: String, pass: String): Flow<AuthResult> {
        return sessionManager.login(email, pass)
    }

    fun logout() {
        sessionManager.logout()
    }

    fun onSwipe(restaurant: Restaurant, direction: SwipeDirection) {
        swipeManager.onSwipe(restaurant, direction)
    }

    fun resetDeck() {
        swipeManager.clearSessionSwipes()
    }

    fun shuffleDeck(){
        swipeManager.shuffleDeck()
    }

    fun undoSwipe(){
        swipeManager.undoLastSwipe()
    }

    fun clearSelectedRestaurant() {
        swipeManager.clearSelectedRestaurant()
    }

    /**
     * Exposes a Flow for a single restaurant by ID so that
     * UI layers (e.g., RestaurantDetailsFragment) don't depend
     * on the current swipe deck contents.
     */
    fun getRestaurantById(id: String): Flow<Restaurant?> {
        return restaurantRepository.getRestaurantById(id)
    }

    fun submitRating(restaurantId: String, rating: Int, comment: String) {
        viewModelScope.launch {
            ratingManager.submitRating(restaurantId, rating.toInt(), comment)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val database = AppDatabase.getDatabase(application)
            val restaurantDao = database.restaurantDao()
            val restaurantRatingDao = database.restaurantRatingDao()

            val restaurantRepository = RestaurantRepository(restaurantDao)
            val prefsManager = UserPreferencesManager(application)
            val authService = FakeAuthService()
            val sessionManager = SessionManager(authService)
            val biometricService = BiometricService(application)
            val authManager = AuthManager(biometricService, prefsManager)
            val swipeManager = SwipeManager(restaurantRepository)
            val ratingManager = RatingManager(swipeManager, restaurantRatingDao)
            val searchManager = SearchManager(restaurantRepository)
            val restaurantPicker = RestaurantPicker(restaurantRepository)

            return MainViewModel(
                application = application,
                restaurantPicker = restaurantPicker,
                swipeManager = swipeManager,
                searchManager = searchManager,
                ratingManager = ratingManager,
                authManager = authManager,
                prefsManager = prefsManager,
                sessionManager = sessionManager,
                restaurantRepository = restaurantRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
