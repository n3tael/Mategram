package com.xxcactussell.data.utils.mappers.saved

import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.reaction.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SavedCredentials.toData(): TdApi.SavedCredentials = TdApi.SavedCredentials(
    this.id,
    this.title
)

fun SavedMessagesTag.toData(): TdApi.SavedMessagesTag = TdApi.SavedMessagesTag(
    this.tag.toData(),
    this.label,
    this.count
)

fun SavedMessagesTags.toData(): TdApi.SavedMessagesTags = TdApi.SavedMessagesTags(
    this.tags.map { it.toData() }.toTypedArray()
)

fun SavedMessagesTopic.toData(): TdApi.SavedMessagesTopic = TdApi.SavedMessagesTopic(
    this.id,
    this.type.toData(),
    this.isPinned,
    this.order,
    this.lastMessage?.toData(),
    this.draftMessage?.toData()
)

fun SavedMessagesTopicType.toData(): TdApi.SavedMessagesTopicType = when(this) {
    is SavedMessagesTopicTypeMyNotes -> this.toData()
    is SavedMessagesTopicTypeAuthorHidden -> this.toData()
    is SavedMessagesTopicTypeSavedFromChat -> this.toData()
}

fun SavedMessagesTopicTypeAuthorHidden.toData(): TdApi.SavedMessagesTopicTypeAuthorHidden = TdApi.SavedMessagesTopicTypeAuthorHidden(
)

fun SavedMessagesTopicTypeMyNotes.toData(): TdApi.SavedMessagesTopicTypeMyNotes = TdApi.SavedMessagesTopicTypeMyNotes(
)

fun SavedMessagesTopicTypeSavedFromChat.toData(): TdApi.SavedMessagesTopicTypeSavedFromChat = TdApi.SavedMessagesTopicTypeSavedFromChat(
    this.chatId
)

