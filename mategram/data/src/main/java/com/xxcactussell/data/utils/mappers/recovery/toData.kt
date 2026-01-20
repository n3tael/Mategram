package com.xxcactussell.data.utils.mappers.recovery

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RecoveryEmailAddress.toData(): TdApi.RecoveryEmailAddress = TdApi.RecoveryEmailAddress(
    this.recoveryEmailAddress
)

