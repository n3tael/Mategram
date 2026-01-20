package com.xxcactussell.data.utils.mappers.ban

import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.BanChatMember.toDomain(): BanChatMember = BanChatMember(
    chatId = this.chatId,
    memberId = this.memberId.toDomain(),
    bannedUntilDate = this.bannedUntilDate,
    revokeMessages = this.revokeMessages
)

fun TdApi.BanGroupCallParticipants.toDomain(): BanGroupCallParticipants = BanGroupCallParticipants(
    groupCallId = this.groupCallId,
    userIds = this.userIds
)

