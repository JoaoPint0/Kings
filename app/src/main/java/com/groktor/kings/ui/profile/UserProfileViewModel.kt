package com.groktor.kings.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.groktor.kings.data.KingsRepository
import com.groktor.kings.model.Account
import com.groktor.kings.model.Battle

class UserProfileViewModel(private val repository: KingsRepository) : ViewModel() {


    val battles: LiveData<PagedList<Battle>> = repository.searchAllBattles()


    fun searchAccount(): LiveData<Account> {

        return repository.searchAccount("5b27dcec71169b02bd16bcbb")
    }
}
