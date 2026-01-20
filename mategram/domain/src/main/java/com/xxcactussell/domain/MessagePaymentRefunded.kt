package com.xxcactussell.domain

data class MessagePaymentRefunded(
    val ownerId: MessageSender,
    val currency: String,
    val totalAmount: Long,
    val invoicePayload: ByteArray,
    val telegramPaymentChargeId: String,
    val providerPaymentChargeId: String
) : MessageContent
