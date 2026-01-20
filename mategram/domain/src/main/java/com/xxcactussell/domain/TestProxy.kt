package com.xxcactussell.domain

data class TestProxy(
    val server: String,
    val port: Int,
    val type: ProxyType,
    val dcId: Int,
    val timeout: Double
) : Function
