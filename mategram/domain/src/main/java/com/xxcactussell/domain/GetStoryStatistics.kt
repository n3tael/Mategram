package com.xxcactussell.domain

data class GetStoryStatistics(
    val chatId: Long,
    val storyId: Int,
    val isDark: Boolean
) : Function
