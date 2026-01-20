package com.xxcactussell.data.utils.mappers.account

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AccountInfo.toData(): TdApi.AccountInfo = TdApi.AccountInfo(
    this.registrationMonth,
    this.registrationYear,
    this.phoneNumberCountryCode,
    this.lastNameChangeDate,
    this.lastPhotoChangeDate
)

fun AccountTtl.toData(): TdApi.AccountTtl = TdApi.AccountTtl(
    this.days
)

