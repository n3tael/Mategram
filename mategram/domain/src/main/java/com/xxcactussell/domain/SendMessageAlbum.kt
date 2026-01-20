package com.xxcactussell.domain

data class SendMessageAlbum(
    val chatId: Long,
    val messageThreadId: Long,
    val replyTo: InputMessageReplyTo,
    val options: MessageSendOptions,
    val inputMessageContents: List<InputMessageContent>
) : Function
