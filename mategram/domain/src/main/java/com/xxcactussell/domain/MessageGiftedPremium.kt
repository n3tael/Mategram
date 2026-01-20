package com.xxcactussell.domain

data class MessageGiftedPremium(
    val gifterUserId: Long,
    val receiverUserId: Long,
    val text: FormattedText,
    val currency: String,
    val amount: Long,
    val cryptocurrency: String,
    val cryptocurrencyAmount: Long,
    val monthCount: Int,
    val sticker: Sticker? = null
) : MessageContent
