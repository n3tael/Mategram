package com.xxcactussell.utils

object PerformanceProfile {
    val maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024)
    val cores = Runtime.getRuntime().availableProcessors()

    val isHighEnd = maxMemory >= 512 && cores >= 8
    val isLowEnd = maxMemory <= 256 || cores <= 4

    val targetFps = if (isHighEnd) 60 else 30
    val minFrameDelay = (1000L / targetFps)

    fun getResolutionScale(isEmoji: Boolean): Float {
        return when {
            isHighEnd -> if (isEmoji) 0.7f else 1.0f
            else -> if (isEmoji) 0.5f else 0.7f
        }
    }
}