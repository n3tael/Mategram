package com.xxcactussell.data.utils.mappers.prepaid

import com.xxcactussell.data.utils.mappers.giveaway.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun PrepaidGiveaway.toData(): TdApi.PrepaidGiveaway = TdApi.PrepaidGiveaway(
    this.id,
    this.winnerCount,
    this.prize.toData(),
    this.boostCount,
    this.paymentDate
)

