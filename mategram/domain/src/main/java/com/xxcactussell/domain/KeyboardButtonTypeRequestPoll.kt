package com.xxcactussell.domain

data class KeyboardButtonTypeRequestPoll(
    val forceRegular: Boolean,
    val forceQuiz: Boolean
) : KeyboardButtonType
