package com.xxcactussell.domain

data class GetGroupsInCommon(
    val userId: Long,
    val offsetChatId: Long,
    val limit: Int
) : Function
