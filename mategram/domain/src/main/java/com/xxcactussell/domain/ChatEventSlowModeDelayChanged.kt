package com.xxcactussell.domain

data class ChatEventSlowModeDelayChanged(
    val oldSlowModeDelay: Int,
    val newSlowModeDelay: Int
) : ChatEventAction
