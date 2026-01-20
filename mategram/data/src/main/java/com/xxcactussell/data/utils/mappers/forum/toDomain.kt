package com.xxcactussell.data.utils.mappers.forum

import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ForumTopic.toDomain(): ForumTopic = ForumTopic(
    info = this.info.toDomain(),
    lastMessage = this.lastMessage?.toDomain(),
    order = this.order,
    isPinned = this.isPinned,
    unreadCount = this.unreadCount,
    lastReadInboxMessageId = this.lastReadInboxMessageId,
    lastReadOutboxMessageId = this.lastReadOutboxMessageId,
    unreadMentionCount = this.unreadMentionCount,
    unreadReactionCount = this.unreadReactionCount,
    notificationSettings = this.notificationSettings.toDomain(),
    draftMessage = this.draftMessage?.toDomain()
)

fun TdApi.ForumTopicIcon.toDomain(): ForumTopicIcon = ForumTopicIcon(
    color = this.color,
    customEmojiId = this.customEmojiId
)

fun TdApi.ForumTopicInfo.toDomain(): ForumTopicInfo = ForumTopicInfo(
    chatId = this.chatId,
    forumTopicId = this.forumTopicId,
    messageThreadId = this.messageThreadId,
    name = this.name,
    icon = this.icon.toDomain(),
    creationDate = this.creationDate,
    creatorId = this.creatorId.toDomain(),
    isGeneral = this.isGeneral,
    isOutgoing = this.isOutgoing,
    isClosed = this.isClosed,
    isHidden = this.isHidden
)

fun TdApi.ForumTopics.toDomain(): ForumTopics = ForumTopics(
    totalCount = this.totalCount,
    topics = this.topics.map { it.toDomain() },
    nextOffsetDate = this.nextOffsetDate,
    nextOffsetMessageId = this.nextOffsetMessageId,
    nextOffsetMessageThreadId = this.nextOffsetMessageThreadId
)

