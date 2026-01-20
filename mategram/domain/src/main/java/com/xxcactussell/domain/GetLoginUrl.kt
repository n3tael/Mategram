package com.xxcactussell.domain

data class GetLoginUrl(
    val chatId: Long,
    val messageId: Long,
    val buttonId: Long,
    val allowWriteAccess: Boolean
) : Function
