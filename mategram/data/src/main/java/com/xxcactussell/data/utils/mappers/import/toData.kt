package com.xxcactussell.data.utils.mappers.import

import com.xxcactussell.data.utils.mappers.input.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ImportMessages.toData(): TdApi.ImportMessages = TdApi.ImportMessages(
    this.chatId,
    this.messageFile.toData(),
    this.attachedFiles.map { it.toData() }.toTypedArray()
)

