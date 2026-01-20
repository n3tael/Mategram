package com.xxcactussell.domain

data class CreateCall(
    val userId: Long,
    val protocol: CallProtocol,
    val isVideo: Boolean
) : Function
