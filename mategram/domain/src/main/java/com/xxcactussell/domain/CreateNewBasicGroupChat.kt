package com.xxcactussell.domain

data class CreateNewBasicGroupChat(
    val userIds: LongArray,
    val title: String,
    val messageAutoDeleteTime: Int
) : Function
