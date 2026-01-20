package com.xxcactussell.data.utils.mappers.read

import com.xxcactussell.data.utils.mappers.chat.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ReadAllChatMentions.toDomain(): ReadAllChatMentions = ReadAllChatMentions(
    chatId = this.chatId
)

fun TdApi.ReadAllChatReactions.toDomain(): ReadAllChatReactions = ReadAllChatReactions(
    chatId = this.chatId
)

fun TdApi.ReadAllDirectMessagesChatTopicReactions.toDomain(): ReadAllDirectMessagesChatTopicReactions = ReadAllDirectMessagesChatTopicReactions(
    chatId = this.chatId,
    topicId = this.topicId
)

fun TdApi.ReadAllMessageThreadMentions.toDomain(): ReadAllMessageThreadMentions = ReadAllMessageThreadMentions(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId
)

fun TdApi.ReadAllMessageThreadReactions.toDomain(): ReadAllMessageThreadReactions = ReadAllMessageThreadReactions(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId
)

fun TdApi.ReadBusinessMessage.toDomain(): ReadBusinessMessage = ReadBusinessMessage(
    businessConnectionId = this.businessConnectionId,
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.ReadChatList.toDomain(): ReadChatList = ReadChatList(
    chatList = this.chatList.toDomain()
)

fun TdApi.ReadDatePrivacySettings.toDomain(): ReadDatePrivacySettings = ReadDatePrivacySettings(
    showReadDate = this.showReadDate
)

fun TdApi.ReadFilePart.toDomain(): ReadFilePart = ReadFilePart(
    fileId = this.fileId,
    offset = this.offset,
    count = this.count
)

