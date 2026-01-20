package com.xxcactussell.domain

data class SavedMessagesTopic(
    val id: Long,
    val type: SavedMessagesTopicType,
    val isPinned: Boolean,
    val order: Long,
    val lastMessage: Message? = null,
    val draftMessage: DraftMessage? = null
) : Object
