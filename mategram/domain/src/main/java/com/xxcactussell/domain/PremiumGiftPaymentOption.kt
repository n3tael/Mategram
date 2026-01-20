package com.xxcactussell.domain

data class PremiumGiftPaymentOption(
    val currency: String,
    val amount: Long,
    val starCount: Long,
    val discountPercentage: Int,
    val monthCount: Int,
    val storeProductId: String,
    val sticker: Sticker? = null
) : Object
