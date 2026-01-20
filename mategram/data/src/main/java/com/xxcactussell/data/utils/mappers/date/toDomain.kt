package com.xxcactussell.data.utils.mappers.date

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Date.toDomain(): Date = Date(
    day = this.day,
    month = this.month,
    year = this.year
)

fun TdApi.DateRange.toDomain(): DateRange = DateRange(
    startDate = this.startDate,
    endDate = this.endDate
)

