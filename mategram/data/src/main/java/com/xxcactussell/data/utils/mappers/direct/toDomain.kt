package com.xxcactussell.data.utils.mappers.direct

import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DirectMessagesChatTopic.toDomain(): DirectMessagesChatTopic = DirectMessagesChatTopic(
    chatId = this.chatId,
    id = this.id,
    senderId = this.senderId.toDomain(),
    order = this.order,
    canSendUnpaidMessages = this.canSendUnpaidMessages,
    isMarkedAsUnread = this.isMarkedAsUnread,
    unreadCount = this.unreadCount,
    lastReadInboxMessageId = this.lastReadInboxMessageId,
    lastReadOutboxMessageId = this.lastReadOutboxMessageId,
    unreadReactionCount = this.unreadReactionCount,
    lastMessage = this.lastMessage?.toDomain(),
    draftMessage = this.draftMessage?.toDomain()
)

