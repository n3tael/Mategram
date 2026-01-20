package com.xxcactussell.data.utils.mappers.bank

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.BankCardActionOpenUrl.toDomain(): BankCardActionOpenUrl = BankCardActionOpenUrl(
    text = this.text,
    url = this.url
)

fun TdApi.BankCardInfo.toDomain(): BankCardInfo = BankCardInfo(
    title = this.title,
    actions = this.actions.map { it.toDomain() }
)

