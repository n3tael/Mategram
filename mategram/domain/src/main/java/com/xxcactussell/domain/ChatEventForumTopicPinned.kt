package com.xxcactussell.domain

data class ChatEventForumTopicPinned(
    val oldTopicInfo: ForumTopicInfo? = null,
    val newTopicInfo: ForumTopicInfo? = null
) : ChatEventAction
