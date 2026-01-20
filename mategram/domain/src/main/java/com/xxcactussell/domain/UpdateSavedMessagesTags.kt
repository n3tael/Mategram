package com.xxcactussell.domain

data class UpdateSavedMessagesTags(
    val savedMessagesTopicId: Long,
    val tags: SavedMessagesTags
) : Update
