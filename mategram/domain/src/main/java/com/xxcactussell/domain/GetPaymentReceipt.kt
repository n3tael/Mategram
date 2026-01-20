package com.xxcactussell.domain

data class GetPaymentReceipt(
    val chatId: Long,
    val messageId: Long
) : Function
