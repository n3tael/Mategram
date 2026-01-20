package com.xxcactussell.data.utils.mappers.assign

import com.xxcactussell.data.utils.mappers.store.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AssignStoreTransaction.toData(): TdApi.AssignStoreTransaction = TdApi.AssignStoreTransaction(
    this.transaction.toData(),
    this.purpose.toData()
)

