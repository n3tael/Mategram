package com.xxcactussell.data.utils.mappers.revoke

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.RevokeChatInviteLink.toDomain(): RevokeChatInviteLink = RevokeChatInviteLink(
    chatId = this.chatId,
    inviteLink = this.inviteLink
)

fun TdApi.RevokeGroupCallInviteLink.toDomain(): RevokeGroupCallInviteLink = RevokeGroupCallInviteLink(
    groupCallId = this.groupCallId
)

