package com.xxcactussell.data.utils.mappers.terms

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.TermsOfService.toDomain(): TermsOfService = TermsOfService(
    text = this.text.toDomain(),
    minUserAge = this.minUserAge,
    showPopup = this.showPopup
)

