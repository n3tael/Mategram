package com.xxcactussell.domain

data class UpdateMessageInteractionInfo(
    val chatId: Long,
    val messageId: Long,
    val interactionInfo: MessageInteractionInfo? = null
) : Update
