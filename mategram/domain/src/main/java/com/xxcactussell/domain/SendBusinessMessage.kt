package com.xxcactussell.domain

data class SendBusinessMessage(
    val businessConnectionId: String,
    val chatId: Long,
    val replyTo: InputMessageReplyTo,
    val disableNotification: Boolean,
    val protectContent: Boolean,
    val effectId: Long,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : Function
