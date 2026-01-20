package com.xxcactussell.domain

data class PushMessageContentChatAddMembers(
    val memberName: String,
    val isCurrentUser: Boolean,
    val isReturned: Boolean
) : PushMessageContent
