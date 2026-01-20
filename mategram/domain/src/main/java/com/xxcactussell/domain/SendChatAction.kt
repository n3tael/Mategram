package com.xxcactussell.domain

data class SendChatAction(
    val chatId: Long,
    val messageThreadId: Long,
    val businessConnectionId: String,
    val action: ChatAction
) : Function
