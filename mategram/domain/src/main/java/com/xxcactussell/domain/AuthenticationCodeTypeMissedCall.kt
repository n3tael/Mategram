package com.xxcactussell.domain

data class AuthenticationCodeTypeMissedCall(
    val phoneNumberPrefix: String,
    val length: Int
) : AuthenticationCodeType
