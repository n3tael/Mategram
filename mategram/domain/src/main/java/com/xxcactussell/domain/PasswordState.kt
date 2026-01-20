package com.xxcactussell.domain

data class PasswordState(
    val hasPassword: Boolean,
    val passwordHint: String,
    val hasRecoveryEmailAddress: Boolean,
    val hasPassportData: Boolean,
    val recoveryEmailAddressCodeInfo: EmailAddressAuthenticationCodeInfo? = null,
    val loginEmailAddressPattern: String,
    val pendingResetDate: Int
) : Object
