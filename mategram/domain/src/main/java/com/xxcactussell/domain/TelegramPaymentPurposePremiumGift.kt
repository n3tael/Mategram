package com.xxcactussell.domain

data class TelegramPaymentPurposePremiumGift(
    val currency: String,
    val amount: Long,
    val userId: Long,
    val monthCount: Int,
    val text: FormattedText
) : TelegramPaymentPurpose
