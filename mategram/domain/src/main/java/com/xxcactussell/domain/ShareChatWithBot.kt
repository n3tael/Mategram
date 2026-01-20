package com.xxcactussell.domain

data class ShareChatWithBot(
    val chatId: Long,
    val messageId: Long,
    val buttonId: Int,
    val sharedChatId: Long,
    val onlyCheck: Boolean
) : Function
