package com.xxcactussell.domain

data class MessageInvoice(
    val productInfo: ProductInfo,
    val currency: String,
    val totalAmount: Long,
    val startParameter: String,
    val isTest: Boolean,
    val needShippingAddress: Boolean,
    val receiptMessageId: Long,
    val paidMedia: PaidMedia? = null,
    val paidMediaCaption: FormattedText? = null
) : MessageContent
