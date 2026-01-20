package com.xxcactussell.data.utils.mappers.clear

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ClearAllDraftMessages.toData(): TdApi.ClearAllDraftMessages = TdApi.ClearAllDraftMessages(
    this.excludeSecretChats
)

fun ClearAutosaveSettingsExceptions.toData(): TdApi.ClearAutosaveSettingsExceptions = TdApi.ClearAutosaveSettingsExceptions(
)

fun ClearRecentEmojiStatuses.toData(): TdApi.ClearRecentEmojiStatuses = TdApi.ClearRecentEmojiStatuses(
)

fun ClearRecentReactions.toData(): TdApi.ClearRecentReactions = TdApi.ClearRecentReactions(
)

fun ClearRecentStickers.toData(): TdApi.ClearRecentStickers = TdApi.ClearRecentStickers(
    this.isAttached
)

fun ClearRecentlyFoundChats.toData(): TdApi.ClearRecentlyFoundChats = TdApi.ClearRecentlyFoundChats(
)

fun ClearSearchedForTags.toData(): TdApi.ClearSearchedForTags = TdApi.ClearSearchedForTags(
    this.clearCashtags
)

