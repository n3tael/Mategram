package com.xxcactussell.domain

data class PaymentReceipt(
    val productInfo: ProductInfo,
    val date: Int,
    val sellerBotUserId: Long,
    val type: PaymentReceiptType
) : Object
