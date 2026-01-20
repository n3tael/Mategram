package com.xxcactussell.data.utils.mappers.email

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun EmailAddressAuthentication.toData(): TdApi.EmailAddressAuthentication = when(this) {
    is EmailAddressAuthenticationCode -> this.toData()
    is EmailAddressAuthenticationAppleId -> this.toData()
    is EmailAddressAuthenticationGoogleId -> this.toData()
}

fun EmailAddressAuthenticationAppleId.toData(): TdApi.EmailAddressAuthenticationAppleId = TdApi.EmailAddressAuthenticationAppleId(
    this.token
)

fun EmailAddressAuthenticationCode.toData(): TdApi.EmailAddressAuthenticationCode = TdApi.EmailAddressAuthenticationCode(
    this.code
)

fun EmailAddressAuthenticationCodeInfo.toData(): TdApi.EmailAddressAuthenticationCodeInfo = TdApi.EmailAddressAuthenticationCodeInfo(
    this.emailAddressPattern,
    this.length
)

fun EmailAddressAuthenticationGoogleId.toData(): TdApi.EmailAddressAuthenticationGoogleId = TdApi.EmailAddressAuthenticationGoogleId(
    this.token
)

fun EmailAddressResetState.toData(): TdApi.EmailAddressResetState = when(this) {
    is EmailAddressResetStateAvailable -> this.toData()
    is EmailAddressResetStatePending -> this.toData()
}

fun EmailAddressResetStateAvailable.toData(): TdApi.EmailAddressResetStateAvailable = TdApi.EmailAddressResetStateAvailable(
    this.waitPeriod
)

fun EmailAddressResetStatePending.toData(): TdApi.EmailAddressResetStatePending = TdApi.EmailAddressResetStatePending(
    this.resetIn
)

