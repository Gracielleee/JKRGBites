package com.jrkg.jrkgbites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.data.UserPreferencesManager
import com.jrkg.jrkgbites.data.source.FakeAuthService
import com.jrkg.jrkgbites.domain.*
import com.jrkg.jrkgbites.services.BiometricService
import com.jrkg.jrkgbites.services.ShakeDetector
import com.jrkg.jrkgbites.ui.theme.JRKGBitesTheme
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import com.jrkg.jrkgbites.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JRKGBitesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JRKGBitesApp()
                }
            }
        }
    }
}

@Composable
fun JRKGBitesApp() {
    // Dependency Setup
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    // Data and Service Layers
    val repository = remember { RestaurantRepository(context) }
    val prefsManager = remember { UserPreferencesManager(context) }
    val biometricService = remember { BiometricService(context) }
    val shakeDetector = remember { ShakeDetector(context) }
    val authService = remember { FakeAuthService() }

    // Domain Layer
    val picker = remember { RestaurantPicker(repository) }
    val swipeManager = remember { SwipeManager(repository) }
    val searchManager = remember { SearchManager(repository) }
    val ratingManager = remember { RatingManager(swipeManager) }
    val authManager = remember { AuthManager(biometricService, prefsManager) }
    val sessionManager = remember { SessionManager(authService) }

    // ViewModel
    val viewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory(
            picker,
            swipeManager,
            searchManager,
            ratingManager,
            authManager,
            prefsManager,
            sessionManager)
    )

    // State Collection -- this block makes the UI reactive.
    val sessionState by viewModel.sessionState.collectAsState()
    val requiredAuthMethod by viewModel.requiredAuthMethod.collectAsState()
    val isBiometricPreferenceEnabled by viewModel.isBiometricPreferenceEnabled.collectAsState()
    val topRestaurant = viewModel.deck.collectAsState().value.firstOrNull()

    // ShakeDetector trigger for roulette
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                shakeDetector.start()
            } else if (event == Lifecycle.Event.ON_PAUSE) {
                shakeDetector.stop()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        shakeDetector.setOnShakeListener { viewModel.onShakeDetected() }
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            shakeDetector.stop()
        }
    }

    // --- UI ---
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // --- Session and Auth UI ---
        Text("Session: ${sessionState?.preferredName ?: "Logged Out"}")
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                coroutineScope.launch {
                    viewModel.login("test@jrkg.com", "password123").collect {
                        // In a real app, you'd handle the result (loading, error)
                    }
                }
            }, enabled = sessionState == null) { Text("Login") }
            Button(onClick = { viewModel.logout() }, enabled = sessionState != null) { Text("Logout") }
        }
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Enable Biometric Login:")
            Spacer(Modifier.width(8.dp))
            Switch(
                checked = isBiometricPreferenceEnabled,
                onCheckedChange = { viewModel.setBiometricPreference(it) }
            )
        }
        Text(
            text = "Required Auth: $requiredAuthMethod",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // --- App Features UI ---
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier.height(150.dp).fillMaxWidth(0.8f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = topRestaurant?.name ?: "No more restaurants!",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }
        // Gestures for Tinder-type selection
        Spacer(modifier = Modifier.height(16.dp))
        if (topRestaurant != null) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { viewModel.swipe(topRestaurant, SwipeDirection.UP) }) { Text("Up") }
                Button(onClick = { viewModel.swipe(topRestaurant, SwipeDirection.DOWN) }) { Text("Down") }
                Button(onClick = { viewModel.swipe(topRestaurant, SwipeDirection.LEFT) }) { Text("Left") }
                Button(onClick = { viewModel.swipe(topRestaurant, SwipeDirection.RIGHT) }) { Text("Right") }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JRKGBitesTheme {
        Text("JRKGBites App Preview")
    }
}
