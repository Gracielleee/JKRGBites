<div align="center">

<img src="https://github.com/rdashnox/JKRGBites/blob/main/app/src/main/res/drawable/Official_JRKGBites_NoBG.png" width="400px" alt="JRKGBites logo"/><br />

**"Just the Right Kind of Grub"**

<br/>

[![GitHub stars](https://img.shields.io/github/stars/rdashnox/JKRGBites?style=for-the-badge)](https://github.com/rdashnox/JKRGBites/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/rdashnox/JKRGBites?style=for-the-badge)](https://github.com/rdashnox/JKRGBites/network)
[![GitHub issues](https://img.shields.io/github/issues/rdashnox/JKRGBites?style=for-the-badge)](https://github.com/rdashnox/JKRGBites/issues)
[![GitHub license](https://img.shields.io/github/license/rdashnox/JKRGBites?style=for-the-badge)](LICENSE)

</div>


# About

&emsp; **JRKGBites** is an Android application designed to eliminate the indecision of choosing where to eat. It offers an interactive and personalized experience to help users find the perfect dining spot based on their cravings, mood, and preferences.

This application aims to solve the common problem of "decision fatigue" when it comes to dining. By providing a fun, gesture-based interface and smart recommendations, JRKG Bites streamlines the process of discovering and selecting a restaurant.

The project is built using Kotlin and follows a modern MVVM-like architecture (View, ViewModel, Domain, Data) to ensure a clean, scalable, and maintainable codebase.

</br>

## Features
JRKGBites provides the following features:

- **🛡️ Authentication:** Secure sign-up and login with email/password and biometric (fingerprint) options.  

- **🫳 Gesture-Based Interface:** An intuitive Tinder-style swipe system to manage your restaurant choices:  
&emsp;&emsp;&emsp;  ⬆️ Swipe Up: Save a restaurant to your Favorites.  
&emsp;&emsp;&emsp;  ⬇️ Swipe Down: Add a restaurant to your "Never Again" list.  
&emsp;&emsp;&emsp;  ⬅️ Swipe Left: Discard for now.  
&emsp;&emsp;&emsp;  ➡️ Swipe Right: View restaurant details.  

- **🔀 Shake to Decide:** A "roulette" feature that randomly picks a restaurant for you when you shake your device.  

- **📍 Location-Aware:** Filters restaurants based on your proximity, with options to see details and get directions via Google Maps.  

- **🔍 Comprehensive Search:** Easily search the entire restaurant database, including those you've previously dismissed.  

- **👤 User Profile:** View your stats, manage preferences, and access app settings.  

- **📂 Offline Capability:** Works with a local `JRKGBites.json` file, ensuring the app is functional even without an internet connection.  

</br>

## Built With

[![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android Jetpack](https://img.shields.io/badge/Android_Jetpack-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/jetpack)
[![Google Maps API](https://img.shields.io/badge/Google_Maps_API-007396?style=for-the-badge&logo=java&logoColor=white)](https://developers.google.com/maps)
[![Gson](https://img.shields.io/badge/Gson-007396?style=for-the-badge&logo=java&logoColor=white)](https://github.com/google/gson)
[![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)](https://gradle.org/)

</br>

## Getting Started

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

</br>

## Project Structure

The project is organized into four main layers, promoting a separation of concerns:

-   `view`: The UI layer, primarily handled in `MainActivity.kt`.
-   `viewmodel`: The `MainViewModel.kt` acts as the brain of the UI, managing state and handling user interactions.
-   `domain`: Contains the core business logic in manager classes like `SwipeManager`, `RestaurantPicker`, and `AuthManager`.
-   `data`: Manages all data sources, including the `RestaurantRepository` (for `JRKGBites.json`) and `UserPreferencesManager`.

## Team

</br>

<div align="center">

<table>
  <tr>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/157578925?v=4" width="100px" alt="GitHub profile picture"/><br />
      <b>Ralph(<a href="https://github.com/rdashnox">rdashnox</a>)</b><br />
       <p> Product Owner </p>
    </td>
     <td align="center">
      <img src="https://avatars.githubusercontent.com/u/158192154?v=4" width="100px" alt="GitHub profile picture"/><br />
      <b>J.R(<a href="https://github.com/Gyabu">Gyabu</a>)</b><br />
      <p> Scrum Master </p>
    </td>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/158242680?v=4" width="100px" alt="GitHub profile picture"/><br />
      <b>Gracielle(<a href="https://github.com/Gracielleee">Gracielleee</a>)</b><br />
      <p> Developer </p>
    </td>   
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/162974622?v=4" width="100px" alt="GitHub profile picture"/><br />
      <b>Krizia(<a href="https://github.com/k358k">k358k</a>)</b><br />
      <p> Developer </p>
    </td>
  </tr>
</table>

</br>

𓅰 𓅬 𓅭 𓅮 𓅯 </br>
Project Link: https://github.com/rdashnox/JRKGBites

</div>
