package com.jrkg.jrkgbites.data

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.jrkg.jrkgbites.model.SpinSession
import kotlinx.coroutines.tasks.await

class RouletteRepository {
    private val db = FirebaseFirestore.getInstance()

    companion object {
        private const val TAG = "RouletteRepository"
        private const val SPIN_SESSIONS = "spinSessions"
        private const val SPIN_HISTORY = "spinHistory"
    }

    suspend fun getSpinSession(userId: String): SpinSession? {
        return try {
            val doc = db.collection(SPIN_SESSIONS).document(userId).get().await()
            if (doc.exists()) {
                SpinSession(
                    spinsUsedToday = doc.getLong("spinsUsedToday")?.toInt() ?: 0,
                    adsWatchedToday = doc.getLong("adsWatchedToday")?.toInt() ?: 0
                )
            } else {
                // Initialize if it doesn't exist
                val initial = SpinSession(0, 0)
                db.collection(SPIN_SESSIONS).document(userId).set(initial, SetOptions.merge()).await()
                initial
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching spin session", e)
            null
        }
    }

    suspend fun logSpin(userId: String, restaurantId: String) {
        try {
            val batch = db.batch()
            val historyRef = db.collection(SPIN_HISTORY).document(userId).collection("spins").document()
            val sessionRef = db.collection(SPIN_SESSIONS).document(userId)

            val spinData = hashMapOf(
                "restaurantId" to restaurantId,
                "timestamp" to FieldValue.serverTimestamp()
            )

            batch.set(historyRef, spinData)
            batch.update(sessionRef, "spinsUsedToday", FieldValue.increment(1))

            batch.commit().await()
            Log.d(TAG, "Successfully logged spin for user: $userId")
        } catch (e: Exception) {
            Log.e(TAG, "Error logging spin to Firestore", e)
        }
    }

    suspend fun logAdCompletion(userId: String) {
        try {
            db.collection(SPIN_SESSIONS).document(userId)
                .update("adsWatchedToday", FieldValue.increment(1)).await()
            Log.d(TAG, "Successfully logged ad completion for user: $userId")
        } catch (e: Exception) {
            Log.e(TAG, "Error logging ad completion", e)
        }
    }
}
