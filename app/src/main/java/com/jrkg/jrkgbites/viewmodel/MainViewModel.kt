package com.jrkg.jrkgbites.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jrkg.jrkgbites.AppDatabase
import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.data.UserPreferencesManager
import com.jrkg.jrkgbites.domain.*
import com.jrkg.jrkgbites.domain.service.AuthResult
import com.jrkg.jrkgbites.domain.service.AuthService
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.model.RestaurantRating
import com.jrkg.jrkgbites.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

import androidx.lifecycle.viewModelScope
import com.jrkg.jrkgbites.data.RestaurantRatingRepository
import com.jrkg.jrkgbites.data.source.FirebaseAuthService
import com.jrkg.jrkgbites.services.BiometricService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.jrkg.jrkgbites.R

/**
 * Resolved start destination for the navigation graph.
 * Used by the Activity to set the graph programmatically and avoid login flash.
 */
enum class StartDestination {
    LOGIN,
    MAIN
}

class MainViewModel(
    private val application: Application,
    private val restaurantPicker: RestaurantPicker,
    private val swipeManager: SwipeManager,
    private val searchManager: SearchManager,
    private val ratingManager: RatingManager,
    private val authManager: AuthManager,
    private val prefsManager: UserPreferencesManager,
    private val sessionManager: SessionManager,
    private val restaurantRepository: RestaurantRepository,
    private val restaurantRatingRepository: RestaurantRatingRepository,
    private val restaurantManager: RestaurantManager
) : ViewModel() {

    // --- Session Manager State ---
    val sessionState: StateFlow<User?> = sessionManager.sessionState
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    /** Resolved once auth state has settled; used by Activity to set nav graph start destination. */
    private val _startDestination = MutableStateFlow<StartDestination?>(null)
    val startDestination: StateFlow<StartDestination?> = _startDestination.asStateFlow()

    /** One-off event: request navigation to main after biometric (or other auth) success. */
    private val _navigateToMainEvent = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val navigateToMainEvent: SharedFlow<Unit> = _navigateToMainEvent.asSharedFlow()

    init {
        // Initialize managers that collect from Flows.
        swipeManager.init(viewModelScope)
        restaurantPicker.init(viewModelScope)
        searchManager.init(viewModelScope)

        // Initial data loading for RoomDB
        viewModelScope.launch {
            delay(150)
            val currentUser = sessionState.value
            val keepLoggedIn = prefsManager.isKeepLoggedIn()

            // If Firebase has a user but the user did NOT opt into persistence,
            // immediately clear the Firebase session so the next launch starts clean.
            if (currentUser != null && !keepLoggedIn) {
                sessionManager.logout()
                _startDestination.value = StartDestination.LOGIN
            } else {
                _startDestination.value =
                    if (currentUser != null && keepLoggedIn) StartDestination.MAIN
                    else StartDestination.LOGIN
            }
        }

        // Initial data loading for RoomDB; full dataset from JSON when needed, then sync.
        viewModelScope.launch {
//            restaurantRepository.refreshRestaurants(application)
            // 1. Observe the sessionState Flow
            sessionState.collectLatest { user ->
                when {
                    user == null -> {
                        // Fallback
                        restaurantRepository.pullFreshFromJSON(application)
                    }
                    user.id.isNotEmpty() -> {
                        // User restored from session OR just logged in
                        restaurantRepository.syncRestaurants(user.id)
                        restaurantRatingRepository.syncRatings(user.id)
                    }
                }
            }
        }
    }

    /** Call after biometric (or other) auth success to trigger navigation to main. */
    fun requestNavigateToMainAfterAuth() {
        viewModelScope.launch {
            _navigateToMainEvent.emit(Unit)
        }
    }

    // --- Toast ---
    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    // --- One-off Events ---
    private val _lowRatingEvent = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val lowRatingEvent: SharedFlow<String> = _lowRatingEvent.asSharedFlow()

    fun clearToastMessage() {
        _toastMessage.value = null
    }

//    -----------------------------------------------------------------------------------------   //

    // --- Auth Manager State ---
    private val _requiredAuthMethod = MutableStateFlow(authManager.getRequiredAuthMethod())
    val requiredAuthMethod: StateFlow<AuthMethod> = _requiredAuthMethod.asStateFlow()

    private val _isBiometricPreferenceEnabled =
        MutableStateFlow(prefsManager.isBiometricAuthEnabled())
    val isBiometricPreferenceEnabled: StateFlow<Boolean> =
        _isBiometricPreferenceEnabled.asStateFlow()

    private val _isKeepLoggedInEnabled =
        MutableStateFlow(prefsManager.isKeepLoggedIn())
    val isKeepLoggedInEnabled: StateFlow<Boolean> =
        _isKeepLoggedInEnabled.asStateFlow()

    // --- Shake Picker State ---
    private val _pickedResult = MutableStateFlow<String?>(null)
    val pickedResult: StateFlow<String?> = _pickedResult.asStateFlow()

    // --- Swipe Manager State ---
    val deck: StateFlow<List<Restaurant>> by lazy { swipeManager.deck }
    val allRestaurants: StateFlow<List<Restaurant>> by lazy { swipeManager.allRestaurants }
    val favoritesList: StateFlow<List<Restaurant>> by lazy { swipeManager.favoritesList }
    val neverAgainList: StateFlow<List<Restaurant>> by lazy { swipeManager.neverAgainList }
    val selectedRestaurant: StateFlow<Restaurant?> by lazy { swipeManager.selectedRestaurant }

    val allRestaurantRatings: StateFlow<List<RestaurantRating>> = ratingManager.allRatings
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    private val _searchResults = MutableStateFlow<List<Restaurant>>(emptyList())
    val searchResults: StateFlow<List<Restaurant>> = _searchResults.asStateFlow()

    fun login(email: String, pass: String): Flow<AuthResult> {
        return sessionManager.login(email, pass)
    }

    fun signUp(email: String, pass: String, preferredName: String): Flow<AuthResult> {
        return sessionManager.signUp(email, pass, preferredName)
    }

    suspend fun logout() {
        restaurantRepository.deleteAllLocal()
        // When the user explicitly logs out, also clear the keep-logged-in preference.
        prefsManager.setKeepLoggedIn(false)
        _isKeepLoggedInEnabled.value = false
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

    fun refreshDeck() {
        swipeManager.updateDeck()
    }

    fun clearSelectedRestaurant() {
        swipeManager.clearSelectedRestaurant()
    }

    fun getRestaurantById(id: String): Flow<Restaurant?> {
        return restaurantRepository.getRestaurantById(id)
    }

    fun toggleFavorite(restaurantId: String) {
        viewModelScope.launch {
            restaurantManager.toggleFavorite(restaurantId, sessionState.value?.id ?: "")
        }
    }

    fun toggleNeverAgain(restaurantId: String) {
        viewModelScope.launch {
            restaurantManager.toggleNeverAgain(restaurantId, sessionState.value?.id ?: "")
        }
    }

    fun setKeepLoggedIn(enabled: Boolean) {
        prefsManager.setKeepLoggedIn(enabled)
        _isKeepLoggedInEnabled.value = enabled
    }

    fun submitRating(restaurantId: String, rating: Int, comment: String) {
        viewModelScope.launch {
            val userId = sessionState.value?.id.orEmpty()
            val isLowRating = ratingManager.submitRating(restaurantId, rating, comment, userId)

            _toastMessage.value = application.getString(R.string.toast_rating_submitted)

            if (isLowRating) {
                // Emit an event so the UI can decide whether to add to "Never Again".
                _lowRatingEvent.emit(restaurantId)
            }
        }
    }

    fun isUserOwner(restaurant: Restaurant?): Boolean {
        return restaurant?.addedBy == sessionState.value?.id
    }

    fun addToNeverAgainFromRating(restaurantId: String) {
        viewModelScope.launch {
            restaurantManager.addToNeverAgain(restaurantId, sessionState.value?.id ?: "")
        }
    }

    fun createRestaurant(restaurant: Restaurant) {
        viewModelScope.launch {
            try {
                restaurantRepository.createRestaurant(restaurant, sessionState.value?.id ?: "")
                _toastMessage.value = "Restaurant saved successfully!"
            } catch (e: Exception) {
                _toastMessage.value = "Failed to save restaurant."
            }
        }
    }

    fun updateRestaurant(restaurant: Restaurant) {
        viewModelScope.launch {
            val result = restaurantRepository.updateRestaurant(restaurant, sessionState.value?.id ?: "")
            _toastMessage.value = when (result) {
                200 -> "Restaurant updated successfully!"
                401 -> "Unauthorized action."
                else -> "Failed to update restaurant."
            }
        }
    }

    fun deleteRestaurant(restaurant: Restaurant) {
        viewModelScope.launch {
            val result = restaurantManager.deleteRestaurant(restaurant, sessionState.value?.id ?: "")
            if (result == 200) {
                _toastMessage.value = "Restaurant deleted successfully!"
            } else if (result == 401) {
                _toastMessage.value = "Unauthorized. You do not have permission to perform this action."
            } else {
                _toastMessage.value = "Failed to delete restaurant. Try again later."
            }
        }
    }

    fun sendPasswordResetEmail(email: String): Flow<Boolean> {
        return sessionManager.sendPasswordResetEmail(email)
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
            val favoriteRestaurantDao = database.favoriteRestaurantDao()
            val neverAgainRestaurantDao = database.neverAgainRestaurantDao()
            val restaurantRepository = RestaurantRepository(
                restaurantDao,
                favoriteRestaurantDao,
                neverAgainRestaurantDao
            )
            val restaurantRatingRepository = RestaurantRatingRepository(restaurantRatingDao)
            val restaurantManager = RestaurantManager(restaurantRepository, restaurantRatingRepository)
            val prefsManager = UserPreferencesManager(application)
            val authService = FirebaseAuthService()
            val sessionManager = SessionManager(authService)
            val userIdFlow = sessionManager.sessionState.map { it?.id ?: "" }
            val biometricService = BiometricService(application)
            val authManager = AuthManager(biometricService, prefsManager)
            val swipeManager = SwipeManager(userIdFlow, restaurantRepository, restaurantManager)
            val ratingManager = RatingManager(restaurantRatingRepository)
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
                restaurantRepository = restaurantRepository,
                restaurantManager = restaurantManager,
                restaurantRatingRepository =  restaurantRatingRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
