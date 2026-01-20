package com.xxcactussell.data.utils.mappers.apply

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ApplyPremiumGiftCode.toDomain(): ApplyPremiumGiftCode = ApplyPremiumGiftCode(
    code = this.code
)

