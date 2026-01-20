package com.xxcactussell.data.utils.mappers.reuse

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ReuseStarSubscription.toData(): TdApi.ReuseStarSubscription = TdApi.ReuseStarSubscription(
    this.subscriptionId
)

