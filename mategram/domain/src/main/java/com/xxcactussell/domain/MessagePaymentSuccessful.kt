package com.xxcactussell.domain

data class MessagePaymentSuccessful(
    val invoiceChatId: Long,
    val invoiceMessageId: Long,
    val currency: String,
    val totalAmount: Long,
    val subscriptionUntilDate: Int,
    val isRecurring: Boolean,
    val isFirstRecurring: Boolean,
    val invoiceName: String
) : MessageContent
