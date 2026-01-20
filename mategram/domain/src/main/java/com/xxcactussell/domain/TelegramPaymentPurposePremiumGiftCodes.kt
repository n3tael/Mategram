package com.xxcactussell.domain

data class TelegramPaymentPurposePremiumGiftCodes(
    val boostedChatId: Long,
    val currency: String,
    val amount: Long,
    val userIds: LongArray,
    val monthCount: Int,
    val text: FormattedText
) : TelegramPaymentPurpose
