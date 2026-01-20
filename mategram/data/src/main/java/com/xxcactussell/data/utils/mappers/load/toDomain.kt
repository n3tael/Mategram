package com.xxcactussell.data.utils.mappers.load

import com.xxcactussell.data.utils.mappers.stories.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.LoadActiveStories.toDomain(): LoadActiveStories = LoadActiveStories(
    storyList = this.storyList.toDomain()
)

fun TdApi.LoadDirectMessagesChatTopics.toDomain(): LoadDirectMessagesChatTopics = LoadDirectMessagesChatTopics(
    chatId = this.chatId,
    limit = this.limit
)

fun TdApi.LoadQuickReplyShortcutMessages.toDomain(): LoadQuickReplyShortcutMessages = LoadQuickReplyShortcutMessages(
    shortcutId = this.shortcutId
)

fun TdApi.LoadQuickReplyShortcuts.toDomain(): LoadQuickReplyShortcuts = LoadQuickReplyShortcuts

fun TdApi.LoadSavedMessagesTopics.toDomain(): LoadSavedMessagesTopics = LoadSavedMessagesTopics(
    limit = this.limit
)

