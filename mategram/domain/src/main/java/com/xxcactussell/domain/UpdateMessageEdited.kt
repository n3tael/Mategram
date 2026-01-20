package com.xxcactussell.domain

data class UpdateMessageEdited(
    val chatId: Long,
    val messageId: Long,
    val editDate: Int,
    val replyMarkup: ReplyMarkup? = null
) : Update
