package com.xxcactussell.domain

data class PushMessageContentGiveaway(
    val winnerCount: Int,
    val prize: GiveawayPrize? = null,
    val isPinned: Boolean
) : PushMessageContent
