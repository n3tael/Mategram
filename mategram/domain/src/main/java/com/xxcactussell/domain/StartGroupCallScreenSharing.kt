package com.xxcactussell.domain

data class StartGroupCallScreenSharing(
    val groupCallId: Int,
    val audioSourceId: Int,
    val payload: String
) : Function
