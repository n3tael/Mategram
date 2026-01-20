package com.xxcactussell.domain

data class MessageGiveawayPrizeStars(
    val starCount: Long,
    val transactionId: String,
    val boostedChatId: Long,
    val giveawayMessageId: Long,
    val isUnclaimed: Boolean,
    val sticker: Sticker? = null
) : MessageContent
