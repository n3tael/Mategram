package com.xxcactussell.domain

data class PhoneNumberAuthenticationSettings(
    val allowFlashCall: Boolean,
    val allowMissedCall: Boolean,
    val isCurrentPhoneNumber: Boolean,
    val hasUnknownPhoneNumber: Boolean,
    val allowSmsRetrieverApi: Boolean,
    val firebaseAuthenticationSettings: FirebaseAuthenticationSettings,
    val authenticationTokens: List<String>
) : Object
