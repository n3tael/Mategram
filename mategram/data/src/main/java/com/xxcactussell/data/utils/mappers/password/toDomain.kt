package com.xxcactussell.data.utils.mappers.password

import com.xxcactussell.data.utils.mappers.email.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.PasswordState.toDomain(): PasswordState = PasswordState(
    hasPassword = this.hasPassword,
    passwordHint = this.passwordHint,
    hasRecoveryEmailAddress = this.hasRecoveryEmailAddress,
    hasPassportData = this.hasPassportData,
    recoveryEmailAddressCodeInfo = this.recoveryEmailAddressCodeInfo?.toDomain(),
    loginEmailAddressPattern = this.loginEmailAddressPattern,
    pendingResetDate = this.pendingResetDate
)

