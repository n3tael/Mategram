package com.xxcactussell.domain

data class CanSendMessageToUserResultUserHasPaidMessages(
    val outgoingPaidMessageStarCount: Long
) : CanSendMessageToUserResult
