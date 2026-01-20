package com.xxcactussell.data.utils.mappers.quick

import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.QuickReplyMessage.toDomain(): QuickReplyMessage = QuickReplyMessage(
    id = this.id,
    sendingState = this.sendingState?.toDomain(),
    canBeEdited = this.canBeEdited,
    replyToMessageId = this.replyToMessageId,
    viaBotUserId = this.viaBotUserId,
    mediaAlbumId = this.mediaAlbumId,
    content = this.content.toDomain(),
    replyMarkup = this.replyMarkup?.toDomain()
)

fun TdApi.QuickReplyMessages.toDomain(): QuickReplyMessages = QuickReplyMessages(
    messages = this.messages.map { it.toDomain() }
)

fun TdApi.QuickReplyShortcut.toDomain(): QuickReplyShortcut = QuickReplyShortcut(
    id = this.id,
    name = this.name,
    firstMessage = this.firstMessage.toDomain(),
    messageCount = this.messageCount
)

