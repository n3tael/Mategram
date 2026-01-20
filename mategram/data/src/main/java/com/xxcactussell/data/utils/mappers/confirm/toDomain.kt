package com.xxcactussell.data.utils.mappers.confirm

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ConfirmQrCodeAuthentication.toDomain(): ConfirmQrCodeAuthentication = ConfirmQrCodeAuthentication(
    link = this.link
)

fun TdApi.ConfirmSession.toDomain(): ConfirmSession = ConfirmSession(
    sessionId = this.sessionId
)

