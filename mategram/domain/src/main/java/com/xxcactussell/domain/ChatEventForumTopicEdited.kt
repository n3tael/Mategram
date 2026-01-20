package com.xxcactussell.domain

data class ChatEventForumTopicEdited(
    val oldTopicInfo: ForumTopicInfo,
    val newTopicInfo: ForumTopicInfo
) : ChatEventAction
