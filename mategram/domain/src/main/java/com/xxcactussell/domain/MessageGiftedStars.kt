package com.xxcactussell.domain

data class MessageGiftedStars(
    val gifterUserId: Long,
    val receiverUserId: Long,
    val currency: String,
    val amount: Long,
    val cryptocurrency: String,
    val cryptocurrencyAmount: Long,
    val starCount: Long,
    val transactionId: String,
    val sticker: Sticker? = null
) : MessageContent
