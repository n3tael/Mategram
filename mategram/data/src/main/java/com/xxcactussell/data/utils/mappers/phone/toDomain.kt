package com.xxcactussell.data.utils.mappers.phone

import com.xxcactussell.data.utils.mappers.country.toDomain
import com.xxcactussell.data.utils.mappers.firebase.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.PhoneNumberAuthenticationSettings.toDomain(): PhoneNumberAuthenticationSettings = PhoneNumberAuthenticationSettings(
    allowFlashCall = this.allowFlashCall,
    allowMissedCall = this.allowMissedCall,
    isCurrentPhoneNumber = this.isCurrentPhoneNumber,
    hasUnknownPhoneNumber = this.hasUnknownPhoneNumber,
    allowSmsRetrieverApi = this.allowSmsRetrieverApi,
    firebaseAuthenticationSettings = this.firebaseAuthenticationSettings.toDomain(),
    authenticationTokens = this.authenticationTokens.toList()
)

fun TdApi.PhoneNumberCodeType.toDomain(): PhoneNumberCodeType = when(this) {
    is TdApi.PhoneNumberCodeTypeChange -> this.toDomain()
    is TdApi.PhoneNumberCodeTypeVerify -> this.toDomain()
    is TdApi.PhoneNumberCodeTypeConfirmOwnership -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PhoneNumberCodeTypeChange.toDomain(): PhoneNumberCodeTypeChange = PhoneNumberCodeTypeChange

fun TdApi.PhoneNumberCodeTypeConfirmOwnership.toDomain(): PhoneNumberCodeTypeConfirmOwnership = PhoneNumberCodeTypeConfirmOwnership(
    hash = this.hash
)

fun TdApi.PhoneNumberCodeTypeVerify.toDomain(): PhoneNumberCodeTypeVerify = PhoneNumberCodeTypeVerify

fun TdApi.PhoneNumberInfo.toDomain(): PhoneNumberInfo = PhoneNumberInfo(
    country = this.country?.toDomain(),
    countryCallingCode = this.countryCallingCode,
    formattedPhoneNumber = this.formattedPhoneNumber,
    isAnonymous = this.isAnonymous
)

