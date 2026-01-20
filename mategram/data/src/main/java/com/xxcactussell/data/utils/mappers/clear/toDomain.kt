package com.xxcactussell.data.utils.mappers.clear

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ClearAllDraftMessages.toDomain(): ClearAllDraftMessages = ClearAllDraftMessages(
    excludeSecretChats = this.excludeSecretChats
)

fun TdApi.ClearAutosaveSettingsExceptions.toDomain(): ClearAutosaveSettingsExceptions = ClearAutosaveSettingsExceptions

fun TdApi.ClearRecentEmojiStatuses.toDomain(): ClearRecentEmojiStatuses = ClearRecentEmojiStatuses

fun TdApi.ClearRecentReactions.toDomain(): ClearRecentReactions = ClearRecentReactions

fun TdApi.ClearRecentStickers.toDomain(): ClearRecentStickers = ClearRecentStickers(
    isAttached = this.isAttached
)

fun TdApi.ClearRecentlyFoundChats.toDomain(): ClearRecentlyFoundChats = ClearRecentlyFoundChats

fun TdApi.ClearSearchedForTags.toDomain(): ClearSearchedForTags = ClearSearchedForTags(
    clearCashtags = this.clearCashtags
)

