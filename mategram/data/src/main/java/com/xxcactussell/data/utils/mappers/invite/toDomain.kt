package com.xxcactussell.data.utils.mappers.invite

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.InviteGroupCallParticipant.toDomain(): InviteGroupCallParticipant = InviteGroupCallParticipant(
    groupCallId = this.groupCallId,
    userId = this.userId,
    isVideo = this.isVideo
)

fun TdApi.InviteGroupCallParticipantResult.toDomain(): InviteGroupCallParticipantResult = when(this) {
    is TdApi.InviteGroupCallParticipantResultUserPrivacyRestricted -> this.toDomain()
    is TdApi.InviteGroupCallParticipantResultUserAlreadyParticipant -> this.toDomain()
    is TdApi.InviteGroupCallParticipantResultUserWasBanned -> this.toDomain()
    is TdApi.InviteGroupCallParticipantResultSuccess -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InviteGroupCallParticipantResultSuccess.toDomain(): InviteGroupCallParticipantResultSuccess = InviteGroupCallParticipantResultSuccess(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.InviteGroupCallParticipantResultUserAlreadyParticipant.toDomain(): InviteGroupCallParticipantResultUserAlreadyParticipant = InviteGroupCallParticipantResultUserAlreadyParticipant

fun TdApi.InviteGroupCallParticipantResultUserPrivacyRestricted.toDomain(): InviteGroupCallParticipantResultUserPrivacyRestricted = InviteGroupCallParticipantResultUserPrivacyRestricted

fun TdApi.InviteGroupCallParticipantResultUserWasBanned.toDomain(): InviteGroupCallParticipantResultUserWasBanned = InviteGroupCallParticipantResultUserWasBanned

fun TdApi.InviteLinkChatType.toDomain(): InviteLinkChatType = when(this) {
    is TdApi.InviteLinkChatTypeBasicGroup -> this.toDomain()
    is TdApi.InviteLinkChatTypeSupergroup -> this.toDomain()
    is TdApi.InviteLinkChatTypeChannel -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InviteLinkChatTypeBasicGroup.toDomain(): InviteLinkChatTypeBasicGroup = InviteLinkChatTypeBasicGroup

fun TdApi.InviteLinkChatTypeChannel.toDomain(): InviteLinkChatTypeChannel = InviteLinkChatTypeChannel

fun TdApi.InviteLinkChatTypeSupergroup.toDomain(): InviteLinkChatTypeSupergroup = InviteLinkChatTypeSupergroup

fun TdApi.InviteVideoChatParticipants.toDomain(): InviteVideoChatParticipants = InviteVideoChatParticipants(
    groupCallId = this.groupCallId,
    userIds = this.userIds
)

