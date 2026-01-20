package com.xxcactussell.domain

data class ChatEventForumTopicToggleIsHidden(
    val topicInfo: ForumTopicInfo
) : ChatEventAction
