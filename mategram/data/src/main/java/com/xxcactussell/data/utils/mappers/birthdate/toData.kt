package com.xxcactussell.data.utils.mappers.birthdate

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Birthdate.toData(): TdApi.Birthdate = TdApi.Birthdate(
    this.day,
    this.month,
    this.year
)

