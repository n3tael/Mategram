package com.xxcactussell.domain

data class GetPreparedInlineMessage(
    val botUserId: Long,
    val preparedMessageId: String
) : Function
