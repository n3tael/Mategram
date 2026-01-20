package com.xxcactussell.data.utils.mappers.fact

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.FactCheck.toDomain(): FactCheck = FactCheck(
    text = this.text.toDomain(),
    countryCode = this.countryCode
)

