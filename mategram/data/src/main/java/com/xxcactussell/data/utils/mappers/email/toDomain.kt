package com.xxcactussell.data.utils.mappers.email

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.EmailAddressAuthentication.toDomain(): EmailAddressAuthentication = when(this) {
    is TdApi.EmailAddressAuthenticationCode -> this.toDomain()
    is TdApi.EmailAddressAuthenticationAppleId -> this.toDomain()
    is TdApi.EmailAddressAuthenticationGoogleId -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.EmailAddressAuthenticationAppleId.toDomain(): EmailAddressAuthenticationAppleId = EmailAddressAuthenticationAppleId(
    token = this.token
)

fun TdApi.EmailAddressAuthenticationCode.toDomain(): EmailAddressAuthenticationCode = EmailAddressAuthenticationCode(
    code = this.code
)

fun TdApi.EmailAddressAuthenticationCodeInfo.toDomain(): EmailAddressAuthenticationCodeInfo = EmailAddressAuthenticationCodeInfo(
    emailAddressPattern = this.emailAddressPattern,
    length = this.length
)

fun TdApi.EmailAddressAuthenticationGoogleId.toDomain(): EmailAddressAuthenticationGoogleId = EmailAddressAuthenticationGoogleId(
    token = this.token
)

fun TdApi.EmailAddressResetState.toDomain(): EmailAddressResetState = when(this) {
    is TdApi.EmailAddressResetStateAvailable -> this.toDomain()
    is TdApi.EmailAddressResetStatePending -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.EmailAddressResetStateAvailable.toDomain(): EmailAddressResetStateAvailable = EmailAddressResetStateAvailable(
    waitPeriod = this.waitPeriod
)

fun TdApi.EmailAddressResetStatePending.toDomain(): EmailAddressResetStatePending = EmailAddressResetStatePending(
    resetIn = this.resetIn
)

