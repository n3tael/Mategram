package com.xxcactussell.domain

data class MessageChatSetBackground(
    val oldBackgroundMessageId: Long,
    val background: ChatBackground,
    val onlyForSelf: Boolean
) : MessageContent
