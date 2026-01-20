package com.xxcactussell.data.utils.mappers.time

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun TimeZone.toData(): TdApi.TimeZone = TdApi.TimeZone(
    this.id,
    this.name,
    this.utcTimeOffset
)

fun TimeZones.toData(): TdApi.TimeZones = TdApi.TimeZones(
    this.timeZones.map { it.toData() }.toTypedArray()
)

