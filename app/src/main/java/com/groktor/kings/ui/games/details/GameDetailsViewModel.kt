package com.groktor.kings.ui.games.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.groktor.kings.data.KingsRepository
import com.groktor.kings.model.Battle

class GameDetailsViewModel(private val repository: KingsRepository) : ViewModel() {

    private val battleId = MutableLiveData<String>()
    val battle = MutableLiveData<Battle>()
    val players = repository.searchPlayers()

    fun searchBattle(id: String) :LiveData<Battle> {
        Log.e("GameDetailsViewModel", "search Battle $id")
        battleId.value = id
        return repository.searchBattle(id)
    }

}
