package com.xxcactussell.domain.chats.model

import com.xxcactussell.domain.messages.model.File
import com.xxcactussell.domain.messages.model.Minithumbnail

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val usernames: Usernames? = null,
    val phoneNumber: String,
    val status: ChatStatus,
    val profilePhoto: ProfilePhoto? = null,
    val accentColorId: Int,
    val backgroundCustomEmojiId: Long,
    val profileAccentColorId: Int,
    val profileBackgroundCustomEmojiId: Long,
    val emojiStatus: EmojiStatus? = null,
    val isContact: Boolean,
    val isMutualContact: Boolean,
    val isCloseFriend: Boolean,
    val verificationStatus: VerificationStatus? = null,
    val isPremium: Boolean,
    val isSupport: Boolean,
    val restrictionInfo: RestrictionInfo? = null,
    val hasActiveStories: Boolean,
    val hasUnreadActiveStories: Boolean,
    val restrictsNewChats: Boolean,
    val paidMessageStarCount: Long,
    val haveAccess: Boolean,
    val type: UserType,
    val languageCode: String,
    val addedToAttachmentMenu: Boolean,
)

data class RestrictionInfo(
    val restrictionReason: String,
    val hasSensitiveContent: Boolean
)

sealed interface UserType {
    object Regular : UserType
    object Deleted : UserType
    data class Bot(
        val canBeEdited: Boolean,
        val canJoinGroups: Boolean,
        val canReadAllGroupMessages: Boolean,
        val hasMainWebApp: Boolean,
        val isInline: Boolean,
        val inlineQueryPlaceholder: String,
        val needLocation: Boolean,
        val canConnectToBusiness: Boolean,
        val canBeAddedToAttachmentMenu: Boolean,
        val activeUserCount: Int
    ) : UserType
    object Unknown: UserType
}

data class Usernames(
    val activeUsernames: List<String>,
    val disabledUsernames: List<String>,
    val editableUsernames: String
)

data class ProfilePhoto(
    val id: Long,
    val small: File,
    val big: File,
    val miniThumbnail: Minithumbnail?,
    val hasAnimation: Boolean,
    val isPersonal: Boolean
)

fun ProfilePhoto.toChatPhoto() : ChatPhoto {
    return ChatPhoto(
        small,
        big,
        hasAnimation,
        isPersonal
    )
}

data class EmojiStatus(
    val type: EmojiStatusType,
    val expirationDate: Int
)

sealed interface EmojiStatusType {
    val id: Long
    data class Custom(override val id: Long) : EmojiStatusType
    data class UpgradableGift(
        override val id: Long,
        val giftTitle: String,
        val giftName: String,
        val modelCustomEmojiId: Long,
        val symbolCustomEmojiId: Long,
        val backdropColors: UpgradedGiftBackdropColors
    ) : EmojiStatusType
}

data class UpgradedGiftBackdropColors(
    val centerColor: Int,
    val edgeColor: Int,
    val symbolColor: Int,
    val textColor: Int
)

data class VerificationStatus(
    val isVerified: Boolean,
    val isScam: Boolean,
    val isFake: Boolean,
    val botVerificationIconCustomEmojiId: Long
)