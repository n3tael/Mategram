package com.xxcactussell.domain

data class GetGameHighScores(
    val chatId: Long,
    val messageId: Long,
    val userId: Long
) : Function
