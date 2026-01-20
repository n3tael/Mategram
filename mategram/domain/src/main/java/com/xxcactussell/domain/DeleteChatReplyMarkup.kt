package com.xxcactussell.domain

data class DeleteChatReplyMarkup(
    val chatId: Long,
    val messageId: Long
) : Function
