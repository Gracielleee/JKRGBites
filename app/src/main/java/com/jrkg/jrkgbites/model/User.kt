package com.jrkg.jrkgbites.model

import com.google.firebase.Timestamp

enum class SubscriptionStatus {
    NONE,   // Haven't started trial or subscribed
    TRIAL,  // Currently using the 15-day free period
    ACTIVE  // Paid member
}

/**
 * Represents a logged-in user in the application with subscription tracking.
 */
data class User(
    val id: String = "",
    val email: String = "",
    val preferredName: String = "",
    val subscriptionStatus: SubscriptionStatus = SubscriptionStatus.NONE,
    val trialStartedAt: Timestamp? = null
)
