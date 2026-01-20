package com.xxcactussell.domain

data class InputMessageDice(
    val emoji: String,
    val clearDraft: Boolean
) : InputMessageContent
