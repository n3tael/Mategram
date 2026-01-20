package com.xxcactussell.data.utils.mappers.saved

import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.reaction.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SavedCredentials.toDomain(): SavedCredentials = SavedCredentials(
    id = this.id,
    title = this.title
)

fun TdApi.SavedMessagesTag.toDomain(): SavedMessagesTag = SavedMessagesTag(
    tag = this.tag.toDomain(),
    label = this.label,
    count = this.count
)

fun TdApi.SavedMessagesTags.toDomain(): SavedMessagesTags = SavedMessagesTags(
    tags = this.tags.map { it.toDomain() }
)

fun TdApi.SavedMessagesTopic.toDomain(): SavedMessagesTopic = SavedMessagesTopic(
    id = this.id,
    type = this.type.toDomain(),
    isPinned = this.isPinned,
    order = this.order,
    lastMessage = this.lastMessage?.toDomain(),
    draftMessage = this.draftMessage?.toDomain()
)

fun TdApi.SavedMessagesTopicType.toDomain(): SavedMessagesTopicType = when(this) {
    is TdApi.SavedMessagesTopicTypeMyNotes -> this.toDomain()
    is TdApi.SavedMessagesTopicTypeAuthorHidden -> this.toDomain()
    is TdApi.SavedMessagesTopicTypeSavedFromChat -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SavedMessagesTopicTypeAuthorHidden.toDomain(): SavedMessagesTopicTypeAuthorHidden = SavedMessagesTopicTypeAuthorHidden

fun TdApi.SavedMessagesTopicTypeMyNotes.toDomain(): SavedMessagesTopicTypeMyNotes = SavedMessagesTopicTypeMyNotes

fun TdApi.SavedMessagesTopicTypeSavedFromChat.toDomain(): SavedMessagesTopicTypeSavedFromChat = SavedMessagesTopicTypeSavedFromChat(
    chatId = this.chatId
)

