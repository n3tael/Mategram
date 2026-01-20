package com.xxcactussell.data.utils.mappers.transaction

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun TransactionDirection.toData(): TdApi.TransactionDirection = when(this) {
    is TransactionDirectionIncoming -> this.toData()
    is TransactionDirectionOutgoing -> this.toData()
}

fun TransactionDirectionIncoming.toData(): TdApi.TransactionDirectionIncoming = TdApi.TransactionDirectionIncoming(
)

fun TransactionDirectionOutgoing.toData(): TdApi.TransactionDirectionOutgoing = TdApi.TransactionDirectionOutgoing(
)

