package com.xxcactussell.data.utils.mappers.authentication

import com.xxcactussell.data.utils.mappers.firebase.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AuthenticationCodeInfo.toData(): TdApi.AuthenticationCodeInfo = TdApi.AuthenticationCodeInfo(
    this.phoneNumber,
    this.type.toData(),
    this.nextType?.toData(),
    this.timeout
)

fun AuthenticationCodeType.toData(): TdApi.AuthenticationCodeType = when(this) {
    is AuthenticationCodeTypeTelegramMessage -> this.toData()
    is AuthenticationCodeTypeSms -> this.toData()
    is AuthenticationCodeTypeSmsWord -> this.toData()
    is AuthenticationCodeTypeSmsPhrase -> this.toData()
    is AuthenticationCodeTypeCall -> this.toData()
    is AuthenticationCodeTypeFlashCall -> this.toData()
    is AuthenticationCodeTypeMissedCall -> this.toData()
    is AuthenticationCodeTypeFragment -> this.toData()
    is AuthenticationCodeTypeFirebaseAndroid -> this.toData()
    is AuthenticationCodeTypeFirebaseIos -> this.toData()
}

fun AuthenticationCodeTypeCall.toData(): TdApi.AuthenticationCodeTypeCall = TdApi.AuthenticationCodeTypeCall(
    this.length
)

fun AuthenticationCodeTypeFirebaseAndroid.toData(): TdApi.AuthenticationCodeTypeFirebaseAndroid = TdApi.AuthenticationCodeTypeFirebaseAndroid(
    this.deviceVerificationParameters.toData(),
    this.length
)

fun AuthenticationCodeTypeFirebaseIos.toData(): TdApi.AuthenticationCodeTypeFirebaseIos = TdApi.AuthenticationCodeTypeFirebaseIos(
    this.receipt,
    this.pushTimeout,
    this.length
)

fun AuthenticationCodeTypeFlashCall.toData(): TdApi.AuthenticationCodeTypeFlashCall = TdApi.AuthenticationCodeTypeFlashCall(
    this.pattern
)

fun AuthenticationCodeTypeFragment.toData(): TdApi.AuthenticationCodeTypeFragment = TdApi.AuthenticationCodeTypeFragment(
    this.url,
    this.length
)

fun AuthenticationCodeTypeMissedCall.toData(): TdApi.AuthenticationCodeTypeMissedCall = TdApi.AuthenticationCodeTypeMissedCall(
    this.phoneNumberPrefix,
    this.length
)

fun AuthenticationCodeTypeSms.toData(): TdApi.AuthenticationCodeTypeSms = TdApi.AuthenticationCodeTypeSms(
    this.length
)

fun AuthenticationCodeTypeSmsPhrase.toData(): TdApi.AuthenticationCodeTypeSmsPhrase = TdApi.AuthenticationCodeTypeSmsPhrase(
    this.firstWord
)

fun AuthenticationCodeTypeSmsWord.toData(): TdApi.AuthenticationCodeTypeSmsWord = TdApi.AuthenticationCodeTypeSmsWord(
    this.firstLetter
)

fun AuthenticationCodeTypeTelegramMessage.toData(): TdApi.AuthenticationCodeTypeTelegramMessage = TdApi.AuthenticationCodeTypeTelegramMessage(
    this.length
)

