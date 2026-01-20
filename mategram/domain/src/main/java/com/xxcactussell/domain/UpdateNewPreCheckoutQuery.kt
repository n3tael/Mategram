package com.xxcactussell.domain

data class UpdateNewPreCheckoutQuery(
    val id: Long,
    val senderUserId: Long,
    val currency: String,
    val totalAmount: Long,
    val invoicePayload: ByteArray,
    val shippingOptionId: String,
    val orderInfo: OrderInfo? = null
) : Update
