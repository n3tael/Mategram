package com.xxcactussell.domain

data class TelegramPaymentPurposeStarGiveaway(
    val parameters: GiveawayParameters,
    val currency: String,
    val amount: Long,
    val winnerCount: Int,
    val starCount: Long
) : TelegramPaymentPurpose
