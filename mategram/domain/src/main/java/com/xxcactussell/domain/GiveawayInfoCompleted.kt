package com.xxcactussell.domain

data class GiveawayInfoCompleted(
    val creationDate: Int,
    val actualWinnersSelectionDate: Int,
    val wasRefunded: Boolean,
    val isWinner: Boolean,
    val winnerCount: Int,
    val activationCount: Int,
    val giftCode: String,
    val wonStarCount: Long
) : GiveawayInfo
