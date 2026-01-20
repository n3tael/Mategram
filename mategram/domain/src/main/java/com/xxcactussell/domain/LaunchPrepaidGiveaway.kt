package com.xxcactussell.domain

data class LaunchPrepaidGiveaway(
    val giveawayId: Long,
    val parameters: GiveawayParameters,
    val winnerCount: Int,
    val starCount: Long
) : Function
