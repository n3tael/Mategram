package com.xxcactussell.domain

data class DeleteChatMessagesBySender(
    val chatId: Long,
    val senderId: MessageSender
) : Function
