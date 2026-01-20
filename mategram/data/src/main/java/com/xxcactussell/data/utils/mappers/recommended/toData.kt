package com.xxcactussell.data.utils.mappers.recommended

import com.xxcactussell.data.utils.mappers.chat.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RecommendedChatFolder.toData(): TdApi.RecommendedChatFolder = TdApi.RecommendedChatFolder(
    this.folder.toData(),
    this.description
)

fun RecommendedChatFolders.toData(): TdApi.RecommendedChatFolders = TdApi.RecommendedChatFolders(
    this.chatFolders.map { it.toData() }.toTypedArray()
)

