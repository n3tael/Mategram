package com.xxcactussell.data.utils.mappers.reset

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ResetAllNotificationSettings.toDomain(): ResetAllNotificationSettings = ResetAllNotificationSettings

fun TdApi.ResetAuthenticationEmailAddress.toDomain(): ResetAuthenticationEmailAddress = ResetAuthenticationEmailAddress

fun TdApi.ResetInstalledBackgrounds.toDomain(): ResetInstalledBackgrounds = ResetInstalledBackgrounds

fun TdApi.ResetPassword.toDomain(): ResetPassword = ResetPassword

fun TdApi.ResetPasswordResult.toDomain(): ResetPasswordResult = when(this) {
    is TdApi.ResetPasswordResultOk -> this.toDomain()
    is TdApi.ResetPasswordResultPending -> this.toDomain()
    is TdApi.ResetPasswordResultDeclined -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ResetPasswordResultDeclined.toDomain(): ResetPasswordResultDeclined = ResetPasswordResultDeclined(
    retryDate = this.retryDate
)

fun TdApi.ResetPasswordResultOk.toDomain(): ResetPasswordResultOk = ResetPasswordResultOk

fun TdApi.ResetPasswordResultPending.toDomain(): ResetPasswordResultPending = ResetPasswordResultPending(
    pendingResetDate = this.pendingResetDate
)

