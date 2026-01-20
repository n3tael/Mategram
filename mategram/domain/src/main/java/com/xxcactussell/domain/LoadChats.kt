package com.xxcactussell.domain

data class LoadChats(
    val chatList: ChatList,
    val limit: Int
) : Function
