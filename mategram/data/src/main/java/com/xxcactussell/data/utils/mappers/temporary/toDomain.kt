package com.xxcactussell.data.utils.mappers.temporary

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.TemporaryPasswordState.toDomain(): TemporaryPasswordState = TemporaryPasswordState(
    hasPassword = this.hasPassword,
    validFor = this.validFor
)

