package com.xxcactussell.data.utils.mappers.phone

import com.xxcactussell.data.utils.mappers.country.toData
import com.xxcactussell.data.utils.mappers.firebase.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun PhoneNumberAuthenticationSettings.toData(): TdApi.PhoneNumberAuthenticationSettings = TdApi.PhoneNumberAuthenticationSettings(
    this.allowFlashCall,
    this.allowMissedCall,
    this.isCurrentPhoneNumber,
    this.hasUnknownPhoneNumber,
    this.allowSmsRetrieverApi,
    this.firebaseAuthenticationSettings.toData(),
    this.authenticationTokens.toTypedArray()
)

fun PhoneNumberCodeType.toData(): TdApi.PhoneNumberCodeType = when(this) {
    is PhoneNumberCodeTypeChange -> this.toData()
    is PhoneNumberCodeTypeVerify -> this.toData()
    is PhoneNumberCodeTypeConfirmOwnership -> this.toData()
}

fun PhoneNumberCodeTypeChange.toData(): TdApi.PhoneNumberCodeTypeChange = TdApi.PhoneNumberCodeTypeChange(
)

fun PhoneNumberCodeTypeConfirmOwnership.toData(): TdApi.PhoneNumberCodeTypeConfirmOwnership = TdApi.PhoneNumberCodeTypeConfirmOwnership(
    this.hash
)

fun PhoneNumberCodeTypeVerify.toData(): TdApi.PhoneNumberCodeTypeVerify = TdApi.PhoneNumberCodeTypeVerify(
)

fun PhoneNumberInfo.toData(): TdApi.PhoneNumberInfo = TdApi.PhoneNumberInfo(
    this.country?.toData(),
    this.countryCallingCode,
    this.formattedPhoneNumber,
    this.isAnonymous
)

