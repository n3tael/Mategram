package com.xxcactussell.data.utils.mappers.revoke

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RevokeChatInviteLink.toData(): TdApi.RevokeChatInviteLink = TdApi.RevokeChatInviteLink(
    this.chatId,
    this.inviteLink
)

fun RevokeGroupCallInviteLink.toData(): TdApi.RevokeGroupCallInviteLink = TdApi.RevokeGroupCallInviteLink(
    this.groupCallId
)

