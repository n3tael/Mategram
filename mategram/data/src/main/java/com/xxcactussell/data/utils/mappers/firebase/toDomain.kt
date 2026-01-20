package com.xxcactussell.data.utils.mappers.firebase

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.FirebaseAuthenticationSettings.toDomain(): FirebaseAuthenticationSettings = when(this) {
    is TdApi.FirebaseAuthenticationSettingsAndroid -> this.toDomain()
    is TdApi.FirebaseAuthenticationSettingsIos -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.FirebaseAuthenticationSettingsAndroid.toDomain(): FirebaseAuthenticationSettingsAndroid = FirebaseAuthenticationSettingsAndroid

fun TdApi.FirebaseAuthenticationSettingsIos.toDomain(): FirebaseAuthenticationSettingsIos = FirebaseAuthenticationSettingsIos(
    deviceToken = this.deviceToken,
    isAppSandbox = this.isAppSandbox
)

fun TdApi.FirebaseDeviceVerificationParameters.toDomain(): FirebaseDeviceVerificationParameters = when(this) {
    is TdApi.FirebaseDeviceVerificationParametersSafetyNet -> this.toDomain()
    is TdApi.FirebaseDeviceVerificationParametersPlayIntegrity -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.FirebaseDeviceVerificationParametersPlayIntegrity.toDomain(): FirebaseDeviceVerificationParametersPlayIntegrity = FirebaseDeviceVerificationParametersPlayIntegrity(
    nonce = this.nonce,
    cloudProjectNumber = this.cloudProjectNumber
)

fun TdApi.FirebaseDeviceVerificationParametersSafetyNet.toDomain(): FirebaseDeviceVerificationParametersSafetyNet = FirebaseDeviceVerificationParametersSafetyNet(
    nonce = this.nonce
)

