package com.xxcactussell.domain

data class UpdateNewShippingQuery(
    val id: Long,
    val senderUserId: Long,
    val invoicePayload: String,
    val shippingAddress: Address
) : Update
