package com.xxcactussell.data.utils.mappers.accept

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AcceptTermsOfService.toDomain(): AcceptTermsOfService = AcceptTermsOfService(
    termsOfServiceId = this.termsOfServiceId
)

