package com.xxcactussell.domain

data class ChatEventHasAggressiveAntiSpamEnabledToggled(
    val hasAggressiveAntiSpamEnabled: Boolean
) : ChatEventAction
