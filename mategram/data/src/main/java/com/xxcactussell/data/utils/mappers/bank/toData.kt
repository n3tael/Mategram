package com.xxcactussell.data.utils.mappers.bank

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun BankCardActionOpenUrl.toData(): TdApi.BankCardActionOpenUrl = TdApi.BankCardActionOpenUrl(
    this.text,
    this.url
)

fun BankCardInfo.toData(): TdApi.BankCardInfo = TdApi.BankCardInfo(
    this.title,
    this.actions.map { it.toData() }.toTypedArray()
)

