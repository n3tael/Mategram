package com.xxcactussell.domain

data class MessageGiveawayCompleted(
    val giveawayMessageId: Long,
    val winnerCount: Int,
    val isStarGiveaway: Boolean,
    val unclaimedPrizeCount: Int
) : MessageContent
