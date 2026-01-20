package com.xxcactussell.data.utils.mappers.collectible

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.CollectibleItemInfo.toDomain(): CollectibleItemInfo = CollectibleItemInfo(
    purchaseDate = this.purchaseDate,
    currency = this.currency,
    amount = this.amount,
    cryptocurrency = this.cryptocurrency,
    cryptocurrencyAmount = this.cryptocurrencyAmount,
    url = this.url
)

fun TdApi.CollectibleItemType.toDomain(): CollectibleItemType = when(this) {
    is TdApi.CollectibleItemTypeUsername -> this.toDomain()
    is TdApi.CollectibleItemTypePhoneNumber -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.CollectibleItemTypePhoneNumber.toDomain(): CollectibleItemTypePhoneNumber = CollectibleItemTypePhoneNumber(
    phoneNumber = this.phoneNumber
)

fun TdApi.CollectibleItemTypeUsername.toDomain(): CollectibleItemTypeUsername = CollectibleItemTypeUsername(
    username = this.username
)

