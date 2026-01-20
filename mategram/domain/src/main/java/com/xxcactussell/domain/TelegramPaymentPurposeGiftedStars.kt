package com.xxcactussell.domain

data class TelegramPaymentPurposeGiftedStars(
    val userId: Long,
    val currency: String,
    val amount: Long,
    val starCount: Long
) : TelegramPaymentPurpose
