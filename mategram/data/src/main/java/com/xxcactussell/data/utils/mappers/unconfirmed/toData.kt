package com.xxcactussell.data.utils.mappers.unconfirmed

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun UnconfirmedSession.toData(): TdApi.UnconfirmedSession = TdApi.UnconfirmedSession(
    this.id,
    this.logInDate,
    this.deviceModel,
    this.location
)

