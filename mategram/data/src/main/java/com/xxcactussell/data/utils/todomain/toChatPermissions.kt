package com.xxcactussell.data.utils

import com.xxcactussell.domain.chats.model.ChatPermissions
import org.drinkless.tdlib.TdApi

fun TdApi.ChatPermissions.toDomain() : ChatPermissions {
    return ChatPermissions(
        canSendBasicMessages = this.canSendBasicMessages,
        canSendOtherMessages = this.canSendOtherMessages,
        canAddLinkPreviews = this.canAddLinkPreviews,

        canSendAudios = this.canSendAudios,
        canSendDocuments = this.canSendDocuments,
        canSendPhotos = this.canSendPhotos,
        canSendVideos = this.canSendVideos,
        canSendVideoNotes = this.canSendVideoNotes,
        canSendVoiceNotes = this.canSendVoiceNotes,

        canSendPolls = this.canSendPolls,
        canCreateTopics = this.canCreateTopics,

        canChangeInfo = this.canChangeInfo,
        canInviteUsers = this.canInviteUsers,
        canPinMessages = this.canPinMessages
    )
}