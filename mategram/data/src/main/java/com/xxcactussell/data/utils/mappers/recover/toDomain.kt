package com.xxcactussell.data.utils.mappers.recover

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.RecoverPassword.toDomain(): RecoverPassword = RecoverPassword(
    recoveryCode = this.recoveryCode,
    newPassword = this.newPassword,
    newHint = this.newHint
)

