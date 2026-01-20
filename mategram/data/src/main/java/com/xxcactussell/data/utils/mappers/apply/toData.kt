package com.xxcactussell.data.utils.mappers.apply

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ApplyPremiumGiftCode.toData(): TdApi.ApplyPremiumGiftCode = TdApi.ApplyPremiumGiftCode(
    this.code
)

