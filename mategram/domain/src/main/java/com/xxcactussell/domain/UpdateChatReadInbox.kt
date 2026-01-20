package com.xxcactussell.domain

data class UpdateChatReadInbox(
    val chatId: Long,
    val lastReadInboxMessageId: Long,
    val unreadCount: Int
) : Update
