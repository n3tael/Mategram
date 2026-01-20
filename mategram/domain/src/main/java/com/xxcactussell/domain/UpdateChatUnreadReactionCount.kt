package com.xxcactussell.domain

data class UpdateChatUnreadReactionCount(
    val chatId: Long,
    val unreadReactionCount: Int
) : Update
