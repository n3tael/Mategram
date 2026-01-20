package com.xxcactussell.domain

data class RefundStarPayment(
    val userId: Long,
    val telegramPaymentChargeId: String
) : Function
