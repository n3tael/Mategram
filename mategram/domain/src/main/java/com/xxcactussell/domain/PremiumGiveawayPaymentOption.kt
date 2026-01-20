package com.xxcactussell.domain

data class PremiumGiveawayPaymentOption(
    val currency: String,
    val amount: Long,
    val winnerCount: Int,
    val monthCount: Int,
    val storeProductId: String,
    val storeProductQuantity: Int
) : Object
