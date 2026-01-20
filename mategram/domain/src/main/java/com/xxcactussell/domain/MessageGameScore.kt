package com.xxcactussell.domain

data class MessageGameScore(
    val gameMessageId: Long,
    val gameId: Long,
    val score: Int
) : MessageContent
