package com.xxcactussell.domain

data class AddProxy(
    val server: String,
    val port: Int,
    val enable: Boolean,
    val type: ProxyType
) : Function
