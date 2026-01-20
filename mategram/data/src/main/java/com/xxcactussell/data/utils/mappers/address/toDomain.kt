package com.xxcactussell.data.utils.mappers.address

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Address.toDomain(): Address = Address(
    countryCode = this.countryCode,
    state = this.state,
    city = this.city,
    streetLine1 = this.streetLine1,
    streetLine2 = this.streetLine2,
    postalCode = this.postalCode
)

