package com.xxcactussell.domain

data class PollOption(
    val text: FormattedText,
    val voterCount: Int,
    val votePercentage: Int,
    val isChosen: Boolean,
    val isBeingChosen: Boolean
) : Object
