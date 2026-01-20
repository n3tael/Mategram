package com.xxcactussell.data.utils.mappers.test

import com.xxcactussell.data.utils.mappers.error.toDomain
import com.xxcactussell.data.utils.mappers.proxy.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.TestBytes.toDomain(): TestBytes = TestBytes(
    value = this.value
)

fun TdApi.TestCallBytes.toDomain(): TestCallBytes = TestCallBytes(
    x = this.x
)

fun TdApi.TestCallEmpty.toDomain(): TestCallEmpty = TestCallEmpty

fun TdApi.TestCallString.toDomain(): TestCallString = TestCallString(
    x = this.x
)

fun TdApi.TestCallVectorInt.toDomain(): TestCallVectorInt = TestCallVectorInt(
    x = this.x
)

fun TdApi.TestCallVectorIntObject.toDomain(): TestCallVectorIntObject = TestCallVectorIntObject(
    x = this.x.map { it.toDomain() }
)

fun TdApi.TestCallVectorString.toDomain(): TestCallVectorString = TestCallVectorString(
    x = this.x.toList()
)

fun TdApi.TestCallVectorStringObject.toDomain(): TestCallVectorStringObject = TestCallVectorStringObject(
    x = this.x.map { it.toDomain() }
)

fun TdApi.TestGetDifference.toDomain(): TestGetDifference = TestGetDifference

fun TdApi.TestInt.toDomain(): TestInt = TestInt(
    value = this.value
)

fun TdApi.TestNetwork.toDomain(): TestNetwork = TestNetwork

fun TdApi.TestProxy.toDomain(): TestProxy = TestProxy(
    server = this.server,
    port = this.port,
    type = this.type.toDomain(),
    dcId = this.dcId,
    timeout = this.timeout
)

fun TdApi.TestReturnError.toDomain(): TestReturnError = TestReturnError(
    error = this.error.toDomain()
)

fun TdApi.TestSquareInt.toDomain(): TestSquareInt = TestSquareInt(
    x = this.x
)

fun TdApi.TestString.toDomain(): TestString = TestString(
    value = this.value
)

fun TdApi.TestUseUpdate.toDomain(): TestUseUpdate = TestUseUpdate

fun TdApi.TestVectorInt.toDomain(): TestVectorInt = TestVectorInt(
    value = this.value
)

fun TdApi.TestVectorIntObject.toDomain(): TestVectorIntObject = TestVectorIntObject(
    value = this.value.map { it.toDomain() }
)

fun TdApi.TestVectorString.toDomain(): TestVectorString = TestVectorString(
    value = this.value.toList()
)

fun TdApi.TestVectorStringObject.toDomain(): TestVectorStringObject = TestVectorStringObject(
    value = this.value.map { it.toDomain() }
)

