package com.xxcactussell.data.utils.mappers.connect

import com.xxcactussell.data.utils.mappers.affiliate.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ConnectAffiliateProgram.toData(): TdApi.ConnectAffiliateProgram = TdApi.ConnectAffiliateProgram(
    this.affiliate.toData(),
    this.botUserId
)

