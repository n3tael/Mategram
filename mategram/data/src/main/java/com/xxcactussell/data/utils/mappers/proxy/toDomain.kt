package com.xxcactussell.data.utils.mappers.proxy

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Proxy.toDomain(): Proxy = Proxy(
    id = this.id,
    server = this.server,
    port = this.port,
    lastUsedDate = this.lastUsedDate,
    isEnabled = this.isEnabled,
    type = this.type.toDomain()
)

fun TdApi.ProxyType.toDomain(): ProxyType = when(this) {
    is TdApi.ProxyTypeSocks5 -> this.toDomain()
    is TdApi.ProxyTypeHttp -> this.toDomain()
    is TdApi.ProxyTypeMtproto -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ProxyTypeHttp.toDomain(): ProxyTypeHttp = ProxyTypeHttp(
    username = this.username,
    password = this.password,
    httpOnly = this.httpOnly
)

fun TdApi.ProxyTypeMtproto.toDomain(): ProxyTypeMtproto = ProxyTypeMtproto(
    secret = this.secret
)

fun TdApi.ProxyTypeSocks5.toDomain(): ProxyTypeSocks5 = ProxyTypeSocks5(
    username = this.username,
    password = this.password
)

