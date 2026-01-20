package com.xxcactussell.data.utils.mappers.disconnect

import com.xxcactussell.data.utils.mappers.affiliate.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DisconnectAffiliateProgram.toData(): TdApi.DisconnectAffiliateProgram = TdApi.DisconnectAffiliateProgram(
    this.affiliate.toData(),
    this.url
)

