package com.xxcactussell.data.utils.mappers.accepted

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AcceptedGiftTypes.toData(): TdApi.AcceptedGiftTypes = TdApi.AcceptedGiftTypes(
    this.unlimitedGifts,
    this.limitedGifts,
    this.upgradedGifts,
    this.premiumSubscription
)

