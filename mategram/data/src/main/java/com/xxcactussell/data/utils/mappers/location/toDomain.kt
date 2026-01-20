package com.xxcactussell.data.utils.mappers.location

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Location.toDomain(): Location = Location(
    latitude = this.latitude,
    longitude = this.longitude,
    horizontalAccuracy = this.horizontalAccuracy
)

fun TdApi.LocationAddress.toDomain(): LocationAddress = LocationAddress(
    countryCode = this.countryCode,
    state = this.state,
    city = this.city,
    street = this.street
)

