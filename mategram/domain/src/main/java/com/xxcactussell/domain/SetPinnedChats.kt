package com.xxcactussell.domain

data class SetPinnedChats(
    val chatList: ChatList,
    val chatIds: LongArray
) : Function
