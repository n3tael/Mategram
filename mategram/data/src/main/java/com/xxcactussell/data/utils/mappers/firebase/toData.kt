package com.xxcactussell.data.utils.mappers.firebase

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun FirebaseAuthenticationSettings.toData(): TdApi.FirebaseAuthenticationSettings = when(this) {
    is FirebaseAuthenticationSettingsAndroid -> this.toData()
    is FirebaseAuthenticationSettingsIos -> this.toData()
}

fun FirebaseAuthenticationSettingsAndroid.toData(): TdApi.FirebaseAuthenticationSettingsAndroid = TdApi.FirebaseAuthenticationSettingsAndroid(
)

fun FirebaseAuthenticationSettingsIos.toData(): TdApi.FirebaseAuthenticationSettingsIos = TdApi.FirebaseAuthenticationSettingsIos(
    this.deviceToken,
    this.isAppSandbox
)

fun FirebaseDeviceVerificationParameters.toData(): TdApi.FirebaseDeviceVerificationParameters = when(this) {
    is FirebaseDeviceVerificationParametersSafetyNet -> this.toData()
    is FirebaseDeviceVerificationParametersPlayIntegrity -> this.toData()
}

fun FirebaseDeviceVerificationParametersPlayIntegrity.toData(): TdApi.FirebaseDeviceVerificationParametersPlayIntegrity = TdApi.FirebaseDeviceVerificationParametersPlayIntegrity(
    this.nonce,
    this.cloudProjectNumber
)

fun FirebaseDeviceVerificationParametersSafetyNet.toData(): TdApi.FirebaseDeviceVerificationParametersSafetyNet = TdApi.FirebaseDeviceVerificationParametersSafetyNet(
    this.nonce
)

