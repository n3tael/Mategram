package com.xxcactussell.data.utils.mappers.collectible

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun CollectibleItemInfo.toData(): TdApi.CollectibleItemInfo = TdApi.CollectibleItemInfo(
    this.purchaseDate,
    this.currency,
    this.amount,
    this.cryptocurrency,
    this.cryptocurrencyAmount,
    this.url
)

fun CollectibleItemType.toData(): TdApi.CollectibleItemType = when(this) {
    is CollectibleItemTypeUsername -> this.toData()
    is CollectibleItemTypePhoneNumber -> this.toData()
}

fun CollectibleItemTypePhoneNumber.toData(): TdApi.CollectibleItemTypePhoneNumber = TdApi.CollectibleItemTypePhoneNumber(
    this.phoneNumber
)

fun CollectibleItemTypeUsername.toData(): TdApi.CollectibleItemTypeUsername = TdApi.CollectibleItemTypeUsername(
    this.username
)

