package com.xxcactussell.domain

data class UpdatePollAnswer(
    val pollId: Long,
    val voterId: MessageSender,
    val optionIds: IntArray
) : Update
