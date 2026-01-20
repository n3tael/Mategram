package com.xxcactussell.data.utils.mappers.decrypt

import com.xxcactussell.data.utils.mappers.calls.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DecryptGroupCallData.toDomain(): DecryptGroupCallData = DecryptGroupCallData(
    groupCallId = this.groupCallId,
    participantId = this.participantId.toDomain(),
    dataChannel = this.dataChannel.toDomain(),
    data = this.data
)

