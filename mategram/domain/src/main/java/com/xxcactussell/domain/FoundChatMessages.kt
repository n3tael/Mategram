package com.xxcactussell.domain

data class FoundChatMessages(
    val totalCount: Int,
    val messages: List<Message>,
    val nextFromMessageId: Long
) : Object
