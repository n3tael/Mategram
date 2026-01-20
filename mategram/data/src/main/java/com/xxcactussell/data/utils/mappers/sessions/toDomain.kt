package com.xxcactussell.data.utils.mappers.sessions

import com.xxcactussell.data.utils.mappers.session.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Sessions.toDomain(): Sessions = Sessions(
    sessions = this.sessions.map { it.toDomain() },
    inactiveSessionTtlDays = this.inactiveSessionTtlDays
)

