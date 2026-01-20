package com.xxcactussell.domain

data class SendCallSignalingData(
    val callId: Int,
    val data: ByteArray
) : Function
