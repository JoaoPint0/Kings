package com.groktor.kings.db

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.groktor.kings.model.Account
import com.groktor.kings.model.Battle
import java.util.concurrent.Executor

/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */
class KingsLocalCache(
        private val battleDao: BattleDao,
        private val accountDao: AccountDao,
        private val ioExecutor: Executor
) {

    /**
     * Insert a list of battles in the database, on a background thread.
     */
    fun insertBattle(battles: List<Battle>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            Log.d("GithubLocalCache", "inserting ${battles.size} battles")
            battleDao.insert(battles)
            insertFinished()
        }
    }

    /**
     * Insert a list of battles in the database, on a background thread.
     */
    fun insertAccount(account: Account) {
        ioExecutor.execute {
            Log.d("GithubLocalCache", "inserting ${account._id} ")
            accountDao.insert(account)
        }
    }

    /**
     * Request a LiveData<List<Battle>> from the Dao, based on a battle name. If the name contains
     * multiple words separated by spaces, then we're emulating the Kings API behavior and allow
     * any characters between the words.
     * @param name repository name
     */
    fun getAllBattles(): DataSource.Factory<Int, Battle> {
        // appending '%' so we can allow other characters to be before and after the query string
        return battleDao.allBattles()
    }

    /**
     * Request a LiveData<List<Battle>> from the Dao, based on a battle name. If the name contains
     * multiple words separated by spaces, then we're emulating the Kings API behavior and allow
     * any characters between the words.
     * @param name repository name
     */
    fun accountById(id: String): LiveData<Account> {
        // appending '%' so we can allow other characters to be before and after the query string
        val query = "%${id.replace(' ', '%')}%"
        return accountDao.accountByUsername(id)
    }

}