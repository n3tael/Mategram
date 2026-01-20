package com.xxcactussell.domain

data class ChatInviteLinkInfo(
    val chatId: Long,
    val accessibleFor: Int,
    val type: InviteLinkChatType,
    val title: String,
    val photo: ChatPhotoInfo? = null,
    val accentColorId: Int,
    val description: String,
    val memberCount: Int,
    val memberUserIds: LongArray,
    val subscriptionInfo: ChatInviteLinkSubscriptionInfo? = null,
    val createsJoinRequest: Boolean,
    val isPublic: Boolean,
    val verificationStatus: VerificationStatus? = null
) : Object
