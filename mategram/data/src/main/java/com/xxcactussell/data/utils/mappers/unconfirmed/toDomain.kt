package com.xxcactussell.data.utils.mappers.unconfirmed

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.UnconfirmedSession.toDomain(): UnconfirmedSession = UnconfirmedSession(
    id = this.id,
    logInDate = this.logInDate,
    deviceModel = this.deviceModel,
    location = this.location
)

