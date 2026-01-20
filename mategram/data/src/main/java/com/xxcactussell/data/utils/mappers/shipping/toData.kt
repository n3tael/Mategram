package com.xxcactussell.data.utils.mappers.shipping

import com.xxcactussell.data.utils.mappers.labeled.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ShippingOption.toData(): TdApi.ShippingOption = TdApi.ShippingOption(
    this.id,
    this.title,
    this.priceParts.map { it.toData() }.toTypedArray()
)

