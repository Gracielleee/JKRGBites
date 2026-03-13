package com.jrkg.jrkgbites.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jrkg.jrkgbites.AppDatabase
import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.data.UserPreferencesManager
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
import com.jrkg.jrkgbites.data.RestaurantRatingRepository
import com.jrkg.jrkgbites.data.source.FirebaseAuthService
import com.jrkg.jrkgbites.services.BiometricService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
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
    private val restaurantRepository: RestaurantRepository,
    private val restaurantManager: RestaurantManager
) : ViewModel() {

    // --- Session Manager State ---
    val sessionState: StateFlow<User?> = sessionManager.sessionState
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    init {
        // Initialize managers that collect from Flows.
        swipeManager.init(viewModelScope)
        restaurantPicker.init(viewModelScope)
        searchManager.init(viewModelScope)

        // Initial data loading for RoomDB
        viewModelScope.launch {
//            restaurantRepository.refreshRestaurants(application)
            // 1. Observe the sessionState Flow
            sessionState.collectLatest { user ->
                when {
                    user == null -> {
                    //Fallback
                        restaurantRepository.pullFreshFromJSON(application)
                    }
                    user.id.isNotEmpty() -> {
                        // User restored from session OR just logged in
                        restaurantRepository.syncRestaurants(user.id)
                    }
                }
            }
        }
    }

    // --- Toast ---
    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

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

    // --- Shake Picker State ---
    private val _pickedResult = MutableStateFlow<String?>(null)
    val pickedResult: StateFlow<String?> = _pickedResult.asStateFlow()

    // --- Swipe Manager State ---
    // Use 'by lazy' to ensure these are only accessed after SwipeManager.init(viewModelScope) has been called.
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

    /**
     * Re-applies current session filters to the deck. Useful when
     * returning to the picker so that previously swiped restaurants
     * (including RIGHT swipes) are not shown again.
     */
    fun refreshDeck() {
        swipeManager.updateDeck()
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

    fun submitRating(restaurantId: String, rating: Int, comment: String) {
        viewModelScope.launch {
            ratingManager.submitRating(restaurantId, rating.toInt(), comment)
            _toastMessage.value = "Rating submitted"
        }
    }

    fun createRestaurant(restaurant: Restaurant) {
        viewModelScope.launch {
            restaurantRepository.createRestaurant(restaurant, sessionState.value?.id ?: "")
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
            val favoriteRestaurantDao = database.favoriteRestaurantDao()
            val neverAgainRestaurantDao = database.neverAgainRestaurantDao()
            val restaurantRepository = RestaurantRepository(
                restaurantDao,
                favoriteRestaurantDao,
                neverAgainRestaurantDao
            )
            val restaurantRatingRepository = RestaurantRatingRepository(restaurantRatingDao)
            val restaurantManager = RestaurantManager(restaurantDao, restaurantRepository)
            val prefsManager = UserPreferencesManager(application)
            val authService = FirebaseAuthService()
            val sessionManager = SessionManager(authService)
            val userIdFlow = sessionManager.sessionState.map { it?.id ?: "" }
            val biometricService = BiometricService(application)
            val authManager = AuthManager(biometricService, prefsManager)
            val swipeManager = SwipeManager(userIdFlow, restaurantRepository, restaurantManager)
            val ratingManager = RatingManager(restaurantRatingRepository, restaurantManager)
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
                restaurantManager = restaurantManager
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
