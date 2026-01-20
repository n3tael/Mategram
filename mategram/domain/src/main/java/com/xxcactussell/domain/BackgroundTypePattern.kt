package com.xxcactussell.domain

data class BackgroundTypePattern(
    val fill: BackgroundFill,
    val intensity: Int,
    val isInverted: Boolean,
    val isMoving: Boolean
) : BackgroundType
