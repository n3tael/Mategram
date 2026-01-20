package com.xxcactussell.domain

data class SetPassword(
    val oldPassword: String,
    val newPassword: String,
    val newHint: String,
    val setRecoveryEmailAddress: Boolean,
    val newRecoveryEmailAddress: String
) : Function
