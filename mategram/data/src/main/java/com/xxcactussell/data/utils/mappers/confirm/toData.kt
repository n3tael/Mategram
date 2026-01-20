package com.xxcactussell.data.utils.mappers.confirm

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ConfirmQrCodeAuthentication.toData(): TdApi.ConfirmQrCodeAuthentication = TdApi.ConfirmQrCodeAuthentication(
    this.link
)

fun ConfirmSession.toData(): TdApi.ConfirmSession = TdApi.ConfirmSession(
    this.sessionId
)

