package com.xxcactussell.data.utils.mappers.decrypt

import com.xxcactussell.data.utils.mappers.calls.toData
import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DecryptGroupCallData.toData(): TdApi.DecryptGroupCallData = TdApi.DecryptGroupCallData(
    this.groupCallId,
    this.participantId.toData(),
    this.dataChannel.toData(),
    this.data
)

