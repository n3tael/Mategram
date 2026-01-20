package com.xxcactussell.domain

data class ToggleSupergroupSignMessages(
    val supergroupId: Long,
    val signMessages: Boolean,
    val showMessageSender: Boolean
) : Function
