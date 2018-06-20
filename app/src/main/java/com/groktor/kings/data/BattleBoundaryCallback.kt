package com.groktor.kings.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.groktor.kings.api.KingsService
import com.groktor.kings.api.searchBattles
import com.groktor.kings.db.KingsLocalCache
import com.groktor.kings.model.Battle

class BattleBoundaryCallback(
        private val service: KingsService,
        private val cache: KingsLocalCache
) : PagedList.BoundaryCallback<Battle>() {

    private val _networkErrors = MutableLiveData<String>()

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Battle) {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        searchBattles(service, { battles ->
            cache.insertBattle(battles, {
                isRequestInProgress = false
            })
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }
}