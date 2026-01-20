package com.xxcactussell.domain

data class ToggleSavedMessagesTopicIsPinned(
    val savedMessagesTopicId: Long,
    val isPinned: Boolean
) : Function
