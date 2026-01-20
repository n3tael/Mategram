package com.xxcactussell.domain

data class AddLocalMessage(
    val chatId: Long,
    val senderId: MessageSender,
    val replyTo: InputMessageReplyTo,
    val disableNotification: Boolean,
    val inputMessageContent: InputMessageContent
) : Function
