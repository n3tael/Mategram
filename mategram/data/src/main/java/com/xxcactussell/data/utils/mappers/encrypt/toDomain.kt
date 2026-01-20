package com.xxcactussell.data.utils.mappers.encrypt

import com.xxcactussell.data.utils.mappers.calls.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.EncryptGroupCallData.toDomain(): EncryptGroupCallData = EncryptGroupCallData(
    groupCallId = this.groupCallId,
    dataChannel = this.dataChannel.toDomain(),
    data = this.data,
    unencryptedPrefixSize = this.unencryptedPrefixSize
)

