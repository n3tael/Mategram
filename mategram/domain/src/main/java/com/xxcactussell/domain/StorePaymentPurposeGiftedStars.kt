package com.xxcactussell.domain

data class StorePaymentPurposeGiftedStars(
    val userId: Long,
    val currency: String,
    val amount: Long,
    val starCount: Long
) : StorePaymentPurpose
