package com.xxcactussell.domain

data class StorePaymentPurposePremiumGift(
    val currency: String,
    val amount: Long,
    val userId: Long,
    val text: FormattedText
) : StorePaymentPurpose
