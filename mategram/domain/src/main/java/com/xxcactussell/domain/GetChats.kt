package com.xxcactussell.domain

data class GetChats(
    val chatList: ChatList,
    val limit: Int
) : Function
