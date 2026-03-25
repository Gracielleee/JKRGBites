package com.jrkg.jrkgbites.domain

import com.google.firebase.Timestamp
import com.jrkg.jrkgbites.model.SubscriptionStatus
import com.jrkg.jrkgbites.model.User
import java.util.Calendar
import java.util.concurrent.TimeUnit

class SubscriptionManager {

    companion object {
        const val TRIAL_DURATION_DAYS = 15
    }

    /**
     * Determines if the user has access to Premium features (like Custom Tags).
     * Access is granted if they have an active subscription OR are within the 15-day trial.
     */
    fun hasPremiumAccess(user: User?): Boolean {
        if (user == null) return false
        
        // 1. Paid members always have access
        if (user.subscriptionStatus == SubscriptionStatus.ACTIVE) return true
        
        // 2. Check trial window
        if (user.subscriptionStatus == SubscriptionStatus.TRIAL) {
            val trialStart = user.trialStartedAt?.toDate() ?: return false
            val now = Calendar.getInstance().time
            
            val diffInMillies = now.time - trialStart.time
            val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillies)
            
            return diffInDays <= TRIAL_DURATION_DAYS
        }
        
        return false
    }

    /**
     * Returns the remaining days in the user's trial period.
     */
    fun getTrialDaysLeft(user: User?): Int {
        if (user?.subscriptionStatus != SubscriptionStatus.TRIAL) return 0
        val trialStart = user.trialStartedAt?.toDate() ?: return 0
        val now = Calendar.getInstance().time
        
        val diffInMillies = now.time - trialStart.time
        val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillies).toInt()
        
        return (TRIAL_DURATION_DAYS - diffInDays).coerceAtLeast(0)
    }
}
