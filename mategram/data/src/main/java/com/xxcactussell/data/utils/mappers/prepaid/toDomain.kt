package com.xxcactussell.data.utils.mappers.prepaid

import com.xxcactussell.data.utils.mappers.giveaway.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.PrepaidGiveaway.toDomain(): PrepaidGiveaway = PrepaidGiveaway(
    id = this.id,
    winnerCount = this.winnerCount,
    prize = this.prize.toDomain(),
    boostCount = this.boostCount,
    paymentDate = this.paymentDate
)

