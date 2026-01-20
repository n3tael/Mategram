package com.xxcactussell.data.utils.mappers.network

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun NetworkType.toData(): TdApi.NetworkType = when(this) {
    is NetworkTypeNone -> this.toData()
    is NetworkTypeMobile -> this.toData()
    is NetworkTypeMobileRoaming -> this.toData()
    is NetworkTypeWiFi -> this.toData()
    is NetworkTypeOther -> this.toData()
}

fun NetworkTypeMobile.toData(): TdApi.NetworkTypeMobile = TdApi.NetworkTypeMobile(
)

fun NetworkTypeMobileRoaming.toData(): TdApi.NetworkTypeMobileRoaming = TdApi.NetworkTypeMobileRoaming(
)

fun NetworkTypeNone.toData(): TdApi.NetworkTypeNone = TdApi.NetworkTypeNone(
)

fun NetworkTypeOther.toData(): TdApi.NetworkTypeOther = TdApi.NetworkTypeOther(
)

fun NetworkTypeWiFi.toData(): TdApi.NetworkTypeWiFi = TdApi.NetworkTypeWiFi(
)

