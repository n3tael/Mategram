package com.xxcactussell.domain

data class UpdateChatReplyMarkup(
    val chatId: Long,
    val replyMarkupMessageId: Long
) : Update
