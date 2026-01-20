package com.xxcactussell.domain

data class UpdateMessageMentionRead(
    val chatId: Long,
    val messageId: Long,
    val unreadMentionCount: Int
) : Update
