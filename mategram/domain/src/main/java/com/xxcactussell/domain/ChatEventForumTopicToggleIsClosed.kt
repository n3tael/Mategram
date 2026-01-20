package com.xxcactussell.domain

data class ChatEventForumTopicToggleIsClosed(
    val topicInfo: ForumTopicInfo
) : ChatEventAction
