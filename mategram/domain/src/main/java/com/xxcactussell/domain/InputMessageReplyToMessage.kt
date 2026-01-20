package com.xxcactussell.domain

data class InputMessageReplyToMessage(
    val messageId: Long,
    val quote: InputTextQuote,
    val checklistTaskId: Int
) : InputMessageReplyTo
