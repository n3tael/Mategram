package com.xxcactussell.domain

data class AuthenticationCodeTypeFragment(
    val url: String,
    val length: Int
) : AuthenticationCodeType
