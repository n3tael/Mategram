package com.xxcactussell.domain

data class PaymentReceiptTypeRegular(
    val paymentProviderUserId: Long,
    val invoice: Invoice,
    val orderInfo: OrderInfo? = null,
    val shippingOption: ShippingOption? = null,
    val credentialsTitle: String,
    val tipAmount: Long
) : PaymentReceiptType
