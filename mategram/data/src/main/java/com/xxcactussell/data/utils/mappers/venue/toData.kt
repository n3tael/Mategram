package com.xxcactussell.data.utils.mappers.venue

import com.xxcactussell.data.utils.mappers.location.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Venue.toData(): TdApi.Venue = TdApi.Venue(
    this.location.toData(),
    this.title,
    this.address,
    this.provider,
    this.id,
    this.type
)

