package com.xxcactussell.domain

data class PrepaidGiveaway(
    val id: Long,
    val winnerCount: Int,
    val prize: GiveawayPrize,
    val boostCount: Int,
    val paymentDate: Int
) : Object
