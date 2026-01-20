package com.xxcactussell.domain

data class MessageChatShared(
    val chat: SharedChat,
    val buttonId: Int
) : MessageContent
