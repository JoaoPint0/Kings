package com.groktor.kings.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.groktor.kings.model.Battle

/**
 * Database schema that holds the list of battles.
 */
@Database(
        entities = [Battle::class],
        version = 1,
        exportSchema = false
)
@TypeConverters(BattleTypesConverter::class)
abstract class BattleDatabase : RoomDatabase() {

    abstract fun battlesDao(): BattleDao

    companion object {

        @Volatile
        private var INSTANCE: BattleDatabase? = null

        fun getInstance(context: Context): BattleDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        BattleDatabase::class.java, "Battles.db")
                        .build()
    }
}