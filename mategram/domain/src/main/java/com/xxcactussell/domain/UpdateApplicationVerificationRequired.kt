package com.xxcactussell.domain

data class UpdateApplicationVerificationRequired(
    val verificationId: Long,
    val nonce: String,
    val cloudProjectNumber: Long
) : Update
