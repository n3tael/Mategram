package com.xxcactussell.domain

data class SetGameScore(
    val chatId: Long,
    val messageId: Long,
    val editMessage: Boolean,
    val userId: Long,
    val score: Int,
    val force: Boolean
) : Function
