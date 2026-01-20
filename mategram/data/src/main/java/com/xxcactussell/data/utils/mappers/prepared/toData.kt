package com.xxcactussell.data.utils.mappers.prepared

import com.xxcactussell.data.utils.mappers.inline.toData
import com.xxcactussell.data.utils.mappers.target.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun PreparedInlineMessage.toData(): TdApi.PreparedInlineMessage = TdApi.PreparedInlineMessage(
    this.inlineQueryId,
    this.result.toData(),
    this.chatTypes.toData()
)

fun PreparedInlineMessageId.toData(): TdApi.PreparedInlineMessageId = TdApi.PreparedInlineMessageId(
    this.id,
    this.expirationDate
)

