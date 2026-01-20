package com.xxcactussell.domain

data class ResendMessages(
    val chatId: Long,
    val messageIds: LongArray,
    val quote: InputTextQuote,
    val paidMessageStarCount: Long
) : Function
