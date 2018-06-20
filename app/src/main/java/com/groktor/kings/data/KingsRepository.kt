package com.groktor.kings.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.groktor.kings.api.KingsService
import com.groktor.kings.api.searchAccount
import com.groktor.kings.api.searchBattleById
import com.groktor.kings.api.searchPlayers
import com.groktor.kings.db.KingsLocalCache
import com.groktor.kings.model.Account
import com.groktor.kings.model.Battle
import com.groktor.kings.model.Player

/**
 * Repository class that works with local and remote data sources.
 */
class KingsRepository(
        private val service: KingsService,
        private val cache: KingsLocalCache
) {

    fun searchAllBattles(): LiveData<PagedList<Battle>> {
        Log.d("Repository", "all battles")

        // Get data source factory from the local cache
        val dataSourceFactory = cache.getAllBattles()


        // Construct the boundary callback
        val boundaryCallback = BattleBoundaryCallback(service, cache)

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        Log.e("Repository" , data.value?.size.toString())
        return data
    }

    fun searchBattle(id: String): LiveData<Battle>{

        val battle = MutableLiveData<Battle>()

        searchBattleById(service,id,{
            battle.value = it
            Log.e("Repository",  "search Battle response")
        },{
            Log.e("Repository", "searchBattle error")
        })

        return battle
    }


    fun searchAccount(query: String): LiveData<Account> {
        Log.d("Repository", "New query: $query")

        val account = MutableLiveData<Account>()

        searchAccount(service,query,{
            account.value = it
            Log.e("Repository",  "search Account response")
        },{
            Log.e("Repository", "search Account error")
        })

        return account
    }

    fun searchPlayers(): LiveData<List<Player>> {
        Log.d("Repository", "playerSearch")

        val players = MutableLiveData<List<Player>>()

        searchPlayers(service,{
            players.value = it
            Log.e("Repository",  "search Players response")
        },{
            Log.e("Repository", "search Players error")
        })

        return players
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 3
    }
}