package com.xxcactussell.data.utils.mappers.recovery

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.RecoveryEmailAddress.toDomain(): RecoveryEmailAddress = RecoveryEmailAddress(
    recoveryEmailAddress = this.recoveryEmailAddress
)

