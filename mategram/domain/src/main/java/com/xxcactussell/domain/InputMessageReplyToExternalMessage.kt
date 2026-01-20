package com.xxcactussell.domain

data class InputMessageReplyToExternalMessage(
    val chatId: Long,
    val messageId: Long,
    val quote: InputTextQuote,
    val checklistTaskId: Int
) : InputMessageReplyTo
