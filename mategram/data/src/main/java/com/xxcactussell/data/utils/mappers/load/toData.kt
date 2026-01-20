package com.xxcactussell.data.utils.mappers.load

import com.xxcactussell.data.utils.mappers.stories.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun LoadActiveStories.toData(): TdApi.LoadActiveStories = TdApi.LoadActiveStories(
    this.storyList.toData()
)

fun LoadDirectMessagesChatTopics.toData(): TdApi.LoadDirectMessagesChatTopics = TdApi.LoadDirectMessagesChatTopics(
    this.chatId,
    this.limit
)

fun LoadQuickReplyShortcutMessages.toData(): TdApi.LoadQuickReplyShortcutMessages = TdApi.LoadQuickReplyShortcutMessages(
    this.shortcutId
)

fun LoadQuickReplyShortcuts.toData(): TdApi.LoadQuickReplyShortcuts = TdApi.LoadQuickReplyShortcuts(
)

fun LoadSavedMessagesTopics.toData(): TdApi.LoadSavedMessagesTopics = TdApi.LoadSavedMessagesTopics(
    this.limit
)

