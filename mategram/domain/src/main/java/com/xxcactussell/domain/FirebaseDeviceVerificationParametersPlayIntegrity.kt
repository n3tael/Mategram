package com.xxcactussell.domain

data class FirebaseDeviceVerificationParametersPlayIntegrity(
    val nonce: String,
    val cloudProjectNumber: Long
) : FirebaseDeviceVerificationParameters
