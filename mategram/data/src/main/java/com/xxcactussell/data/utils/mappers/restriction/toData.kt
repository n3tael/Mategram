package com.xxcactussell.data.utils.mappers.restriction

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RestrictionInfo.toData(): TdApi.RestrictionInfo = TdApi.RestrictionInfo(
    this.restrictionReason,
    this.hasSensitiveContent
)

