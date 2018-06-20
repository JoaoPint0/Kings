package com.groktor.kings.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "invites")
data class Invite(
        @PrimaryKey
        @field:SerializedName("code") val id: Long,
        @field:SerializedName("actor") val actor: Account,
        @field:SerializedName("battle") val battle: Battle,
        @field:SerializedName("target") val target: Account)