package com.xxcactussell.domain

data class SearchSavedMessages(
    val savedMessagesTopicId: Long,
    val tag: ReactionType,
    val query: String,
    val fromMessageId: Long,
    val offset: Int,
    val limit: Int
) : Function
