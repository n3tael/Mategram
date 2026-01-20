package com.xxcactussell.domain

data class UpdateChatVideoChat(
    val chatId: Long,
    val videoChat: VideoChat
) : Update
