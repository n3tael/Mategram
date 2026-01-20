package com.xxcactussell.data.utils.mappers.account

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AccountInfo.toDomain(): AccountInfo = AccountInfo(
    registrationMonth = this.registrationMonth,
    registrationYear = this.registrationYear,
    phoneNumberCountryCode = this.phoneNumberCountryCode,
    lastNameChangeDate = this.lastNameChangeDate,
    lastPhotoChangeDate = this.lastPhotoChangeDate
)

fun TdApi.AccountTtl.toDomain(): AccountTtl = AccountTtl(
    days = this.days
)

