package com.xxcactussell.domain

data class CallServer(
    val id: Long,
    val ipAddress: String,
    val ipv6Address: String,
    val port: Int,
    val type: CallServerType
) : Object
