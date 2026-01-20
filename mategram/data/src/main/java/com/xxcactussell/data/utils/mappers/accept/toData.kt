package com.xxcactussell.data.utils.mappers.accept

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AcceptTermsOfService.toData(): TdApi.AcceptTermsOfService = TdApi.AcceptTermsOfService(
    this.termsOfServiceId
)

