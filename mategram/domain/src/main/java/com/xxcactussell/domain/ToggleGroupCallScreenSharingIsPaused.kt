package com.xxcactussell.domain

data class ToggleGroupCallScreenSharingIsPaused(
    val groupCallId: Int,
    val isPaused: Boolean
) : Function
