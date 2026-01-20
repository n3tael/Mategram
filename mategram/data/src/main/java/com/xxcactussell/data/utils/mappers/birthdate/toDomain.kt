package com.xxcactussell.data.utils.mappers.birthdate

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Birthdate.toDomain(): Birthdate = Birthdate(
    day = this.day,
    month = this.month,
    year = this.year
)

