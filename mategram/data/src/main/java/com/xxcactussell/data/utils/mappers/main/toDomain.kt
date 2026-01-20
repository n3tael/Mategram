package com.xxcactussell.data.utils.mappers.main

import com.xxcactussell.data.utils.mappers.bots.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.MainWebApp.toDomain(): MainWebApp = MainWebApp(
    url = this.url,
    mode = this.mode.toDomain()
)

