package com.xxcactussell.domain

data class SearchPublicStoriesByTag(
    val storyPosterChatId: Long,
    val tag: String,
    val offset: String,
    val limit: Int
) : Function
