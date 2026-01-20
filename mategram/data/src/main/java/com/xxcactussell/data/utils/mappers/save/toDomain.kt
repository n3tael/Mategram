package com.xxcactussell.data.utils.mappers.save

import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.json.toDomain
import com.xxcactussell.data.utils.mappers.target.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SaveApplicationLogEvent.toDomain(): SaveApplicationLogEvent = SaveApplicationLogEvent(
    type = this.type,
    chatId = this.chatId,
    data = this.data.toDomain()
)

fun TdApi.SavePreparedInlineMessage.toDomain(): SavePreparedInlineMessage = SavePreparedInlineMessage(
    userId = this.userId,
    result = this.result.toDomain(),
    chatTypes = this.chatTypes.toDomain()
)

