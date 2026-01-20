package com.xxcactussell.data.utils.mappers.recommended

import com.xxcactussell.data.utils.mappers.chat.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.RecommendedChatFolder.toDomain(): RecommendedChatFolder = RecommendedChatFolder(
    folder = this.folder.toDomain(),
    description = this.description
)

fun TdApi.RecommendedChatFolders.toDomain(): RecommendedChatFolders = RecommendedChatFolders(
    chatFolders = this.chatFolders.map { it.toDomain() }
)

