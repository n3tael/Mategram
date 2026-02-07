package com.xxcactussell.customdomain

data class ForwardFullInfo(
    val isHidden: Boolean,
    val chat: String? = null,
    val signature: String? = null,
    val link: ForwardInfoLink? = null
)

data class ForwardInfoLink(
    val chatId: Long? = null,
    val messageId: Long? = null
)