package com.xxcactussell.domain

data class InviteGroupCallParticipantResultSuccess(
    val chatId: Long,
    val messageId: Long
) : InviteGroupCallParticipantResult
