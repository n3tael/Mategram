package com.xxcactussell.domain

data class ChatEventForumTopicDeleted(
    val topicInfo: ForumTopicInfo
) : ChatEventAction
