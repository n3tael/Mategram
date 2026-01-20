package com.xxcactussell.domain

data class ProxyTypeHttp(
    val username: String,
    val password: String,
    val httpOnly: Boolean
) : ProxyType
