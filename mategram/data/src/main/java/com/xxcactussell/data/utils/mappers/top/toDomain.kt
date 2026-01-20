package com.xxcactussell.data.utils.mappers.top

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.TopChatCategory.toDomain(): TopChatCategory = when(this) {
    is TdApi.TopChatCategoryUsers -> this.toDomain()
    is TdApi.TopChatCategoryBots -> this.toDomain()
    is TdApi.TopChatCategoryGroups -> this.toDomain()
    is TdApi.TopChatCategoryChannels -> this.toDomain()
    is TdApi.TopChatCategoryInlineBots -> this.toDomain()
    is TdApi.TopChatCategoryWebAppBots -> this.toDomain()
    is TdApi.TopChatCategoryCalls -> this.toDomain()
    is TdApi.TopChatCategoryForwardChats -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.TopChatCategoryBots.toDomain(): TopChatCategoryBots = TopChatCategoryBots

fun TdApi.TopChatCategoryCalls.toDomain(): TopChatCategoryCalls = TopChatCategoryCalls

fun TdApi.TopChatCategoryChannels.toDomain(): TopChatCategoryChannels = TopChatCategoryChannels

fun TdApi.TopChatCategoryForwardChats.toDomain(): TopChatCategoryForwardChats = TopChatCategoryForwardChats

fun TdApi.TopChatCategoryGroups.toDomain(): TopChatCategoryGroups = TopChatCategoryGroups

fun TdApi.TopChatCategoryInlineBots.toDomain(): TopChatCategoryInlineBots = TopChatCategoryInlineBots

fun TdApi.TopChatCategoryUsers.toDomain(): TopChatCategoryUsers = TopChatCategoryUsers

fun TdApi.TopChatCategoryWebAppBots.toDomain(): TopChatCategoryWebAppBots = TopChatCategoryWebAppBots

