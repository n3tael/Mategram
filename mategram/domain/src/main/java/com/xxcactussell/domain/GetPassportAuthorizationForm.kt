package com.xxcactussell.domain

data class GetPassportAuthorizationForm(
    val botUserId: Long,
    val scope: String,
    val publicKey: String,
    val nonce: String
) : Function
