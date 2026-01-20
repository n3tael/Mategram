package com.xxcactussell.domain

data class ToggleGroupCallIsMyVideoEnabled(
    val groupCallId: Int,
    val isMyVideoEnabled: Boolean
) : Function
