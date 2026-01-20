package com.xxcactussell.domain

data class InputMessagePoll(
    val question: FormattedText,
    val options: List<FormattedText>,
    val isAnonymous: Boolean,
    val type: PollType,
    val openPeriod: Int,
    val closeDate: Int,
    val isClosed: Boolean
) : InputMessageContent
