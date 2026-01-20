package com.xxcactussell.data.utils.mappers.new

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.NewChatPrivacySettings.toDomain(): NewChatPrivacySettings = NewChatPrivacySettings(
    allowNewChatsFromUnknownUsers = this.allowNewChatsFromUnknownUsers,
    incomingPaidMessageStarCount = this.incomingPaidMessageStarCount
)

