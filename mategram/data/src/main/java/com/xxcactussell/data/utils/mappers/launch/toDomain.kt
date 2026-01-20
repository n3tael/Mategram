package com.xxcactussell.data.utils.mappers.launch

import com.xxcactussell.data.utils.mappers.giveaway.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.LaunchPrepaidGiveaway.toDomain(): LaunchPrepaidGiveaway = LaunchPrepaidGiveaway(
    giveawayId = this.giveawayId,
    parameters = this.parameters.toDomain(),
    winnerCount = this.winnerCount,
    starCount = this.starCount
)

