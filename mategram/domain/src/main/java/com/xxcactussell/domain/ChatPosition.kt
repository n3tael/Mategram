package com.xxcactussell.domain

data class ChatPosition(
    val list: ChatList,
    val order: Long,
    val isPinned: Boolean,
    val source: ChatSource? = null
) : Object
