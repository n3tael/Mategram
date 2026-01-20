package com.xxcactussell.domain

data class UpdateUnreadMessageCount(
    val chatList: ChatList,
    val unreadCount: Int,
    val unreadUnmutedCount: Int
) : Update
