package com.xxcactussell.data.utils.mappers.reset

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ResetAllNotificationSettings.toData(): TdApi.ResetAllNotificationSettings = TdApi.ResetAllNotificationSettings(
)

fun ResetAuthenticationEmailAddress.toData(): TdApi.ResetAuthenticationEmailAddress = TdApi.ResetAuthenticationEmailAddress(
)

fun ResetInstalledBackgrounds.toData(): TdApi.ResetInstalledBackgrounds = TdApi.ResetInstalledBackgrounds(
)

fun ResetPassword.toData(): TdApi.ResetPassword = TdApi.ResetPassword(
)

fun ResetPasswordResult.toData(): TdApi.ResetPasswordResult = when(this) {
    is ResetPasswordResultOk -> this.toData()
    is ResetPasswordResultPending -> this.toData()
    is ResetPasswordResultDeclined -> this.toData()
}

fun ResetPasswordResultDeclined.toData(): TdApi.ResetPasswordResultDeclined = TdApi.ResetPasswordResultDeclined(
    this.retryDate
)

fun ResetPasswordResultOk.toData(): TdApi.ResetPasswordResultOk = TdApi.ResetPasswordResultOk(
)

fun ResetPasswordResultPending.toData(): TdApi.ResetPasswordResultPending = TdApi.ResetPasswordResultPending(
    this.pendingResetDate
)

