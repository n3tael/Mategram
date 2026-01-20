package com.xxcactussell.domain

data class CallServerTypeTelegramReflector(
    val peerTag: ByteArray,
    val isTcp: Boolean
) : CallServerType
