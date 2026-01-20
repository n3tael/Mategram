package com.xxcactussell.domain

data class AllowUnpaidMessagesFromUser(
    val userId: Long,
    val refundPayments: Boolean
) : Function
