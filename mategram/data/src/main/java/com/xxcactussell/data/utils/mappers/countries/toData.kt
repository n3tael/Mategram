package com.xxcactussell.data.utils.mappers.countries

import com.xxcactussell.data.utils.mappers.country.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Countries.toData(): TdApi.Countries = TdApi.Countries(
    this.countries.map { it.toData() }.toTypedArray()
)

