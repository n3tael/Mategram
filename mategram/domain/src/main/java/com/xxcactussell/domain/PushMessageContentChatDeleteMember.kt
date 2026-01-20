package com.xxcactussell.domain

data class PushMessageContentChatDeleteMember(
    val memberName: String,
    val isCurrentUser: Boolean,
    val isLeft: Boolean
) : PushMessageContent
