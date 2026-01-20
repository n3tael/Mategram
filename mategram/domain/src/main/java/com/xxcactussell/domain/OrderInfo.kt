package com.xxcactussell.domain

data class OrderInfo(
    val name: String,
    val phoneNumber: String,
    val emailAddress: String,
    val shippingAddress: Address? = null
) : Object
