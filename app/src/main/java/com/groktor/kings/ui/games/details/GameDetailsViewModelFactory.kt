package com.groktor.kings.ui.games.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.groktor.kings.data.KingsRepository

/**
 * Factory for ViewModels
 */
class GameDetailsViewModelFactory(private val repository: KingsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}