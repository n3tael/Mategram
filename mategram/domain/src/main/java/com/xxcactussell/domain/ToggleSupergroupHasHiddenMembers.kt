package com.xxcactussell.domain

data class ToggleSupergroupHasHiddenMembers(
    val supergroupId: Long,
    val hasHiddenMembers: Boolean
) : Function
