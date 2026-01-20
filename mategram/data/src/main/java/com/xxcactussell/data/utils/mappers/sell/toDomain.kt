package com.xxcactussell.data.utils.mappers.sell

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SellGift.toDomain(): SellGift = SellGift(
    businessConnectionId = this.businessConnectionId,
    receivedGiftId = this.receivedGiftId
)

