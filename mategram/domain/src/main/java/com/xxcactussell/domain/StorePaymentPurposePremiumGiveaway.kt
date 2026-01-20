package com.xxcactussell.domain

data class StorePaymentPurposePremiumGiveaway(
    val parameters: GiveawayParameters,
    val currency: String,
    val amount: Long
) : StorePaymentPurpose
