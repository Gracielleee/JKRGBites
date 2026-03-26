package com.jrkg.jrkgbites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jrkg.jrkgbites.data.RouletteRepository

class RouletteViewModelFactory(
    private val userId: String,
    private val rouletteRepository: RouletteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RouletteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RouletteViewModel(userId, rouletteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
