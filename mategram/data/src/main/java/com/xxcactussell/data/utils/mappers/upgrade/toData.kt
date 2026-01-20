package com.xxcactussell.data.utils.mappers.upgrade

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun UpgradeBasicGroupChatToSupergroupChat.toData(): TdApi.UpgradeBasicGroupChatToSupergroupChat = TdApi.UpgradeBasicGroupChatToSupergroupChat(
    this.chatId
)

