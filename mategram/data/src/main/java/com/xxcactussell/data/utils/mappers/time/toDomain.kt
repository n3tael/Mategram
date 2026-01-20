package com.xxcactussell.data.utils.mappers.time

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.TimeZone.toDomain(): TimeZone = TimeZone(
    id = this.id,
    name = this.name,
    utcTimeOffset = this.utcTimeOffset
)

fun TdApi.TimeZones.toDomain(): TimeZones = TimeZones(
    timeZones = this.timeZones.map { it.toDomain() }
)

