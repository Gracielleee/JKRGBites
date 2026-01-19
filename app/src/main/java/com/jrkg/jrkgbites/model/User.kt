package com.jrkg.jrkgbites.model

/**
 * Represents the user's customizable preferences for restaurant recommendations.
 */
data class UserPreferences(
    val cuisineTypes: List<String> = emptyList(),
    val maxDistance: Int // The maximum distance in meters for recommendations
)

/**
 * Represents a logged-in user in the application.
 */
data class User(
    val userId: String,
    val email: String,
    val preferredName: String, // As mentioned in the sign-up function description
    val favorites: Set<String> = emptySet(), // A Set is used to ensure no duplicate favorite IDs
    val preferences: UserPreferences
)
