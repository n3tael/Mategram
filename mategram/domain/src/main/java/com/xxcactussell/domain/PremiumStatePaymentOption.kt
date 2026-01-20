package com.xxcactussell.domain

data class PremiumStatePaymentOption(
    val paymentOption: PremiumPaymentOption,
    val isCurrent: Boolean,
    val isUpgrade: Boolean,
    val lastTransactionId: String
) : Object
