package com.xxcactussell.domain

data class PaymentReceiptTypeStars(
    val starCount: Long,
    val transactionId: String
) : PaymentReceiptType
