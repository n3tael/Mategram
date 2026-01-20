package com.xxcactussell.data.utils.mappers.connection

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ConnectionState.toData(): TdApi.ConnectionState = when(this) {
    is ConnectionStateWaitingForNetwork -> this.toData()
    is ConnectionStateConnectingToProxy -> this.toData()
    is ConnectionStateConnecting -> this.toData()
    is ConnectionStateUpdating -> this.toData()
    is ConnectionStateReady -> this.toData()
}

fun ConnectionStateConnecting.toData(): TdApi.ConnectionStateConnecting = TdApi.ConnectionStateConnecting(
)

fun ConnectionStateConnectingToProxy.toData(): TdApi.ConnectionStateConnectingToProxy = TdApi.ConnectionStateConnectingToProxy(
)

fun ConnectionStateReady.toData(): TdApi.ConnectionStateReady = TdApi.ConnectionStateReady(
)

fun ConnectionStateUpdating.toData(): TdApi.ConnectionStateUpdating = TdApi.ConnectionStateUpdating(
)

fun ConnectionStateWaitingForNetwork.toData(): TdApi.ConnectionStateWaitingForNetwork = TdApi.ConnectionStateWaitingForNetwork(
)

