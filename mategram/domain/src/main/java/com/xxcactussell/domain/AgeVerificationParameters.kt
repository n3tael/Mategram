package com.xxcactussell.domain

data class AgeVerificationParameters(
    val minAge: Int,
    val verificationBotUsername: String,
    val country: String
) : Object
