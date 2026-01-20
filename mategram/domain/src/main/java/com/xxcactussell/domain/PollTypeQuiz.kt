package com.xxcactussell.domain

data class PollTypeQuiz(
    val correctOptionId: Int,
    val explanation: FormattedText
) : PollType
