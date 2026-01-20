package com.xxcactussell.domain

data class ToggleSupergroupJoinToSendMessages(
    val supergroupId: Long,
    val joinToSendMessages: Boolean
) : Function
