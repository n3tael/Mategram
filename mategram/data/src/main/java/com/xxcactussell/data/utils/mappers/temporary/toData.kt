package com.xxcactussell.data.utils.mappers.temporary

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun TemporaryPasswordState.toData(): TdApi.TemporaryPasswordState = TdApi.TemporaryPasswordState(
    this.hasPassword,
    this.validFor
)

