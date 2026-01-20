package com.xxcactussell.domain

data class PreparedInlineMessage(
    val inlineQueryId: Long,
    val result: InlineQueryResult,
    val chatTypes: TargetChatTypes
) : Object
