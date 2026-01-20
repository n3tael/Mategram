package com.xxcactussell.data.utils.mappers.main

import com.xxcactussell.data.utils.mappers.bots.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun MainWebApp.toData(): TdApi.MainWebApp = TdApi.MainWebApp(
    this.url,
    this.mode.toData()
)

