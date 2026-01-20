package com.xxcactussell.data.utils.mappers.prepared

import com.xxcactussell.data.utils.mappers.inline.toDomain
import com.xxcactussell.data.utils.mappers.target.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.PreparedInlineMessage.toDomain(): PreparedInlineMessage = PreparedInlineMessage(
    inlineQueryId = this.inlineQueryId,
    result = this.result.toDomain(),
    chatTypes = this.chatTypes.toDomain()
)

fun TdApi.PreparedInlineMessageId.toDomain(): PreparedInlineMessageId = PreparedInlineMessageId(
    id = this.id,
    expirationDate = this.expirationDate
)

