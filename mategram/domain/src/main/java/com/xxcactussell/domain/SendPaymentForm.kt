package com.xxcactussell.domain

data class SendPaymentForm(
    val inputInvoice: InputInvoice,
    val paymentFormId: Long,
    val orderInfoId: String,
    val shippingOptionId: String,
    val credentials: InputCredentials,
    val tipAmount: Long
) : Function
