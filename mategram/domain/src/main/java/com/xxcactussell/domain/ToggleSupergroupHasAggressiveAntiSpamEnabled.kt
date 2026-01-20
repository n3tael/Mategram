package com.xxcactussell.domain

data class ToggleSupergroupHasAggressiveAntiSpamEnabled(
    val supergroupId: Long,
    val hasAggressiveAntiSpamEnabled: Boolean
) : Function
