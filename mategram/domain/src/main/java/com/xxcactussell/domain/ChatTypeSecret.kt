package com.xxcactussell.domain

data class ChatTypeSecret(
    val secretChatId: Int,
    val userId: Long
) : ChatType
