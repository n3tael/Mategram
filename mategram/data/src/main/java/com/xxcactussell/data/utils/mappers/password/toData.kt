package com.xxcactussell.data.utils.mappers.password

import com.xxcactussell.data.utils.mappers.email.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun PasswordState.toData(): TdApi.PasswordState = TdApi.PasswordState(
    this.hasPassword,
    this.passwordHint,
    this.hasRecoveryEmailAddress,
    this.hasPassportData,
    this.recoveryEmailAddressCodeInfo?.toData(),
    this.loginEmailAddressPattern,
    this.pendingResetDate
)

