package com.groktor.kings.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.groktor.kings.model.Player
import java.util.*


class BattleTypesConverter {

    @TypeConverter
    fun fromString(value: String): List<Player> {
        val listType = object : TypeToken<List<Player>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListtoString(list: List<Player>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return (if (date == null) null else date.time)?.toLong()
    }
}