package com.xxcactussell.domain

data class LoginUrlInfoOpen(
    val url: String,
    val skipConfirmation: Boolean
) : LoginUrlInfo
