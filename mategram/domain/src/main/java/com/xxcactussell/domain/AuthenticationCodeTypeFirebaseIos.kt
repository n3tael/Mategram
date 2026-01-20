package com.xxcactussell.domain

data class AuthenticationCodeTypeFirebaseIos(
    val receipt: String,
    val pushTimeout: Int,
    val length: Int
) : AuthenticationCodeType
