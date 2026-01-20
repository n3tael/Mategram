package com.xxcactussell.domain

data class SavePreparedInlineMessage(
    val userId: Long,
    val result: InputInlineQueryResult,
    val chatTypes: TargetChatTypes
) : Function
