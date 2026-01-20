package com.xxcactussell.data.utils.mappers.age

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AgeVerificationParameters.toDomain(): AgeVerificationParameters = AgeVerificationParameters(
    minAge = this.minAge,
    verificationBotUsername = this.verificationBotUsername,
    country = this.country
)

