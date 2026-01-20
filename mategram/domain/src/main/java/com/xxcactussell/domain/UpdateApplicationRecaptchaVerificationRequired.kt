package com.xxcactussell.domain

data class UpdateApplicationRecaptchaVerificationRequired(
    val verificationId: Long,
    val action: String,
    val recaptchaKeyId: String
) : Update
