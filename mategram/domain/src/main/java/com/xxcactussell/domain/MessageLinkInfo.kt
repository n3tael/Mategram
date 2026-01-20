package com.xxcactussell.domain

data class MessageLinkInfo(
    val isPublic: Boolean,
    val chatId: Long,
    val messageThreadId: Long,
    val message: Message? = null,
    val mediaTimestamp: Int,
    val forAlbum: Boolean
) : Object
