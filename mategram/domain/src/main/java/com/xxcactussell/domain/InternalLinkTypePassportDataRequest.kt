package com.xxcactussell.domain

data class InternalLinkTypePassportDataRequest(
    val botUserId: Long,
    val scope: String,
    val publicKey: String,
    val nonce: String,
    val callbackUrl: String
) : InternalLinkType
