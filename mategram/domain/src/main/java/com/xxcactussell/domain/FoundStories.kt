package com.xxcactussell.domain

data class FoundStories(
    val totalCount: Int,
    val stories: List<Story>,
    val nextOffset: String
) : Object
