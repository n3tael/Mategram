package com.xxcactussell.domain

data class MessageDice(
    val initialState: DiceStickers? = null,
    val finalState: DiceStickers? = null,
    val emoji: String,
    val value: Int,
    val successAnimationFrameNumber: Int
) : MessageContent
