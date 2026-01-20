package com.xxcactussell.domain

data class TelegramPaymentPurposeStars(
    val currency: String,
    val amount: Long,
    val starCount: Long,
    val chatId: Long
) : TelegramPaymentPurpose
