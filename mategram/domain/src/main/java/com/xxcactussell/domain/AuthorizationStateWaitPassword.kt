package com.xxcactussell.domain

data class AuthorizationStateWaitPassword(
    val passwordHint: String,
    val hasRecoveryEmailAddress: Boolean,
    val hasPassportData: Boolean,
    val recoveryEmailAddressPattern: String
) : AuthorizationState
