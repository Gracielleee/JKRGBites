package com.jrkg.jrkgbites

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Toast
import com.jrkg.jrkgbites.services.LocationService
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import com.jrkg.jrkgbites.viewmodel.MainViewModelFactory
import com.jrkg.jrkgbites.viewmodel.StartDestination
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController
    private lateinit var locationService: LocationService
    private var navGraphAttached = false

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                fetchAndSaveLocation()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                fetchAndSaveLocation()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. Splash Screen setup
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2. ViewModel initialization
        val factory = MainViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        // 3. Location Service
        locationService = LocationService(this)

        // Keep splash on screen until we know where to go (Fixes flicker)
        splashScreen.setKeepOnScreenCondition { viewModel.startDestination.value == null }

        // 4. Navigation setup
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // 5. Reactive Listeners
        setupObservers()

        // 6. Bottom Navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

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

        // 7. Check for Location Permissions on Startup (One-time check)
        checkLocationPermissions()
    }

    private fun checkLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationPermissionRequest.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ))
        } else {
            fetchAndSaveLocation()
        }

    }

    private fun fetchAndSaveLocation() {
        lifecycleScope.launch {
            val location = locationService.getCurrentLocation()
            location?.let {
                viewModel.updateUserLocation(it.latitude, it.longitude)
                // Add a toast for debugging
                Toast.makeText(this@MainActivity, "Location saved: ${it.latitude}, ${it.longitude}", Toast.LENGTH_SHORT).show()
                } ?: run {
                // Add a toast if location is null
                Toast.makeText(this@MainActivity, "Failed to get current location. Please ensure GPS is enabled and try again.", Toast.LENGTH_LONG).show()
                // REMOVED: viewModel.updateUserLocation(0.0, 0.0) // Ensure default is set if location fails
                }        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                // Handle Initial Navigation (Login vs Main)
                launch {
                    viewModel.startDestination.collectLatest { destination ->
                        if (destination == null) return@collectLatest
                        attachNavGraph(destination)
                    }
                }

                // Handle Biometric/Auth Success Navigation
                launch {
                    viewModel.navigateToMainEvent.collectLatest {
                        // This makes the biometric sensor actually OPEN the app
                        navController.navigate(R.id.nav_picker)
                    }
                }
            }
        }
    }

    private fun attachNavGraph(destination: StartDestination) {
        if (navGraphAttached) return
        navGraphAttached = true

        val graph = navController.navInflater.inflate(R.navigation.nav_graph)

        // Map the enum to your actual NavGraph IDs
        val startId = when (destination) {
            StartDestination.LOGIN -> R.id.loginFragment
            StartDestination.MAIN -> R.id.nav_picker // Corrected to nav_picker based on your graph
        }

        graph.setStartDestination(startId)

        // FIXED: Added 'null' to handle the NavGraph object correctly
        navController.setGraph(graph, null)
    }
}