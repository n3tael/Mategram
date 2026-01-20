package com.xxcactussell.domain

data class ToggleSupergroupIsAllHistoryAvailable(
    val supergroupId: Long,
    val isAllHistoryAvailable: Boolean
) : Function
