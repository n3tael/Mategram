package com.xxcactussell.domain

data class SetApplicationVerificationToken(
    val verificationId: Long,
    val token: String
) : Function
