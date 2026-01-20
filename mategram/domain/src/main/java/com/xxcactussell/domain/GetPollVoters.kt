package com.xxcactussell.domain

data class GetPollVoters(
    val chatId: Long,
    val messageId: Long,
    val optionId: Int,
    val offset: Int,
    val limit: Int
) : Function
