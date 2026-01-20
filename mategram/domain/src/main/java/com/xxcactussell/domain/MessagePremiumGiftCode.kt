package com.xxcactussell.domain

data class MessagePremiumGiftCode(
    val creatorId: MessageSender? = null,
    val text: FormattedText,
    val isFromGiveaway: Boolean,
    val isUnclaimed: Boolean,
    val currency: String,
    val amount: Long,
    val cryptocurrency: String,
    val cryptocurrencyAmount: Long,
    val monthCount: Int,
    val sticker: Sticker? = null,
    val code: String
) : MessageContent
