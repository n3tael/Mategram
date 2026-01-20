package com.xxcactussell.data.utils.mappers.join

import com.xxcactussell.data.utils.mappers.calls.toData
import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun JoinVideoChat.toData(): TdApi.JoinVideoChat = TdApi.JoinVideoChat(
    this.groupCallId,
    this.participantId.toData(),
    this.joinParameters.toData(),
    this.inviteHash
)

