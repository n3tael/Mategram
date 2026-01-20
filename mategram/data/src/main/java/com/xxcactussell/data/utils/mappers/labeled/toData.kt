package com.xxcactussell.data.utils.mappers.labeled

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun LabeledPricePart.toData(): TdApi.LabeledPricePart = TdApi.LabeledPricePart(
    this.label,
    this.amount
)

