package com.xxcactussell.data.utils.mappers.buy

import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun BuyGiftUpgrade.toData(): TdApi.BuyGiftUpgrade = TdApi.BuyGiftUpgrade(
    this.ownerId.toData(),
    this.prepaidUpgradeHash,
    this.starCount
)

