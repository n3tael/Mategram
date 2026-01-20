package com.xxcactussell.data.utils.mappers.network

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.NetworkType.toDomain(): NetworkType = when(this) {
    is TdApi.NetworkTypeNone -> this.toDomain()
    is TdApi.NetworkTypeMobile -> this.toDomain()
    is TdApi.NetworkTypeMobileRoaming -> this.toDomain()
    is TdApi.NetworkTypeWiFi -> this.toDomain()
    is TdApi.NetworkTypeOther -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.NetworkTypeMobile.toDomain(): NetworkTypeMobile = NetworkTypeMobile

fun TdApi.NetworkTypeMobileRoaming.toDomain(): NetworkTypeMobileRoaming = NetworkTypeMobileRoaming

fun TdApi.NetworkTypeNone.toDomain(): NetworkTypeNone = NetworkTypeNone

fun TdApi.NetworkTypeOther.toDomain(): NetworkTypeOther = NetworkTypeOther

fun TdApi.NetworkTypeWiFi.toDomain(): NetworkTypeWiFi = NetworkTypeWiFi

