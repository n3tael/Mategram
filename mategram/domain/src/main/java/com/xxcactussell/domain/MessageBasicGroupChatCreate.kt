package com.xxcactussell.domain

data class MessageBasicGroupChatCreate(
    val title: String,
    val memberUserIds: LongArray
) : MessageContent
