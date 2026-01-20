package com.xxcactussell.data.utils.mappers.forum

import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ForumTopic.toData(): TdApi.ForumTopic = TdApi.ForumTopic(
    this.info.toData(),
    this.lastMessage?.toData(),
    this.order,
    this.isPinned,
    this.unreadCount,
    this.lastReadInboxMessageId,
    this.lastReadOutboxMessageId,
    this.unreadMentionCount,
    this.unreadReactionCount,
    this.notificationSettings.toData(),
    this.draftMessage?.toData()
)

fun ForumTopicIcon.toData(): TdApi.ForumTopicIcon = TdApi.ForumTopicIcon(
    this.color,
    this.customEmojiId
)

fun ForumTopicInfo.toData(): TdApi.ForumTopicInfo = TdApi.ForumTopicInfo(
    this.chatId,
    this.forumTopicId,
    this.messageThreadId,
    this.name,
    this.icon.toData(),
    this.creationDate,
    this.creatorId.toData(),
    this.isGeneral,
    this.isOutgoing,
    this.isClosed,
    this.isHidden
)

fun ForumTopics.toData(): TdApi.ForumTopics = TdApi.ForumTopics(
    this.totalCount,
    this.topics.map { it.toData() }.toTypedArray(),
    this.nextOffsetDate,
    this.nextOffsetMessageId,
    this.nextOffsetMessageThreadId
)

