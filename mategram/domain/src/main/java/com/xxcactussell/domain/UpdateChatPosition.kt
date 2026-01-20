package com.xxcactussell.domain

data class UpdateChatPosition(
    val chatId: Long,
    val position: ChatPosition
) : Update
