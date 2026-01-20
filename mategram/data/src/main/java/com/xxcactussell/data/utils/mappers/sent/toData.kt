package com.xxcactussell.data.utils.mappers.sent

import com.xxcactussell.data.utils.mappers.monetization.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SentGift.toData(): TdApi.SentGift = when(this) {
    is SentGiftRegular -> this.toData()
    is SentGiftUpgraded -> this.toData()
}

fun SentGiftRegular.toData(): TdApi.SentGiftRegular = TdApi.SentGiftRegular(
    this.gift.toData()
)

fun SentGiftUpgraded.toData(): TdApi.SentGiftUpgraded = TdApi.SentGiftUpgraded(
    this.gift.toData()
)

fun SentWebAppMessage.toData(): TdApi.SentWebAppMessage = TdApi.SentWebAppMessage(
    this.inlineMessageId
)

