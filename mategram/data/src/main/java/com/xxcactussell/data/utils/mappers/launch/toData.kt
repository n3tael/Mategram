package com.xxcactussell.data.utils.mappers.launch

import com.xxcactussell.data.utils.mappers.giveaway.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun LaunchPrepaidGiveaway.toData(): TdApi.LaunchPrepaidGiveaway = TdApi.LaunchPrepaidGiveaway(
    this.giveawayId,
    this.parameters.toData(),
    this.winnerCount,
    this.starCount
)

