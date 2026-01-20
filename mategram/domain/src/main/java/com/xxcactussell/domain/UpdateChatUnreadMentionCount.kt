package com.xxcactussell.domain

data class UpdateChatUnreadMentionCount(
    val chatId: Long,
    val unreadMentionCount: Int
) : Update
