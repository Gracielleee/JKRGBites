package com.jrkg.jrkgbites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.data.UserPreferencesManager
import com.jrkg.jrkgbites.data.source.FakeAuthService
import com.jrkg.jrkgbites.domain.*
import com.jrkg.jrkgbites.services.BiometricService
import com.jrkg.jrkgbites.services.ShakeDetector
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import com.jrkg.jrkgbites.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shakeDetector: ShakeDetector

    private lateinit var sessionTextView: TextView
    private lateinit var loginButton: Button
    private lateinit var logoutButton: Button
    private lateinit var biometricSwitch: Switch
    private lateinit var requiredAuthTextView: TextView
    private lateinit var restaurantNameTextView: TextView
    private lateinit var buttonUp: Button
    private lateinit var buttonDown: Button
    private lateinit var buttonLeft: Button
    private lateinit var buttonRight: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        sessionTextView = findViewById(R.id.session_text)
        loginButton = findViewById(R.id.login_button)
        logoutButton = findViewById(R.id.logout_button)
        biometricSwitch = findViewById(R.id.biometric_switch)
        requiredAuthTextView = findViewById(R.id.required_auth_text)
        restaurantNameTextView = findViewById(R.id.restaurant_name_text)
        buttonUp = findViewById(R.id.button_up)
        buttonDown = findViewById(R.id.button_down)
        buttonLeft = findViewById(R.id.button_left)
        buttonRight = findViewById(R.id.button_right)

        // Dependency Setup
        val context = applicationContext
        val repository = RestaurantRepository(context)
        val prefsManager = UserPreferencesManager(context)
        val biometricService = BiometricService(context)
        shakeDetector = ShakeDetector(context)
        val authService = FakeAuthService()

        // Domain Layer
        val picker = RestaurantPicker(repository)
        val swipeManager = SwipeManager(repository)
        val searchManager = SearchManager(repository)
        val ratingManager = RatingManager(swipeManager)
        val authManager = AuthManager(biometricService, prefsManager)
        val sessionManager = SessionManager(authService)

        // ViewModel
        val factory = MainViewModelFactory(
            picker,
            swipeManager,
            searchManager,
            ratingManager,
            authManager,
            prefsManager,
            sessionManager
        )
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        // Set up Listeners
        loginButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.login("test@jrkg.com", "password123").collect {
                    // Handle login result if necessary
                }
            }
        }
        logoutButton.setOnClickListener { viewModel.logout() }
        biometricSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setBiometricPreference(isChecked)
        }
        buttonUp.setOnClickListener {
            viewModel.deck.value.firstOrNull()?.let { restaurant ->
                viewModel.swipe(restaurant, SwipeDirection.UP)
            }
        }
        buttonDown.setOnClickListener {
            viewModel.deck.value.firstOrNull()?.let { restaurant ->
                viewModel.swipe(restaurant, SwipeDirection.DOWN)
            }
        }
        buttonLeft.setOnClickListener {
            viewModel.deck.value.firstOrNull()?.let { restaurant ->
                viewModel.swipe(restaurant, SwipeDirection.LEFT)
            }
        }
        buttonRight.setOnClickListener {
            viewModel.deck.value.firstOrNull()?.let { restaurant ->
                viewModel.swipe(restaurant, SwipeDirection.RIGHT)
            }
        }

        // Observe ViewModel LiveData/Flows
        lifecycleScope.launch {
            viewModel.sessionState.collect { sessionState ->
                sessionTextView.text = "Session: ${sessionState?.preferredName ?: "Logged Out"}"
                loginButton.isEnabled = sessionState == null
                logoutButton.isEnabled = sessionState != null
            }
        }
        lifecycleScope.launch {
            viewModel.requiredAuthMethod.collect { authMethod ->
                requiredAuthTextView.text = "Required Auth: $authMethod"
            }
        }
        lifecycleScope.launch {
            viewModel.isBiometricPreferenceEnabled.collect { isEnabled ->
                biometricSwitch.isChecked = isEnabled
            }
        }
        lifecycleScope.launch {
            viewModel.deck.collect { deck ->
                restaurantNameTextView.text = deck.firstOrNull()?.name ?: "No more restaurants!"
                // Enable/disable swipe buttons based on whether there's a restaurant
                val hasRestaurant = deck.firstOrNull() != null
                buttonUp.isEnabled = hasRestaurant
                buttonDown.isEnabled = hasRestaurant
                buttonLeft.isEnabled = hasRestaurant
                buttonRight.isEnabled = hasRestaurant
            }
        }

        // ShakeDetector setup
        shakeDetector.setOnShakeListener { viewModel.onShakeDetected() }
    }

    override fun onResume() {
        super.onResume()
        shakeDetector.start()
    }

    override fun onPause() {
        super.onPause()
        shakeDetector.stop()
    }
}
