package com.groktor.kings.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "wallets")
data class Wallet(
        @PrimaryKey
        @field:SerializedName("id") val id: Long,
        @field:SerializedName("balance") val balance: Long,
        @field:SerializedName("owner") val owner: Account)