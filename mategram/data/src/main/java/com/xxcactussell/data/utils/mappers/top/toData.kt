package com.xxcactussell.data.utils.mappers.top

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun TopChatCategory.toData(): TdApi.TopChatCategory = when(this) {
    is TopChatCategoryUsers -> this.toData()
    is TopChatCategoryBots -> this.toData()
    is TopChatCategoryGroups -> this.toData()
    is TopChatCategoryChannels -> this.toData()
    is TopChatCategoryInlineBots -> this.toData()
    is TopChatCategoryWebAppBots -> this.toData()
    is TopChatCategoryCalls -> this.toData()
    is TopChatCategoryForwardChats -> this.toData()
}

fun TopChatCategoryBots.toData(): TdApi.TopChatCategoryBots = TdApi.TopChatCategoryBots(
)

fun TopChatCategoryCalls.toData(): TdApi.TopChatCategoryCalls = TdApi.TopChatCategoryCalls(
)

fun TopChatCategoryChannels.toData(): TdApi.TopChatCategoryChannels = TdApi.TopChatCategoryChannels(
)

fun TopChatCategoryForwardChats.toData(): TdApi.TopChatCategoryForwardChats = TdApi.TopChatCategoryForwardChats(
)

fun TopChatCategoryGroups.toData(): TdApi.TopChatCategoryGroups = TdApi.TopChatCategoryGroups(
)

fun TopChatCategoryInlineBots.toData(): TdApi.TopChatCategoryInlineBots = TdApi.TopChatCategoryInlineBots(
)

fun TopChatCategoryUsers.toData(): TdApi.TopChatCategoryUsers = TdApi.TopChatCategoryUsers(
)

fun TopChatCategoryWebAppBots.toData(): TdApi.TopChatCategoryWebAppBots = TdApi.TopChatCategoryWebAppBots(
)

