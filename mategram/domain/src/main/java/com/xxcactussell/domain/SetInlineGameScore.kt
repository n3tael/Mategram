package com.xxcactussell.domain

data class SetInlineGameScore(
    val inlineMessageId: String,
    val editMessage: Boolean,
    val userId: Long,
    val score: Int,
    val force: Boolean
) : Function
