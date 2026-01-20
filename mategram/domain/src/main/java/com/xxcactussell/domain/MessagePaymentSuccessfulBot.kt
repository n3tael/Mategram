package com.xxcactussell.domain

data class MessagePaymentSuccessfulBot(
    val currency: String,
    val totalAmount: Long,
    val subscriptionUntilDate: Int,
    val isRecurring: Boolean,
    val isFirstRecurring: Boolean,
    val invoicePayload: ByteArray,
    val shippingOptionId: String,
    val orderInfo: OrderInfo? = null,
    val telegramPaymentChargeId: String,
    val providerPaymentChargeId: String
) : MessageContent
