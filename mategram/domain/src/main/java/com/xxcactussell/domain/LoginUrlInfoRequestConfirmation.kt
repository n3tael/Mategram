package com.xxcactussell.domain

data class LoginUrlInfoRequestConfirmation(
    val url: String,
    val domain: String,
    val botUserId: Long,
    val requestWriteAccess: Boolean
) : LoginUrlInfo
