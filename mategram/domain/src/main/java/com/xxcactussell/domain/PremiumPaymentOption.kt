package com.xxcactussell.domain

data class PremiumPaymentOption(
    val currency: String,
    val amount: Long,
    val discountPercentage: Int,
    val monthCount: Int,
    val storeProductId: String,
    val paymentLink: InternalLinkType? = null
) : Object
