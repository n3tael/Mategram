package com.xxcactussell.domain

data class UpdateUnreadChatCount(
    val chatList: ChatList,
    val totalCount: Int,
    val unreadCount: Int,
    val unreadUnmutedCount: Int,
    val markedAsUnreadCount: Int,
    val markedAsUnreadUnmutedCount: Int
) : Update
