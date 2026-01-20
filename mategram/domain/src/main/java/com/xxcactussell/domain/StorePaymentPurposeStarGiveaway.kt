package com.xxcactussell.domain

data class StorePaymentPurposeStarGiveaway(
    val parameters: GiveawayParameters,
    val currency: String,
    val amount: Long,
    val winnerCount: Int,
    val starCount: Long
) : StorePaymentPurpose
