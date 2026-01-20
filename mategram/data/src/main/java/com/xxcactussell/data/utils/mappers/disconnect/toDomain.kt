package com.xxcactussell.data.utils.mappers.disconnect

import com.xxcactussell.data.utils.mappers.affiliate.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DisconnectAffiliateProgram.toDomain(): DisconnectAffiliateProgram = DisconnectAffiliateProgram(
    affiliate = this.affiliate.toDomain(),
    url = this.url
)

