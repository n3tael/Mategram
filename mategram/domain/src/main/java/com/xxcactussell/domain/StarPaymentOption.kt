package com.xxcactussell.domain

data class StarPaymentOption(
    val currency: String,
    val amount: Long,
    val starCount: Long,
    val storeProductId: String,
    val isAdditional: Boolean
) : Object
