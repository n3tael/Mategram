package com.xxcactussell.data.utils.mappers.connection

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ConnectionState.toDomain(): ConnectionState = when(this) {
    is TdApi.ConnectionStateWaitingForNetwork -> this.toDomain()
    is TdApi.ConnectionStateConnectingToProxy -> this.toDomain()
    is TdApi.ConnectionStateConnecting -> this.toDomain()
    is TdApi.ConnectionStateUpdating -> this.toDomain()
    is TdApi.ConnectionStateReady -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ConnectionStateConnecting.toDomain(): ConnectionStateConnecting = ConnectionStateConnecting

fun TdApi.ConnectionStateConnectingToProxy.toDomain(): ConnectionStateConnectingToProxy = ConnectionStateConnectingToProxy

fun TdApi.ConnectionStateReady.toDomain(): ConnectionStateReady = ConnectionStateReady

fun TdApi.ConnectionStateUpdating.toDomain(): ConnectionStateUpdating = ConnectionStateUpdating

fun TdApi.ConnectionStateWaitingForNetwork.toDomain(): ConnectionStateWaitingForNetwork = ConnectionStateWaitingForNetwork

