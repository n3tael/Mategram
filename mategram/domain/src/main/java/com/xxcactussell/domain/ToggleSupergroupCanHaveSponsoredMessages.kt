package com.xxcactussell.domain

data class ToggleSupergroupCanHaveSponsoredMessages(
    val supergroupId: Long,
    val canHaveSponsoredMessages: Boolean
) : Function
