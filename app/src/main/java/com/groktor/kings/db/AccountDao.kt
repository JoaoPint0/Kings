package com.groktor.kings.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.groktor.kings.model.Account


/**
 * Room data access object for accessing the [Account] table.
 */
@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: Account)

    @Query("SELECT * FROM accounts WHERE (userName LIKE :queryString)  ORDER BY _id ASC")
    fun accountByUsername(queryString: String): LiveData<Account>
}