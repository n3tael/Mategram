package com.xxcactussell.domain

data class ValidateOrderInfo(
    val inputInvoice: InputInvoice,
    val orderInfo: OrderInfo,
    val allowSave: Boolean
) : Function
