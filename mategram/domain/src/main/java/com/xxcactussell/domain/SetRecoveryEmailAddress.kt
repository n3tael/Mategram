package com.xxcactussell.domain

data class SetRecoveryEmailAddress(
    val password: String,
    val newRecoveryEmailAddress: String
) : Function
