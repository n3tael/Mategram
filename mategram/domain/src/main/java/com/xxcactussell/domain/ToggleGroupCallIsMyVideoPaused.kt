package com.xxcactussell.domain

data class ToggleGroupCallIsMyVideoPaused(
    val groupCallId: Int,
    val isMyVideoPaused: Boolean
) : Function
