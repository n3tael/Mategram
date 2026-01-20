package com.xxcactussell.domain

data class StopPoll(
    val chatId: Long,
    val messageId: Long,
    val replyMarkup: ReplyMarkup
) : Function
