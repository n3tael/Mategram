package com.xxcactussell.domain

data class MessageReplyToMessage(
    val message: Message? = null,
    val chatId: Long,
    val messageId: Long,
    val quote: TextQuote? = null,
    val checklistTaskId: Int,
    val origin: MessageOrigin? = null,
    val originSendDate: Int,
    val content: MessageContent? = null
) : MessageReplyTo
