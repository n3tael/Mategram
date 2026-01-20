package com.xxcactussell.domain

data class ShareUsersWithBot(
    val chatId: Long,
    val messageId: Long,
    val buttonId: Int,
    val sharedUserIds: LongArray,
    val onlyCheck: Boolean
) : Function
