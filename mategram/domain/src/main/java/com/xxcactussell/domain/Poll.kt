package com.xxcactussell.domain

data class Poll(
    val id: Long,
    val question: FormattedText,
    val options: List<PollOption>,
    val totalVoterCount: Int,
    val recentVoterIds: List<MessageSender>,
    val isAnonymous: Boolean,
    val type: PollType,
    val openPeriod: Int,
    val closeDate: Int,
    val isClosed: Boolean
) : Object
