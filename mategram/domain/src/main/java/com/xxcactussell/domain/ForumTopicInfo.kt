package com.xxcactussell.domain

data class ForumTopicInfo(
    val chatId: Long,
    val forumTopicId: Long,
    val messageThreadId: Long,
    val name: String,
    val icon: ForumTopicIcon,
    val creationDate: Int,
    val creatorId: MessageSender,
    val isGeneral: Boolean,
    val isOutgoing: Boolean,
    val isClosed: Boolean,
    val isHidden: Boolean
) : Object
