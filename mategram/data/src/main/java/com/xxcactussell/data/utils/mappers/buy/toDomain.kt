package com.xxcactussell.data.utils.mappers.buy

import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.BuyGiftUpgrade.toDomain(): BuyGiftUpgrade = BuyGiftUpgrade(
    ownerId = this.ownerId.toDomain(),
    prepaidUpgradeHash = this.prepaidUpgradeHash,
    starCount = this.starCount
)

