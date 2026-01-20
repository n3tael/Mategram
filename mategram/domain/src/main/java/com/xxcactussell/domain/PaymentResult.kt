package com.xxcactussell.domain

data class PaymentResult(
    val success: Boolean,
    val verificationUrl: String
) : Object
