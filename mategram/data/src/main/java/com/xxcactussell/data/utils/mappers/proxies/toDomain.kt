package com.xxcactussell.data.utils.mappers.proxies

import com.xxcactussell.data.utils.mappers.proxy.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Proxies.toDomain(): Proxies = Proxies(
    proxies = this.proxies.map { it.toDomain() }
)

