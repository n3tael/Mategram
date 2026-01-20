package com.xxcactussell.domain

data class Stories(
    val totalCount: Int,
    val stories: List<Story>,
    val pinnedStoryIds: IntArray
) : Object
