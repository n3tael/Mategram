package com.xxcactussell.data.utils.mappers.venue

import com.xxcactussell.data.utils.mappers.location.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Venue.toDomain(): Venue = Venue(
    location = this.location.toDomain(),
    title = this.title,
    address = this.address,
    provider = this.provider,
    id = this.id,
    type = this.type
)

