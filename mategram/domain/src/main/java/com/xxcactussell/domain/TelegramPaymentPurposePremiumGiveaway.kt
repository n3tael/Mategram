package com.xxcactussell.domain

data class TelegramPaymentPurposePremiumGiveaway(
    val parameters: GiveawayParameters,
    val currency: String,
    val amount: Long,
    val winnerCount: Int,
    val monthCount: Int
) : TelegramPaymentPurpose
