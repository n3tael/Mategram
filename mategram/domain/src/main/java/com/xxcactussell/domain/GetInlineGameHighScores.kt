package com.xxcactussell.domain

data class GetInlineGameHighScores(
    val inlineMessageId: String,
    val userId: Long
) : Function
