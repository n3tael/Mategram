package com.xxcactussell.domain

data class GetMessageLink(
    val chatId: Long,
    val messageId: Long,
    val mediaTimestamp: Int,
    val forAlbum: Boolean,
    val inMessageThread: Boolean
) : Function
