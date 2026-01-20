package com.xxcactussell.domain

data class GetMessageEmbeddingCode(
    val chatId: Long,
    val messageId: Long,
    val forAlbum: Boolean
) : Function
