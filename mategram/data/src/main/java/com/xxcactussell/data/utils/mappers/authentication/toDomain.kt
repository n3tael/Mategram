package com.xxcactussell.data.utils.mappers.authentication

import com.xxcactussell.data.utils.mappers.firebase.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AuthenticationCodeInfo.toDomain(): AuthenticationCodeInfo = AuthenticationCodeInfo(
    phoneNumber = this.phoneNumber,
    type = this.type.toDomain(),
    nextType = this.nextType?.toDomain(),
    timeout = this.timeout
)

fun TdApi.AuthenticationCodeType.toDomain(): AuthenticationCodeType = when(this) {
    is TdApi.AuthenticationCodeTypeTelegramMessage -> this.toDomain()
    is TdApi.AuthenticationCodeTypeSms -> this.toDomain()
    is TdApi.AuthenticationCodeTypeSmsWord -> this.toDomain()
    is TdApi.AuthenticationCodeTypeSmsPhrase -> this.toDomain()
    is TdApi.AuthenticationCodeTypeCall -> this.toDomain()
    is TdApi.AuthenticationCodeTypeFlashCall -> this.toDomain()
    is TdApi.AuthenticationCodeTypeMissedCall -> this.toDomain()
    is TdApi.AuthenticationCodeTypeFragment -> this.toDomain()
    is TdApi.AuthenticationCodeTypeFirebaseAndroid -> this.toDomain()
    is TdApi.AuthenticationCodeTypeFirebaseIos -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.AuthenticationCodeTypeCall.toDomain(): AuthenticationCodeTypeCall = AuthenticationCodeTypeCall(
    length = this.length
)

fun TdApi.AuthenticationCodeTypeFirebaseAndroid.toDomain(): AuthenticationCodeTypeFirebaseAndroid = AuthenticationCodeTypeFirebaseAndroid(
    deviceVerificationParameters = this.deviceVerificationParameters.toDomain(),
    length = this.length
)

fun TdApi.AuthenticationCodeTypeFirebaseIos.toDomain(): AuthenticationCodeTypeFirebaseIos = AuthenticationCodeTypeFirebaseIos(
    receipt = this.receipt,
    pushTimeout = this.pushTimeout,
    length = this.length
)

fun TdApi.AuthenticationCodeTypeFlashCall.toDomain(): AuthenticationCodeTypeFlashCall = AuthenticationCodeTypeFlashCall(
    pattern = this.pattern
)

fun TdApi.AuthenticationCodeTypeFragment.toDomain(): AuthenticationCodeTypeFragment = AuthenticationCodeTypeFragment(
    url = this.url,
    length = this.length
)

fun TdApi.AuthenticationCodeTypeMissedCall.toDomain(): AuthenticationCodeTypeMissedCall = AuthenticationCodeTypeMissedCall(
    phoneNumberPrefix = this.phoneNumberPrefix,
    length = this.length
)

fun TdApi.AuthenticationCodeTypeSms.toDomain(): AuthenticationCodeTypeSms = AuthenticationCodeTypeSms(
    length = this.length
)

fun TdApi.AuthenticationCodeTypeSmsPhrase.toDomain(): AuthenticationCodeTypeSmsPhrase = AuthenticationCodeTypeSmsPhrase(
    firstWord = this.firstWord
)

fun TdApi.AuthenticationCodeTypeSmsWord.toDomain(): AuthenticationCodeTypeSmsWord = AuthenticationCodeTypeSmsWord(
    firstLetter = this.firstLetter
)

fun TdApi.AuthenticationCodeTypeTelegramMessage.toDomain(): AuthenticationCodeTypeTelegramMessage = AuthenticationCodeTypeTelegramMessage(
    length = this.length
)

