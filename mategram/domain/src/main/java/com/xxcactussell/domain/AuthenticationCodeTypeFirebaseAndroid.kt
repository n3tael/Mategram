package com.xxcactussell.domain

data class AuthenticationCodeTypeFirebaseAndroid(
    val deviceVerificationParameters: FirebaseDeviceVerificationParameters,
    val length: Int
) : AuthenticationCodeType
