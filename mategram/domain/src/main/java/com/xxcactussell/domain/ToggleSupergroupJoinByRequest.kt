package com.xxcactussell.domain

data class ToggleSupergroupJoinByRequest(
    val supergroupId: Long,
    val joinByRequest: Boolean
) : Function
