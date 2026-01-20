package com.xxcactussell.data.utils.mappers.terms

import com.xxcactussell.data.utils.mappers.formatted.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun TermsOfService.toData(): TdApi.TermsOfService = TdApi.TermsOfService(
    this.text.toData(),
    this.minUserAge,
    this.showPopup
)

