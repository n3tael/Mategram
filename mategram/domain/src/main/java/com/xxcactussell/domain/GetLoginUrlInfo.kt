package com.xxcactussell.domain

data class GetLoginUrlInfo(
    val chatId: Long,
    val messageId: Long,
    val buttonId: Long
) : Function
