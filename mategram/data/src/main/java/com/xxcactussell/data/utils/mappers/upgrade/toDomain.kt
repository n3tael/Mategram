package com.xxcactussell.data.utils.mappers.upgrade

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.UpgradeBasicGroupChatToSupergroupChat.toDomain(): UpgradeBasicGroupChatToSupergroupChat = UpgradeBasicGroupChatToSupergroupChat(
    chatId = this.chatId
)

