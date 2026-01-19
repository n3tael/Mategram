package com.xxcactussell.domain.fullinfo.model

// Domain Models (Pure Kotlin)

data class BasicGroupFullInfo(
    val photo: ChatPhoto? = null,
    val description: String,
    val creatorUserId: Long,
    val members: List<ChatMember>,
    val canHideMembers: Boolean,
    val canToggleAggressiveAntiSpam: Boolean,
    val inviteLink: ChatInviteLink? = null,
    val botCommands: List<BotCommands>
)

data class ChatMember(
    val memberId: MessageSender,
    val inviterUserId: Long,
    val joinedChatDate: Int,
    val status: ChatMemberStatus
)

sealed interface ChatMemberStatus 

data class ChatMemberStatusAdministrator(
    val customTitle: String,
    val canBeEdited: Boolean,
    val rights: ChatAdministratorRights
): ChatMemberStatus

data class ChatMemberStatusBanned(
    val bannedUntilDate: Int
): ChatMemberStatus

data class ChatMemberStatusCreator(
    val customTitle: String,
    val isAnonymous: Boolean,
    val isMember: Boolean
): ChatMemberStatus

object ChatMemberStatusLeft: ChatMemberStatus

data class ChatMemberStatusMember(
    val memberUntilDate: Int
): ChatMemberStatus

data class ChatMemberStatusRestricted(
    val isMember: Boolean,
    val restrictedUntilDate: Int,
    val permissions: ChatPermissions
): ChatMemberStatus

data class ChatPermissions(
    val canSendBasicMessages: Boolean,
    val canSendAudios: Boolean,
    val canSendDocuments: Boolean,
    val canSendPhotos: Boolean,
    val canSendVideos: Boolean,
    val canSendVideoNotes: Boolean,
    val canSendVoiceNotes: Boolean,
    val canSendPolls: Boolean,
    val canSendOtherMessages: Boolean,
    val canAddLinkPreviews: Boolean,
    val canChangeInfo: Boolean,
    val canInviteUsers: Boolean,
    val canPinMessages: Boolean,
    val canCreateTopics: Boolean
)

sealed interface MessageSender 

data class MessageSenderChat(
    val chatId: Long
): MessageSender

data class MessageSenderUser(
    val userId: Long
): MessageSender

