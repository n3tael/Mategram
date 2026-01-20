package com.xxcactussell.domain

data class EditProxy(
    val proxyId: Int,
    val server: String,
    val port: Int,
    val enable: Boolean,
    val type: ProxyType
) : Function
