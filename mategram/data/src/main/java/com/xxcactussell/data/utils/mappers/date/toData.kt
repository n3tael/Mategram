package com.xxcactussell.data.utils.mappers.date

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Date.toData(): TdApi.Date = TdApi.Date(
    this.day,
    this.month,
    this.year
)

fun DateRange.toData(): TdApi.DateRange = TdApi.DateRange(
    this.startDate,
    this.endDate
)

