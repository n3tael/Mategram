package com.xxcactussell.domain

data class AuthenticationCodeInfo(
    val phoneNumber: String,
    val type: AuthenticationCodeType,
    val nextType: AuthenticationCodeType? = null,
    val timeout: Int
) : Object
