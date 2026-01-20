package com.xxcactussell.data.utils.mappers.recover

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RecoverPassword.toData(): TdApi.RecoverPassword = TdApi.RecoverPassword(
    this.recoveryCode,
    this.newPassword,
    this.newHint
)

