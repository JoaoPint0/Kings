package com.groktor.kings.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "accounts")
data class Account(
        @PrimaryKey
        @field:SerializedName("_id") val _id: String,
        @field:SerializedName("userName") val userName: String,
        @field:SerializedName("profilePicUrl") val profilePicUrl: String)
