package com.xxcactussell.domain

data class UpdateChatActionBar(
    val chatId: Long,
    val actionBar: ChatActionBar? = null
) : Update
