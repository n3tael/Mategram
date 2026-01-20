package com.xxcactussell.domain

data class EmailAddressAuthenticationCode(
    val code: String
) : EmailAddressAuthentication
