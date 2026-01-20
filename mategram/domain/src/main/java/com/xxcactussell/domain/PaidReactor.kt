package com.xxcactussell.domain

data class PaidReactor(
    val senderId: MessageSender? = null,
    val starCount: Int,
    val isTop: Boolean,
    val isMe: Boolean,
    val isAnonymous: Boolean
) : Object
