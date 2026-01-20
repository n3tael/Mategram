package com.xxcactussell.data.utils.mappers.new

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun NewChatPrivacySettings.toData(): TdApi.NewChatPrivacySettings = TdApi.NewChatPrivacySettings(
    this.allowNewChatsFromUnknownUsers,
    this.incomingPaidMessageStarCount
)

