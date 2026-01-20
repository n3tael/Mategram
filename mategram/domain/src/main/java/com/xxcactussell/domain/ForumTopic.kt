package com.xxcactussell.domain

data class ForumTopic(
    val info: ForumTopicInfo,
    val lastMessage: Message? = null,
    val order: Long,
    val isPinned: Boolean,
    val unreadCount: Int,
    val lastReadInboxMessageId: Long,
    val lastReadOutboxMessageId: Long,
    val unreadMentionCount: Int,
    val unreadReactionCount: Int,
    val notificationSettings: ChatNotificationSettings,
    val draftMessage: DraftMessage? = null
) : Object
