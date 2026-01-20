package com.xxcactussell.data.utils.mappers.age

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AgeVerificationParameters.toData(): TdApi.AgeVerificationParameters = TdApi.AgeVerificationParameters(
    this.minAge,
    this.verificationBotUsername,
    this.country
)

