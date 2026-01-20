package com.xxcactussell.data.utils.mappers.country

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun CountryInfo.toData(): TdApi.CountryInfo = TdApi.CountryInfo(
    this.countryCode,
    this.name,
    this.englishName,
    this.isHidden,
    this.callingCodes.toTypedArray()
)

