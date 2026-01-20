package com.xxcactussell.data.utils.mappers.sessions

import com.xxcactussell.data.utils.mappers.session.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Sessions.toData(): TdApi.Sessions = TdApi.Sessions(
    this.sessions.map { it.toData() }.toTypedArray(),
    this.inactiveSessionTtlDays
)

