package com.xxcactussell.domain

data class ChatBoostSourceGiveaway(
    val userId: Long,
    val giftCode: String,
    val starCount: Long,
    val giveawayMessageId: Long,
    val isUnclaimed: Boolean
) : ChatBoostSource
