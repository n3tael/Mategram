package com.xxcactussell.domain

data class ChatEventForumTopicCreated(
    val topicInfo: ForumTopicInfo
) : ChatEventAction
