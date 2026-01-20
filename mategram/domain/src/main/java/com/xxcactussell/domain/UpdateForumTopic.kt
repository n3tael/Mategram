package com.xxcactussell.domain

data class UpdateForumTopic(
    val chatId: Long,
    val messageThreadId: Long,
    val isPinned: Boolean,
    val lastReadInboxMessageId: Long,
    val lastReadOutboxMessageId: Long,
    val unreadMentionCount: Int,
    val unreadReactionCount: Int,
    val notificationSettings: ChatNotificationSettings
) : Update
