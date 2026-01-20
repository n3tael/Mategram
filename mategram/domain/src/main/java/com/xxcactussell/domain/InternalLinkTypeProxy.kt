package com.xxcactussell.domain

data class InternalLinkTypeProxy(
    val server: String,
    val port: Int,
    val type: ProxyType
) : InternalLinkType
