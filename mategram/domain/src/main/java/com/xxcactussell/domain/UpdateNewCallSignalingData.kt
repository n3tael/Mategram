package com.xxcactussell.domain

data class UpdateNewCallSignalingData(
    val callId: Int,
    val data: ByteArray
) : Update
