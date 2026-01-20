package com.xxcactussell.domain

data class MessageGiveawayWinners(
    val boostedChatId: Long,
    val giveawayMessageId: Long,
    val additionalChatCount: Int,
    val actualWinnersSelectionDate: Int,
    val onlyNewMembers: Boolean,
    val wasRefunded: Boolean,
    val prize: GiveawayPrize,
    val prizeDescription: String,
    val winnerCount: Int,
    val winnerUserIds: LongArray,
    val unclaimedPrizeCount: Int
) : MessageContent
