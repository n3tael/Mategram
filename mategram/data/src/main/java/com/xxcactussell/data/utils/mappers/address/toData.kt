package com.xxcactussell.data.utils.mappers.address

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Address.toData(): TdApi.Address = TdApi.Address(
    this.countryCode,
    this.state,
    this.city,
    this.streetLine1,
    this.streetLine2,
    this.postalCode
)

