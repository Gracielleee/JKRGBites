package com.jrkg.jrkgbites.domain

import com.jrkg.jrkgbites.data.RouletteRepository
import kotlinx.coroutines.delay

class AdManager(private val repository: RouletteRepository) {
    
    /**
     * Simulates watching an ad and logs the completion to Firestore.
     * Returns true if successful.
     */
    suspend fun watchAd(userId: String): Boolean {
        // Simulate the 5-second ad as requested

        repository.logAdCompletion(userId)
        return true
    }
}
