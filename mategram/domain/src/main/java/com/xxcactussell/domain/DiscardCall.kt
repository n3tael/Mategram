package com.xxcactussell.domain

data class DiscardCall(
    val callId: Int,
    val isDisconnected: Boolean,
    val inviteLink: String,
    val duration: Int,
    val isVideo: Boolean,
    val connectionId: Long
) : Function
