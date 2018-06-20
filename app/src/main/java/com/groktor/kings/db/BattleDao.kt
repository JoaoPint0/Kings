package com.groktor.kings.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.groktor.kings.model.Battle

/**
 * Room data access object for accessing the [Battle] table.
 */
@Dao
interface BattleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Battle>)

    @Query("SELECT * FROM battles ORDER BY _id ASC")
    fun allBattles(): DataSource.Factory<Int, Battle>

}