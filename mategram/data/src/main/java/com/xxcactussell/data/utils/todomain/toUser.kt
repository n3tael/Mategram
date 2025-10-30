package com.xxcactussell.data.utils.todomain

import com.xxcactussell.data.utils.toDomain
import com.xxcactussell.domain.chats.model.ChatStatus
import com.xxcactussell.domain.chats.model.EmojiStatus
import com.xxcactussell.domain.chats.model.EmojiStatusType
import com.xxcactussell.domain.chats.model.ProfilePhoto
import com.xxcactussell.domain.chats.model.RestrictionInfo
import com.xxcactussell.domain.chats.model.UpgradedGiftBackdropColors
import com.xxcactussell.domain.chats.model.User
import com.xxcactussell.domain.chats.model.UserType
import com.xxcactussell.domain.chats.model.Usernames
import com.xxcactussell.domain.chats.model.VerificationStatus
import org.drinkless.tdlib.TdApi

fun TdApi.Usernames.toDomain(): Usernames {
    return Usernames(
        activeUsernames = this.activeUsernames.toList(),
        disabledUsernames = this.disabledUsernames.toList(),
        editableUsernames = this.editableUsername
    )
}

fun TdApi.ProfilePhoto.toDomain(): ProfilePhoto {
    return ProfilePhoto(
        id = this.id,
        small = this.small.toDomain(),
        big = this.big.toDomain(),
        miniThumbnail = this.minithumbnail?.toDomain(),
        hasAnimation = this.hasAnimation,
        isPersonal = this.isPersonal
    )
}

fun TdApi.EmojiStatusType.toDomain(): EmojiStatusType {
    return when (this.constructor) {
        TdApi.EmojiStatusTypeCustomEmoji.CONSTRUCTOR -> {
            val type = this as TdApi.EmojiStatusTypeCustomEmoji
            EmojiStatusType.Custom(id = type.customEmojiId)
        }
        TdApi.EmojiStatusTypeUpgradedGift.CONSTRUCTOR -> {
            val type = this as TdApi.EmojiStatusTypeUpgradedGift
            EmojiStatusType.UpgradableGift(
                id = type.upgradedGiftId,
                giftTitle = type.giftTitle,
                giftName = type.giftName,
                modelCustomEmojiId = type.modelCustomEmojiId,
                symbolCustomEmojiId = type.symbolCustomEmojiId,
                backdropColors = UpgradedGiftBackdropColors(
                    centerColor = type.backdropColors.centerColor,
                    edgeColor = type.backdropColors.edgeColor,
                    symbolColor = type.backdropColors.symbolColor,
                    textColor = type.backdropColors.textColor,
                )
            )
        }
        else -> EmojiStatusType.Custom(id = 0L)
    }
}

fun TdApi.EmojiStatus.toDomain(): EmojiStatus {
    return EmojiStatus(
        type = this.type.toDomain(),
        expirationDate = this.expirationDate
    )
}

fun TdApi.UserType.toDomain(): UserType {
    return when (this.constructor) {
        TdApi.UserTypeRegular.CONSTRUCTOR -> UserType.Regular
        TdApi.UserTypeDeleted.CONSTRUCTOR -> UserType.Deleted
        TdApi.UserTypeBot.CONSTRUCTOR -> {
            val type = this as TdApi.UserTypeBot
            UserType.Bot(
                canBeEdited = type.canBeEdited,
                canJoinGroups = type.canJoinGroups,
                canReadAllGroupMessages = type.canReadAllGroupMessages,
                hasMainWebApp = type.hasMainWebApp,
                isInline = type.isInline,
                inlineQueryPlaceholder = type.inlineQueryPlaceholder,
                needLocation = type.needLocation,
                canConnectToBusiness = type.canConnectToBusiness,
                canBeAddedToAttachmentMenu = type.canBeAddedToAttachmentMenu,
                activeUserCount = type.activeUserCount
            )
        }
        else -> UserType.Unknown
    }
}

fun TdApi.VerificationStatus.toDomain(): VerificationStatus {
    return VerificationStatus(
        isVerified = this.isVerified,
        isScam = this.isScam,
        isFake = this.isFake,
        botVerificationIconCustomEmojiId = this.botVerificationIconCustomEmojiId
    )
}

fun TdApi.RestrictionInfo.toDomain(): RestrictionInfo {
    return RestrictionInfo(
        restrictionReason = this.restrictionReason,
        hasSensitiveContent = this.hasSensitiveContent
    )
}

fun TdApi.UserStatus.toDomain(): ChatStatus {
    return when (this.constructor) {
        TdApi.UserStatusOnline.CONSTRUCTOR -> ChatStatus.Online

        TdApi.UserStatusOffline.CONSTRUCTOR -> {
            val status = this as TdApi.UserStatusOffline
            ChatStatus.Offline(wasOnline = status.wasOnline)
        }
        TdApi.UserStatusRecently.CONSTRUCTOR -> ChatStatus.Recently
        TdApi.UserStatusLastWeek.CONSTRUCTOR -> ChatStatus.LastWeek
        TdApi.UserStatusLastMonth.CONSTRUCTOR -> ChatStatus.LastMonth
        else -> ChatStatus.Empty
    }
}

fun TdApi.User.toDomain(): User {
    return User(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        phoneNumber = this.phoneNumber,

        usernames = this.usernames?.toDomain(),
        status = this.status.toDomain(),
        profilePhoto = this.profilePhoto?.toDomain(),
        emojiStatus = this.emojiStatus?.toDomain(),
        type = this.type.toDomain(),
        restrictionInfo = this.restrictionInfo?.toDomain(),
        verificationStatus = this.verificationStatus?.toDomain(),

        accentColorId = this.accentColorId,
        backgroundCustomEmojiId = this.backgroundCustomEmojiId,
        profileAccentColorId = this.profileAccentColorId,
        profileBackgroundCustomEmojiId = this.profileBackgroundCustomEmojiId,
        isContact = this.isContact,
        isMutualContact = this.isMutualContact,
        isCloseFriend = this.isCloseFriend,
        isPremium = this.isPremium,
        isSupport = this.isSupport,
        hasActiveStories = this.hasActiveStories,
        hasUnreadActiveStories = this.hasUnreadActiveStories,
        restrictsNewChats = this.restrictsNewChats,
        paidMessageStarCount = this.paidMessageStarCount,
        haveAccess = this.haveAccess,
        languageCode = this.languageCode,
        addedToAttachmentMenu = this.addedToAttachmentMenu
    )
}