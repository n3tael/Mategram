package com.xxcactussell.data.utils.mappers.shipping

import com.xxcactussell.data.utils.mappers.labeled.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ShippingOption.toDomain(): ShippingOption = ShippingOption(
    id = this.id,
    title = this.title,
    priceParts = this.priceParts.map { it.toDomain() }
)

