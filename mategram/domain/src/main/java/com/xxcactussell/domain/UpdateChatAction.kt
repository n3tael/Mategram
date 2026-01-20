package com.xxcactussell.domain

data class UpdateChatAction(
    val chatId: Long,
    val messageThreadId: Long,
    val senderId: MessageSender,
    val action: ChatAction
) : Update
