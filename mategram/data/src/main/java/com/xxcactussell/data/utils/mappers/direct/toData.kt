package com.xxcactussell.data.utils.mappers.direct

import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DirectMessagesChatTopic.toData(): TdApi.DirectMessagesChatTopic = TdApi.DirectMessagesChatTopic(
    this.chatId,
    this.id,
    this.senderId.toData(),
    this.order,
    this.canSendUnpaidMessages,
    this.isMarkedAsUnread,
    this.unreadCount,
    this.lastReadInboxMessageId,
    this.lastReadOutboxMessageId,
    this.unreadReactionCount,
    this.lastMessage?.toData(),
    this.draftMessage?.toData()
)

