package com.xxcactussell.domain

data class StopBusinessPoll(
    val businessConnectionId: String,
    val chatId: Long,
    val messageId: Long,
    val replyMarkup: ReplyMarkup
) : Function
