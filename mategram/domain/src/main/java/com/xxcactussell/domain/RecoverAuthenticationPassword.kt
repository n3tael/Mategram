package com.xxcactussell.domain

data class RecoverAuthenticationPassword(
    val recoveryCode: String,
    val newPassword: String,
    val newHint: String
) : Function
