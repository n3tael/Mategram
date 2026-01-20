package com.xxcactussell.domain

data class ToggleDownloadIsPaused(
    val fileId: Int,
    val isPaused: Boolean
) : Function
