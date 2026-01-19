package com.xxcactussell.domain.fullinfo.model

// Domain Models (Pure Kotlin)

data class AnimatedChatPhoto(
    val length: Int,
    val file: File,
    val mainFrameTimestamp: Double
)

sealed interface BackgroundFill 

data class BackgroundFillFreeformGradient(
    val colors: IntArray
): BackgroundFill

data class BackgroundFillGradient(
    val topColor: Int,
    val bottomColor: Int,
    val rotationAngle: Int
): BackgroundFill

data class BackgroundFillSolid(
    val color: Int
): BackgroundFill

data class BotCommand(
    val command: String,
    val description: String
)

data class BotCommands(
    val botUserId: Long,
    val commands: List<BotCommand>
)

data class BotVerification(
    val botUserId: Long,
    val iconCustomEmojiId: Long,
    val customDescription: FormattedText
)

data class ChatAdministratorRights(
    val canManageChat: Boolean,
    val canChangeInfo: Boolean,
    val canPostMessages: Boolean,
    val canEditMessages: Boolean,
    val canDeleteMessages: Boolean,
    val canInviteUsers: Boolean,
    val canRestrictMembers: Boolean,
    val canPinMessages: Boolean,
    val canManageTopics: Boolean,
    val canPromoteMembers: Boolean,
    val canManageVideoChats: Boolean,
    val canPostStories: Boolean,
    val canEditStories: Boolean,
    val canDeleteStories: Boolean,
    val canManageDirectMessages: Boolean,
    val isAnonymous: Boolean
)

data class ChatInviteLink(
    val inviteLink: String,
    val name: String,
    val creatorUserId: Long,
    val date: Int,
    val editDate: Int,
    val expirationDate: Int,
    val subscriptionPricing: StarSubscriptionPricing? = null,
    val memberLimit: Int,
    val memberCount: Int,
    val expiredMemberCount: Int,
    val pendingJoinRequestCount: Int,
    val createsJoinRequest: Boolean,
    val isPrimary: Boolean,
    val isRevoked: Boolean
)

data class ChatPhoto(
    val id: Long,
    val addedDate: Int,
    val minithumbnail: Minithumbnail? = null,
    val sizes: List<PhotoSize>,
    val animation: AnimatedChatPhoto? = null,
    val smallAnimation: AnimatedChatPhoto? = null,
    val sticker: ChatPhotoSticker? = null
)

data class ChatPhotoSticker(
    val type: ChatPhotoStickerType,
    val backgroundFill: BackgroundFill
)

sealed interface ChatPhotoStickerType 

data class ChatPhotoStickerTypeCustomEmoji(
    val customEmojiId: Long
): ChatPhotoStickerType

data class ChatPhotoStickerTypeRegularOrMask(
    val stickerSetId: Long,
    val stickerId: Long
): ChatPhotoStickerType

data class File(
    val id: Int,
    val size: Long,
    val expectedSize: Long,
    val local: LocalFile,
    val remote: RemoteFile
)

data class FormattedText(
    val text: String,
    val entities: List<TextEntity>
)

data class LocalFile(
    val path: String,
    val canBeDownloaded: Boolean,
    val canBeDeleted: Boolean,
    val isDownloadingActive: Boolean,
    val isDownloadingCompleted: Boolean,
    val downloadOffset: Long,
    val downloadedPrefixSize: Long,
    val downloadedSize: Long
)

data class Location(
    val latitude: Double,
    val longitude: Double,
    val horizontalAccuracy: Double
)

data class Minithumbnail(
    val width: Int,
    val height: Int,
    val data: ByteArray
)

data class PhotoSize(
    val type: String,
    val photo: File,
    val width: Int,
    val height: Int,
    val progressiveSizes: IntArray
)

sealed interface ProfileTab 

object ProfileTabFiles: ProfileTab

object ProfileTabGifs: ProfileTab

object ProfileTabGifts: ProfileTab

object ProfileTabLinks: ProfileTab

object ProfileTabMedia: ProfileTab

object ProfileTabMusic: ProfileTab

object ProfileTabPosts: ProfileTab

object ProfileTabVoice: ProfileTab

data class RemoteFile(
    val id: String,
    val uniqueId: String,
    val isUploadingActive: Boolean,
    val isUploadingCompleted: Boolean,
    val uploadedSize: Long
)

data class StarSubscriptionPricing(
    val period: Int,
    val starCount: Long
)

data class TextEntity(
    val offset: Int,
    val length: Int,
    val type: TextEntityType
)

sealed interface TextEntityType 

object TextEntityTypeBankCardNumber: TextEntityType

object TextEntityTypeBlockQuote: TextEntityType

object TextEntityTypeBold: TextEntityType

object TextEntityTypeBotCommand: TextEntityType

object TextEntityTypeCashtag: TextEntityType

object TextEntityTypeCode: TextEntityType

data class TextEntityTypeCustomEmoji(
    val customEmojiId: Long
): TextEntityType

object TextEntityTypeEmailAddress: TextEntityType

object TextEntityTypeExpandableBlockQuote: TextEntityType

object TextEntityTypeHashtag: TextEntityType

object TextEntityTypeItalic: TextEntityType

data class TextEntityTypeMediaTimestamp(
    val mediaTimestamp: Int
): TextEntityType

object TextEntityTypeMention: TextEntityType

data class TextEntityTypeMentionName(
    val userId: Long
): TextEntityType

object TextEntityTypePhoneNumber: TextEntityType

object TextEntityTypePre: TextEntityType

data class TextEntityTypePreCode(
    val language: String
): TextEntityType

object TextEntityTypeSpoiler: TextEntityType

object TextEntityTypeStrikethrough: TextEntityType

data class TextEntityTypeTextUrl(
    val url: String
): TextEntityType

object TextEntityTypeUnderline: TextEntityType

object TextEntityTypeUrl: TextEntityType

