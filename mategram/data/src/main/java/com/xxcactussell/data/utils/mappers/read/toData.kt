package com.xxcactussell.data.utils.mappers.read

import com.xxcactussell.data.utils.mappers.chat.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ReadAllChatMentions.toData(): TdApi.ReadAllChatMentions = TdApi.ReadAllChatMentions(
    this.chatId
)

fun ReadAllChatReactions.toData(): TdApi.ReadAllChatReactions = TdApi.ReadAllChatReactions(
    this.chatId
)

fun ReadAllDirectMessagesChatTopicReactions.toData(): TdApi.ReadAllDirectMessagesChatTopicReactions = TdApi.ReadAllDirectMessagesChatTopicReactions(
    this.chatId,
    this.topicId
)

fun ReadAllMessageThreadMentions.toData(): TdApi.ReadAllMessageThreadMentions = TdApi.ReadAllMessageThreadMentions(
    this.chatId,
    this.messageThreadId
)

fun ReadAllMessageThreadReactions.toData(): TdApi.ReadAllMessageThreadReactions = TdApi.ReadAllMessageThreadReactions(
    this.chatId,
    this.messageThreadId
)

fun ReadBusinessMessage.toData(): TdApi.ReadBusinessMessage = TdApi.ReadBusinessMessage(
    this.businessConnectionId,
    this.chatId,
    this.messageId
)

fun ReadChatList.toData(): TdApi.ReadChatList = TdApi.ReadChatList(
    this.chatList.toData()
)

fun ReadDatePrivacySettings.toData(): TdApi.ReadDatePrivacySettings = TdApi.ReadDatePrivacySettings(
    this.showReadDate
)

fun ReadFilePart.toData(): TdApi.ReadFilePart = TdApi.ReadFilePart(
    this.fileId,
    this.offset,
    this.count
)

