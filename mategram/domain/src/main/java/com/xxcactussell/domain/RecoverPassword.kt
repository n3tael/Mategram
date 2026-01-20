package com.xxcactussell.domain

data class RecoverPassword(
    val recoveryCode: String,
    val newPassword: String,
    val newHint: String
) : Function
