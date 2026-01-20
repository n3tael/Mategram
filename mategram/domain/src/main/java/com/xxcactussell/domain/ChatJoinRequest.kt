package com.xxcactussell.domain

data class ChatJoinRequest(
    val userId: Long,
    val date: Int,
    val bio: String
) : Object
