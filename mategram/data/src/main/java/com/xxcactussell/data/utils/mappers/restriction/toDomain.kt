package com.xxcactussell.data.utils.mappers.restriction

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.RestrictionInfo.toDomain(): RestrictionInfo = RestrictionInfo(
    restrictionReason = this.restrictionReason,
    hasSensitiveContent = this.hasSensitiveContent
)

