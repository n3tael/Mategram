package com.xxcactussell.data.utils.mappers.sell

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SellGift.toData(): TdApi.SellGift = TdApi.SellGift(
    this.businessConnectionId,
    this.receivedGiftId
)

