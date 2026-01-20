package com.xxcactussell.data.utils.mappers.ban

import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun BanChatMember.toData(): TdApi.BanChatMember = TdApi.BanChatMember(
    this.chatId,
    this.memberId.toData(),
    this.bannedUntilDate,
    this.revokeMessages
)

fun BanGroupCallParticipants.toData(): TdApi.BanGroupCallParticipants = TdApi.BanGroupCallParticipants(
    this.groupCallId,
    this.userIds
)

