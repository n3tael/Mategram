package com.xxcactussell.data.utils.mappers.fact

import com.xxcactussell.data.utils.mappers.formatted.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun FactCheck.toData(): TdApi.FactCheck = TdApi.FactCheck(
    this.text.toData(),
    this.countryCode
)

