package com.xxcactussell.domain

data class ForwardMessages(
    val chatId: Long,
    val messageThreadId: Long,
    val fromChatId: Long,
    val messageIds: LongArray,
    val options: MessageSendOptions,
    val sendCopy: Boolean,
    val removeCaption: Boolean
) : Function
