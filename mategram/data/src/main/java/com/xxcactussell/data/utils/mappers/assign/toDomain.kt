package com.xxcactussell.data.utils.mappers.assign

import com.xxcactussell.data.utils.mappers.store.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AssignStoreTransaction.toDomain(): AssignStoreTransaction = AssignStoreTransaction(
    transaction = this.transaction.toDomain(),
    purpose = this.purpose.toDomain()
)

