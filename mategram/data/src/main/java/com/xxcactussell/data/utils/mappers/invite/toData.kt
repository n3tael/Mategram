package com.xxcactussell.data.utils.mappers.invite

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun InviteGroupCallParticipant.toData(): TdApi.InviteGroupCallParticipant = TdApi.InviteGroupCallParticipant(
    this.groupCallId,
    this.userId,
    this.isVideo
)

fun InviteGroupCallParticipantResult.toData(): TdApi.InviteGroupCallParticipantResult = when(this) {
    is InviteGroupCallParticipantResultUserPrivacyRestricted -> this.toData()
    is InviteGroupCallParticipantResultUserAlreadyParticipant -> this.toData()
    is InviteGroupCallParticipantResultUserWasBanned -> this.toData()
    is InviteGroupCallParticipantResultSuccess -> this.toData()
}

fun InviteGroupCallParticipantResultSuccess.toData(): TdApi.InviteGroupCallParticipantResultSuccess = TdApi.InviteGroupCallParticipantResultSuccess(
    this.chatId,
    this.messageId
)

fun InviteGroupCallParticipantResultUserAlreadyParticipant.toData(): TdApi.InviteGroupCallParticipantResultUserAlreadyParticipant = TdApi.InviteGroupCallParticipantResultUserAlreadyParticipant(
)

fun InviteGroupCallParticipantResultUserPrivacyRestricted.toData(): TdApi.InviteGroupCallParticipantResultUserPrivacyRestricted = TdApi.InviteGroupCallParticipantResultUserPrivacyRestricted(
)

fun InviteGroupCallParticipantResultUserWasBanned.toData(): TdApi.InviteGroupCallParticipantResultUserWasBanned = TdApi.InviteGroupCallParticipantResultUserWasBanned(
)

fun InviteLinkChatType.toData(): TdApi.InviteLinkChatType = when(this) {
    is InviteLinkChatTypeBasicGroup -> this.toData()
    is InviteLinkChatTypeSupergroup -> this.toData()
    is InviteLinkChatTypeChannel -> this.toData()
}

fun InviteLinkChatTypeBasicGroup.toData(): TdApi.InviteLinkChatTypeBasicGroup = TdApi.InviteLinkChatTypeBasicGroup(
)

fun InviteLinkChatTypeChannel.toData(): TdApi.InviteLinkChatTypeChannel = TdApi.InviteLinkChatTypeChannel(
)

fun InviteLinkChatTypeSupergroup.toData(): TdApi.InviteLinkChatTypeSupergroup = TdApi.InviteLinkChatTypeSupergroup(
)

fun InviteVideoChatParticipants.toData(): TdApi.InviteVideoChatParticipants = TdApi.InviteVideoChatParticipants(
    this.groupCallId,
    this.userIds
)

