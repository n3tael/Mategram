package com.xxcactussell.domain

data class UpdateChatLastMessage(
    val chatId: Long,
    val lastMessage: Message? = null,
    val positions: List<ChatPosition>
) : Update
