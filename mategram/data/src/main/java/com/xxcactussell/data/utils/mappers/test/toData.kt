package com.xxcactussell.data.utils.mappers.test

import com.xxcactussell.data.utils.mappers.error.toData
import com.xxcactussell.data.utils.mappers.proxy.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun TestBytes.toData(): TdApi.TestBytes = TdApi.TestBytes(
    this.value
)

fun TestCallBytes.toData(): TdApi.TestCallBytes = TdApi.TestCallBytes(
    this.x
)

fun TestCallEmpty.toData(): TdApi.TestCallEmpty = TdApi.TestCallEmpty(
)

fun TestCallString.toData(): TdApi.TestCallString = TdApi.TestCallString(
    this.x
)

fun TestCallVectorInt.toData(): TdApi.TestCallVectorInt = TdApi.TestCallVectorInt(
    this.x
)

fun TestCallVectorIntObject.toData(): TdApi.TestCallVectorIntObject = TdApi.TestCallVectorIntObject(
    this.x.map { it.toData() }.toTypedArray()
)

fun TestCallVectorString.toData(): TdApi.TestCallVectorString = TdApi.TestCallVectorString(
    this.x.toTypedArray()
)

fun TestCallVectorStringObject.toData(): TdApi.TestCallVectorStringObject = TdApi.TestCallVectorStringObject(
    this.x.map { it.toData() }.toTypedArray()
)

fun TestGetDifference.toData(): TdApi.TestGetDifference = TdApi.TestGetDifference(
)

fun TestInt.toData(): TdApi.TestInt = TdApi.TestInt(
    this.value
)

fun TestNetwork.toData(): TdApi.TestNetwork = TdApi.TestNetwork(
)

fun TestProxy.toData(): TdApi.TestProxy = TdApi.TestProxy(
    this.server,
    this.port,
    this.type.toData(),
    this.dcId,
    this.timeout
)

fun TestReturnError.toData(): TdApi.TestReturnError = TdApi.TestReturnError(
    this.error.toData()
)

fun TestSquareInt.toData(): TdApi.TestSquareInt = TdApi.TestSquareInt(
    this.x
)

fun TestString.toData(): TdApi.TestString = TdApi.TestString(
    this.value
)

fun TestUseUpdate.toData(): TdApi.TestUseUpdate = TdApi.TestUseUpdate(
)

fun TestVectorInt.toData(): TdApi.TestVectorInt = TdApi.TestVectorInt(
    this.value
)

fun TestVectorIntObject.toData(): TdApi.TestVectorIntObject = TdApi.TestVectorIntObject(
    this.value.map { it.toData() }.toTypedArray()
)

fun TestVectorString.toData(): TdApi.TestVectorString = TdApi.TestVectorString(
    this.value.toTypedArray()
)

fun TestVectorStringObject.toData(): TdApi.TestVectorStringObject = TdApi.TestVectorStringObject(
    this.value.map { it.toData() }.toTypedArray()
)

