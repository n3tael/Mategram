package com.xxcactussell.data.utils.mappers.encrypt

import com.xxcactussell.data.utils.mappers.calls.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun EncryptGroupCallData.toData(): TdApi.EncryptGroupCallData = TdApi.EncryptGroupCallData(
    this.groupCallId,
    this.dataChannel.toData(),
    this.data,
    this.unencryptedPrefixSize
)

