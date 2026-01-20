package com.xxcactussell.domain

data class EditMessageChecklist(
    val chatId: Long,
    val messageId: Long,
    val replyMarkup: ReplyMarkup,
    val checklist: InputChecklist
) : Function
