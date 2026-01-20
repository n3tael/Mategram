package com.xxcactussell.data.utils.mappers.request

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RequestPasswordRecovery.toData(): TdApi.RequestPasswordRecovery = TdApi.RequestPasswordRecovery(
)

