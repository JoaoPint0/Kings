package com.groktor.kings.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "battles")
data class Battle(
        @PrimaryKey
        @ColumnInfo(name = "_id") val _id: String,
        @ColumnInfo(name ="name") val name: String,
        @ColumnInfo(name ="pictureUrl") val pictureUrl: String?,
        @ColumnInfo(name ="startsAt") val startsAt: Date,
        @ColumnInfo(name ="finishesAt") val finishesAt: Date,
        @ColumnInfo(name ="privateBattle") val privateBattle : Boolean?,
        @ColumnInfo(name ="buyInAmount") val buyInAmount: Int,
        @ColumnInfo(name ="buyInCurrency") val buyInCurrency : String?,
        @ColumnInfo(name ="prize") val prizePot: Long)