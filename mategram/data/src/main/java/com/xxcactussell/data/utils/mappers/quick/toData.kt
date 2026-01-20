package com.xxcactussell.data.utils.mappers.quick

import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun QuickReplyMessage.toData(): TdApi.QuickReplyMessage = TdApi.QuickReplyMessage(
    this.id,
    this.sendingState?.toData(),
    this.canBeEdited,
    this.replyToMessageId,
    this.viaBotUserId,
    this.mediaAlbumId,
    this.content.toData(),
    this.replyMarkup?.toData()
)

fun QuickReplyMessages.toData(): TdApi.QuickReplyMessages = TdApi.QuickReplyMessages(
    this.messages.map { it.toData() }.toTypedArray()
)

fun QuickReplyShortcut.toData(): TdApi.QuickReplyShortcut = TdApi.QuickReplyShortcut(
    this.id,
    this.name,
    this.firstMessage.toData(),
    this.messageCount
)

