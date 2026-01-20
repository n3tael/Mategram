package com.xxcactussell.data.utils.mappers.join

import com.xxcactussell.data.utils.mappers.calls.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.JoinVideoChat.toDomain(): JoinVideoChat = JoinVideoChat(
    groupCallId = this.groupCallId,
    participantId = this.participantId.toDomain(),
    joinParameters = this.joinParameters.toDomain(),
    inviteHash = this.inviteHash
)

