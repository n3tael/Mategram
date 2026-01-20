package com.xxcactussell.domain

data class EditUserStarSubscription(
    val userId: Long,
    val telegramPaymentChargeId: String,
    val isCanceled: Boolean
) : Function
