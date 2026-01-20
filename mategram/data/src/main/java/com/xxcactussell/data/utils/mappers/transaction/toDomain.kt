package com.xxcactussell.data.utils.mappers.transaction

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.TransactionDirection.toDomain(): TransactionDirection = when(this) {
    is TdApi.TransactionDirectionIncoming -> this.toDomain()
    is TdApi.TransactionDirectionOutgoing -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.TransactionDirectionIncoming.toDomain(): TransactionDirectionIncoming = TransactionDirectionIncoming

fun TdApi.TransactionDirectionOutgoing.toDomain(): TransactionDirectionOutgoing = TransactionDirectionOutgoing

