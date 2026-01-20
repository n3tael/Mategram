package com.xxcactussell.domain

data class MessageChatAddMembers(
    val memberUserIds: LongArray
) : MessageContent
