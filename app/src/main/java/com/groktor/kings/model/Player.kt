package com.groktor.kings.model

data class Player(
        val _id: String,
        val account: String,
        val score: Long,
        val betHistory : List<Bet>)