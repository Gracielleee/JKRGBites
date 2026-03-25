package com.jrkg.jrkgbites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrkg.jrkgbites.model.Restaurant
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.TimeUnit

class RouletteViewModel : ViewModel() {

    private val _spinsLeft = MutableStateFlow(1) // Start with 1 free spin
    val spinsLeft: StateFlow<Int> = _spinsLeft

    private val _isSpinning = MutableStateFlow(false)
    val isSpinning: StateFlow<Boolean> = _isSpinning

    private val _selectedRestaurant = MutableStateFlow<Restaurant?>(null)
    val selectedRestaurant: StateFlow<Restaurant?> = _selectedRestaurant

    private val _timeUntilReset = MutableStateFlow("")
    val timeUntilReset: StateFlow<String> = _timeUntilReset

    private val _maxSpins = 3 // Total possible spins (1 Free + 2 Ads)
    val maxSpins: Int get() = _maxSpins

    init {
        startCountdownTimer()
    }

    fun setSpinsLeft(count: Int) {
        _spinsLeft.value = count
    }

    private fun startCountdownTimer() {
        viewModelScope.launch {
            while (true) {
                _timeUntilReset.value = calculateTimeUntilMidnight()
                delay(1000 * 60) // Update every minute
            }
        }
    }

    private fun calculateTimeUntilMidnight(): String {
        val now = Calendar.getInstance()
        val midnight = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        val diff = midnight.timeInMillis - now.timeInMillis
        val hours = TimeUnit.MILLISECONDS.toHours(diff)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60
        return String.format("%dh %02dm", hours, minutes)
    }

    fun canSpin(): Boolean {
        return _spinsLeft.value > 0 && !_isSpinning.value
    }

    fun startSpin() {
        if (_spinsLeft.value > 0) {
            _isSpinning.value = true
            _spinsLeft.value -= 1
        }
    }

    fun addSpinFromAd() {
        if (_spinsLeft.value < _maxSpins) {
            _spinsLeft.value += 1
        }
    }

    fun onSpinFinished() {
        _isSpinning.value = false
    }
}
