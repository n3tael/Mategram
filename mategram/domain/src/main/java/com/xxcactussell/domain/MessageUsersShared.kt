package com.xxcactussell.domain

data class MessageUsersShared(
    val users: List<SharedUser>,
    val buttonId: Int
) : MessageContent
