package com.xxcactussell.domain

data class MessageSendingStateFailed(
    val error: Error,
    val canRetry: Boolean,
    val needAnotherSender: Boolean,
    val needAnotherReplyQuote: Boolean,
    val needDropReply: Boolean,
    val requiredPaidMessageStarCount: Long,
    val retryAfter: Double
) : MessageSendingState
