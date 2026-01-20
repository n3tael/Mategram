package com.xxcactussell.domain

data class MessageBotWriteAccessAllowed(
    val reason: BotWriteAccessAllowReason
) : MessageContent
