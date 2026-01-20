package com.xxcactussell.data.utils.mappers.proxies

import com.xxcactussell.data.utils.mappers.proxy.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Proxies.toData(): TdApi.Proxies = TdApi.Proxies(
    this.proxies.map { it.toData() }.toTypedArray()
)

