package com.xxcactussell.domain

data class SetPollAnswer(
    val chatId: Long,
    val messageId: Long,
    val optionIds: IntArray
) : Function
