package com.xxcactussell.domain

data class StarGiveawayPaymentOption(
    val currency: String,
    val amount: Long,
    val starCount: Long,
    val storeProductId: String,
    val yearlyBoostCount: Int,
    val winnerOptions: List<StarGiveawayWinnerOption>,
    val isDefault: Boolean,
    val isAdditional: Boolean
) : Object
