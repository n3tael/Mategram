package com.xxcactussell.domain

data class UpdateMessageContent(
    val chatId: Long,
    val messageId: Long,
    val newContent: MessageContent
) : Update
