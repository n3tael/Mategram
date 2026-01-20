package com.xxcactussell.data.utils.mappers.connect

import com.xxcactussell.data.utils.mappers.affiliate.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ConnectAffiliateProgram.toDomain(): ConnectAffiliateProgram = ConnectAffiliateProgram(
    affiliate = this.affiliate.toDomain(),
    botUserId = this.botUserId
)

