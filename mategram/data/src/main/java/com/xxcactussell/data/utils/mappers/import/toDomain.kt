package com.xxcactussell.data.utils.mappers.import

import com.xxcactussell.data.utils.mappers.input.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ImportMessages.toDomain(): ImportMessages = ImportMessages(
    chatId = this.chatId,
    messageFile = this.messageFile.toDomain(),
    attachedFiles = this.attachedFiles.map { it.toDomain() }
)

