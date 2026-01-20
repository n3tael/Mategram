package com.xxcactussell.domain

data class CallStatePending(
    val isCreated: Boolean,
    val isReceived: Boolean
) : CallState
