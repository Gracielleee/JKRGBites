# JRKG Bites

**"Just the Right Kind of Grub"**

JRKGBites is an Android application designed to eliminate the indecision of choosing where to eat. It offers an interactive and personalized experience to help users find the perfect dining spot based on their cravings, mood, and preferences.

--- About The Project ---

This application aims to solve the common problem of "decision fatigue" when it comes to dining. By providing a fun, gesture-based interface and smart recommendations, JRKG Bites streamlines the process of discovering and selecting a restaurant.

The project is built using Kotlin and follows a modern MVVM-like architecture (View, ViewModel, Domain, Data) to ensure a clean, scalable, and maintainable codebase.

--- Features ---

-   **Authentication:** Secure sign-up and login with email/password and biometric (fingerprint) options.
-   **Gesture-Based Interface:** An intuitive Tinder-style swipe system to manage your restaurant choices:
    -   **Swipe Up:** Save a restaurant to your Favorites.
    -   **Swipe Down:** Add a restaurant to your "Never Again" list.
    -   **Swipe Left:** Discard for now.
    -   **Swipe Right:** View restaurant details.
-   **Shake to Decide:** A "roulette" feature that randomly picks a restaurant for you when you shake your device.
-   **Location-Aware:** Filters restaurants based on your proximity, with options to see details and get directions via Google Maps.
-   **Comprehensive Search:** Easily search the entire restaurant database, including those you've previously dismissed.
-   **User Profile:** View your stats, manage preferences, and access app settings.
-   **Offline Capability:** Works with a local `JRKGBites.json` file, ensuring the app is functional even without an internet connection.

--- Built With ---

-   [Kotlin](https://kotlinlang.org/)
-   [Android Jetpack](https://developer.android.com/jetpack)
-   [Google Maps API](https://developers.google.com/maps)
-   [Gson](https://github.com/google/gson)
-   [Gradle](https://gradle.org/)

--- Getting Started ---

To get a local copy up and running, follow these simple steps.

### Prerequisites

-   Android Studio (latest stable version recommended)
-   An Android device or emulator

### Installation

1.  Clone the repo
    git clone https://github.com/rdashnox/JRKGBites.git
2.  Open the project in Android Studio.
3.  Let Gradle sync and download the required dependencies.
4.  Run the app on your selected device or emulator.

--- Project Structure ---

The project is organized into four main layers, promoting a separation of concerns:

-   **`view`**: The UI layer, primarily handled in `MainActivity.kt`.
-   **`viewmodel`**: The `MainViewModel.kt` acts as the brain of the UI, managing state and handling user interactions.
-   **`domain`**: Contains the core business logic in manager classes like `SwipeManager`, `RestaurantPicker`, and `AuthManager`.
-   **`data`**: Manages all data sources, including the `RestaurantRepository` (for `JRKGBites.json`) and `UserPreferencesManager`.

--- Contact ---

Product Owner - Ralph
Scrum Master - Gracielle
Developer - Krizia
Developer - J.R

Project Link: [https://github.com/rdashnox/JRKGBites](https://github.com/rdashnox/JRKGBites)