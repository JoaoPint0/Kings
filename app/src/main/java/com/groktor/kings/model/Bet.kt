package com.groktor.kings.model

import java.util.*

data class Bet (val _id: Long,
                val market: String,
                val amountWagered: String,
                val currencyWagered: String,
                val scoreValue: String,
                val open: String,
                val won: String,
                val date: Date)