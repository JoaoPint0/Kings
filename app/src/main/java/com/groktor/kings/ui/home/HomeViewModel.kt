package com.groktor.kings.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.groktor.kings.data.KingsRepository
import com.groktor.kings.model.Battle

class HomeViewModel(repository: KingsRepository) : ViewModel()  {


    val battles: LiveData<PagedList<Battle>> = repository.searchAllBattles()


}
