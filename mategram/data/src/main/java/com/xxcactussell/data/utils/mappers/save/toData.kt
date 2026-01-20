package com.xxcactussell.data.utils.mappers.save

import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.json.toData
import com.xxcactussell.data.utils.mappers.target.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SaveApplicationLogEvent.toData(): TdApi.SaveApplicationLogEvent = TdApi.SaveApplicationLogEvent(
    this.type,
    this.chatId,
    this.data.toData()
)

fun SavePreparedInlineMessage.toData(): TdApi.SavePreparedInlineMessage = TdApi.SavePreparedInlineMessage(
    this.userId,
    this.result.toData(),
    this.chatTypes.toData()
)

