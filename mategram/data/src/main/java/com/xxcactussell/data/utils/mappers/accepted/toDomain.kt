package com.xxcactussell.data.utils.mappers.accepted

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AcceptedGiftTypes.toDomain(): AcceptedGiftTypes = AcceptedGiftTypes(
    unlimitedGifts = this.unlimitedGifts,
    limitedGifts = this.limitedGifts,
    upgradedGifts = this.upgradedGifts,
    premiumSubscription = this.premiumSubscription
)

