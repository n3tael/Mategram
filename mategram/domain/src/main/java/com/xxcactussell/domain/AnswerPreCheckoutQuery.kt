package com.xxcactussell.domain

data class AnswerPreCheckoutQuery(
    val preCheckoutQueryId: Long,
    val errorMessage: String
) : Function
