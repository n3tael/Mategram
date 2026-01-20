package com.xxcactussell.data.utils.mappers.labeled

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.LabeledPricePart.toDomain(): LabeledPricePart = LabeledPricePart(
    label = this.label,
    amount = this.amount
)

