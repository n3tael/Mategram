package com.xxcactussell.domain

data class ToggleSessionCanAcceptSecretChats(
    val sessionId: Long,
    val canAcceptSecretChats: Boolean
) : Function
