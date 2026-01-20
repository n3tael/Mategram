package com.xxcactussell.domain

data class AuthenticationCodeTypeSmsWord(
    val firstLetter: String
) : AuthenticationCodeType
