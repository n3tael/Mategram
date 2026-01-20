package com.xxcactussell.data.utils.mappers.proxy

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Proxy.toData(): TdApi.Proxy = TdApi.Proxy(
    this.id,
    this.server,
    this.port,
    this.lastUsedDate,
    this.isEnabled,
    this.type.toData()
)

fun ProxyType.toData(): TdApi.ProxyType = when(this) {
    is ProxyTypeSocks5 -> this.toData()
    is ProxyTypeHttp -> this.toData()
    is ProxyTypeMtproto -> this.toData()
}

fun ProxyTypeHttp.toData(): TdApi.ProxyTypeHttp = TdApi.ProxyTypeHttp(
    this.username,
    this.password,
    this.httpOnly
)

fun ProxyTypeMtproto.toData(): TdApi.ProxyTypeMtproto = TdApi.ProxyTypeMtproto(
    this.secret
)

fun ProxyTypeSocks5.toData(): TdApi.ProxyTypeSocks5 = TdApi.ProxyTypeSocks5(
    this.username,
    this.password
)

