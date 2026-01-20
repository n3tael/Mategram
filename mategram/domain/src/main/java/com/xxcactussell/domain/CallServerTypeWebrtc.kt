package com.xxcactussell.domain

data class CallServerTypeWebrtc(
    val username: String,
    val password: String,
    val supportsTurn: Boolean,
    val supportsStun: Boolean
) : CallServerType
