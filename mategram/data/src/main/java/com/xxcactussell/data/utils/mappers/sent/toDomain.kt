package com.xxcactussell.data.utils.mappers.sent

import com.xxcactussell.data.utils.mappers.monetization.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SentGift.toDomain(): SentGift = when(this) {
    is TdApi.SentGiftRegular -> this.toDomain()
    is TdApi.SentGiftUpgraded -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SentGiftRegular.toDomain(): SentGiftRegular = SentGiftRegular(
    gift = this.gift.toDomain()
)

fun TdApi.SentGiftUpgraded.toDomain(): SentGiftUpgraded = SentGiftUpgraded(
    gift = this.gift.toDomain()
)

fun TdApi.SentWebAppMessage.toDomain(): SentWebAppMessage = SentWebAppMessage(
    inlineMessageId = this.inlineMessageId
)

