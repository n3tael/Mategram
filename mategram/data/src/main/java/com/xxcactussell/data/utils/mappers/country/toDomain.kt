package com.xxcactussell.data.utils.mappers.country

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.CountryInfo.toDomain(): CountryInfo = CountryInfo(
    countryCode = this.countryCode,
    name = this.name,
    englishName = this.englishName,
    isHidden = this.isHidden,
    callingCodes = this.callingCodes.toList()
)

