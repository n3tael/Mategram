package com.xxcactussell.domain

data class ToggleSessionCanAcceptCalls(
    val sessionId: Long,
    val canAcceptCalls: Boolean
) : Function
