package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.data.UserPreferencesManager
import com.jrkg.jrkgbites.data.source.FakeAuthService
import com.jrkg.jrkgbites.domain.*
import com.jrkg.jrkgbites.services.BiometricService
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import com.jrkg.jrkgbites.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Dependency Setup (kept as is, as it's the core logic setup)
        val context = applicationContext
        val repository = RestaurantRepository(context)
        val prefsManager = UserPreferencesManager(context)
        val biometricService = BiometricService(context)
        val authService = FakeAuthService() // ShakeDetector removed for now

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
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        // Setup Navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        // Hide BottomNavigationView on specific fragments
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.registerFragment, R.id.forgotPasswordDialog -> {
                    bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }


        // No more direct UI handling or observers in MainActivity for authentication/swipe
        // These functionalities will now be handled within their respective fragments
    }

    // Removed onResume and onPause as shakeDetector is no longer directly in MainActivity
}
