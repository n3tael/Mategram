package com.xxcactussell.domain

data class Proxy(
    val id: Int,
    val server: String,
    val port: Int,
    val lastUsedDate: Int,
    val isEnabled: Boolean,
    val type: ProxyType
) : Object
