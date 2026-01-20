package com.xxcactussell.domain

data class ValidatedOrderInfo(
    val orderInfoId: String,
    val shippingOptions: List<ShippingOption>
) : Object
