package com.xxcactussell.domain

data class InputMessageGame(
    val botUserId: Long,
    val gameShortName: String
) : InputMessageContent
