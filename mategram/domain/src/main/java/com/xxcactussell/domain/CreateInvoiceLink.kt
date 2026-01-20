package com.xxcactussell.domain

data class CreateInvoiceLink(
    val businessConnectionId: String,
    val invoice: InputMessageContent
) : Function
