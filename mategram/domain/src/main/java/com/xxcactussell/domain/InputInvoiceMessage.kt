package com.xxcactussell.domain

data class InputInvoiceMessage(
    val chatId: Long,
    val messageId: Long
) : InputInvoice
