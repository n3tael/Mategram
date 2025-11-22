package com.xxcactussell.data.utils.todomain

import com.xxcactussell.data.utils.toDomain
import com.xxcactussell.domain.chats.model.ChatAdministratorRights
import com.xxcactussell.domain.chats.model.ChatMemberStatus
import org.drinkless.tdlib.TdApi

fun TdApi.ChatMemberStatus.toDomain(): ChatMemberStatus {
    return when (this) {
        is TdApi.ChatMemberStatusCreator -> ChatMemberStatus.Creator(
            customTitle = this.customTitle,
            isAnonymous = this.isAnonymous,
            isMember = this.isMember
        )
        is TdApi.ChatMemberStatusAdministrator -> ChatMemberStatus.Administrator(
            customTitle = this.customTitle,
            canBeEdited = this.canBeEdited,
            rights = this.rights.toDomain()
        )
        is TdApi.ChatMemberStatusMember -> ChatMemberStatus.Member(
            memberUntilDate = this.memberUntilDate
        )
        is TdApi.ChatMemberStatusRestricted -> ChatMemberStatus.Restricted(
            isMember = this.isMember,
            restrictedUntilDate = this.restrictedUntilDate,
            permissions = this.permissions.toDomain()
        )
        is TdApi.ChatMemberStatusLeft -> ChatMemberStatus.Left
        is TdApi.ChatMemberStatusBanned -> ChatMemberStatus.Banned(
            bannedUntilDate = this.bannedUntilDate
        )
        else -> error("Unknown ChatMemberStatus type: ${this::class.java.simpleName}")
    }
}

fun TdApi.ChatAdministratorRights.toDomain(): ChatAdministratorRights {
    return ChatAdministratorRights(
        canManageChat = this.canManageChat,
        canChangeInfo = this.canChangeInfo,
        canPostMessages = this.canPostMessages,
        canEditMessages = this.canEditMessages,
        canDeleteMessages = this.canDeleteMessages,
        canInviteUsers = this.canInviteUsers,
        canRestrictMembers = this.canRestrictMembers,
        canPinMessages = this.canPinMessages,
        canManageTopics = this.canManageTopics,
        canPromoteMembers = this.canPromoteMembers,
        canManageVideoChats = this.canManageVideoChats,
        canPostStories = this.canPostStories,
        canEditStories = this.canEditStories,
        canDeleteStories = this.canDeleteStories,
        canManageDirectMessages = this.canManageDirectMessages,
        isAnonymous = this.isAnonymous
    )
}