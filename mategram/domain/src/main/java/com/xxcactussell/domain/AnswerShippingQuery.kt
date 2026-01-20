package com.xxcactussell.domain

data class AnswerShippingQuery(
    val shippingQueryId: Long,
    val shippingOptions: List<ShippingOption>,
    val errorMessage: String
) : Function
