package com.xxcactussell.data.utils.mappers.location

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Location.toData(): TdApi.Location = TdApi.Location(
    this.latitude,
    this.longitude,
    this.horizontalAccuracy
)

fun LocationAddress.toData(): TdApi.LocationAddress = TdApi.LocationAddress(
    this.countryCode,
    this.state,
    this.city,
    this.street
)

