package com.xxcactussell.domain

data class StorePaymentPurposeStars(
    val currency: String,
    val amount: Long,
    val starCount: Long,
    val chatId: Long
) : StorePaymentPurpose
