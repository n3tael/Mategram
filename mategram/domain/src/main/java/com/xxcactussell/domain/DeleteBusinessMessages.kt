package com.xxcactussell.domain

data class DeleteBusinessMessages(
    val businessConnectionId: String,
    val messageIds: LongArray
) : Function
