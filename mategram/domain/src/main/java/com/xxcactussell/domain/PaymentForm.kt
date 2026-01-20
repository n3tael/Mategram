package com.xxcactussell.domain

data class PaymentForm(
    val id: Long,
    val type: PaymentFormType,
    val sellerBotUserId: Long,
    val productInfo: ProductInfo
) : Object
