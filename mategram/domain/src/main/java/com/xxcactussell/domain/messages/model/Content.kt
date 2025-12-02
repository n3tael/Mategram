package com.xxcactussell.domain.messages.model

data class Address(
    val countryCode: String,
    val state: String,
    val city: String,
    val streetLine1: String,
    val streetLine2: String,
    val postalCode: String
)

data class AlternativeVideo(
    val id: Long,
    val width: Int,
    val height: Int,
    val codec: String,
    val hlsFile: File,
    val video: File
)

data class AnimatedChatPhoto(
    val length: Int,
    val file: File,
    val mainFrameTimestamp: Double
)

data class AnimatedEmoji(
    val sticker: Sticker? = null,
    val stickerWidth: Int,
    val stickerHeight: Int,
    val fitzpatrickType: Int,
    val sound: File? = null
)

data class Animation(
    val duration: Int,
    val width: Int,
    val height: Int,
    val fileName: String,
    val mimeType: String,
    val hasStickers: Boolean,
    val minithumbnail: Minithumbnail? = null,
    val thumbnail: Thumbnail? = null,
    val animation: File
)

data class Audio(
    val duration: Int,
    val title: String,
    val performer: String,
    val fileName: String,
    val mimeType: String,
    val albumCoverMinithumbnail: Minithumbnail? = null,
    val albumCoverThumbnail: Thumbnail? = null,
    val externalAlbumCovers: List<Thumbnail>,
    val audio: File
)

data class Background(
    val id: Long,
    val isDefault: Boolean,
    val isDark: Boolean,
    val name: String,
    val document: Document? = null,
    val type: BackgroundType
)

sealed interface BackgroundFill

data class BackgroundFillFreeformGradient(
    val colors: IntArray
): BackgroundFill {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BackgroundFillFreeformGradient

        if (!colors.contentEquals(other.colors)) return false

        return true
    }

    override fun hashCode(): Int {
        return colors.contentHashCode()
    }
}

data class BackgroundFillGradient(
    val topColor: Int,
    val bottomColor: Int,
    val rotationAngle: Int
): BackgroundFill

data class BackgroundFillSolid(
    val color: Int
): BackgroundFill

sealed interface BackgroundType

data class BackgroundTypeChatTheme(
    val themeName: String
): BackgroundType

data class BackgroundTypeFill(
    val fill: BackgroundFill
): BackgroundType

data class BackgroundTypePattern(
    val fill: BackgroundFill,
    val intensity: Int,
    val isInverted: Boolean,
    val isMoving: Boolean
): BackgroundType

data class BackgroundTypeWallpaper(
    val isBlurred: Boolean,
    val isMoving: Boolean
): BackgroundType

sealed interface BotWriteAccessAllowReason

object BotWriteAccessAllowReasonAcceptedRequest: BotWriteAccessAllowReason

object BotWriteAccessAllowReasonAddedToAttachmentMenu: BotWriteAccessAllowReason

data class BotWriteAccessAllowReasonConnectedWebsite(
    val domainName: String
): BotWriteAccessAllowReason

data class BotWriteAccessAllowReasonLaunchedWebApp(
    val webApp: WebApp
): BotWriteAccessAllowReason

sealed interface BuiltInTheme

object BuiltInThemeArctic: BuiltInTheme

object BuiltInThemeClassic: BuiltInTheme

object BuiltInThemeDay: BuiltInTheme

object BuiltInThemeNight: BuiltInTheme

object BuiltInThemeTinted: BuiltInTheme

sealed interface CallDiscardReason

object CallDiscardReasonDeclined: CallDiscardReason

object CallDiscardReasonDisconnected: CallDiscardReason

object CallDiscardReasonEmpty: CallDiscardReason

object CallDiscardReasonHungUp: CallDiscardReason

object CallDiscardReasonMissed: CallDiscardReason

data class CallDiscardReasonUpgradeToGroupCall(
    val inviteLink: String
): CallDiscardReason

data class ChatBackground(
    val background: Background,
    val darkThemeDimming: Int
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

sealed interface ChatTheme

data class ChatThemeEmoji(
    val name: String
): ChatTheme

data class ChatThemeGift(
    val giftTheme: GiftChatTheme
): ChatTheme

data class Checklist(
    val title: FormattedText,
    val tasks: List<ChecklistTask>,
    val othersCanAddTasks: Boolean,
    val canAddTasks: Boolean,
    val othersCanMarkTasksAsDone: Boolean,
    val canMarkTasksAsDone: Boolean
)

data class ChecklistTask(
    val id: Int,
    val text: FormattedText,
    val completedByUserId: Long,
    val completionDate: Int
)

data class Contact(
    val phoneNumber: String,
    val firstName: String,
    val lastName: String,
    val vcard: String,
    val userId: Long
)

data class DatedFile(
    val file: File,
    val date: Int
)

sealed interface DiceStickers

data class DiceStickersRegular(
    val sticker: Sticker
): DiceStickers

data class DiceStickersSlotMachine(
    val background: Sticker,
    val lever: Sticker,
    val leftReel: Sticker,
    val centerReel: Sticker,
    val rightReel: Sticker
): DiceStickers

data class Document(
    val fileName: String,
    val mimeType: String,
    val minithumbnail: Minithumbnail? = null,
    val thumbnail: Thumbnail? = null,
    val document: File
)

data class EncryptedCredentials(
    val data: ByteArray,
    val hash: ByteArray,
    val secret: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EncryptedCredentials

        if (!data.contentEquals(other.data)) return false
        if (!hash.contentEquals(other.hash)) return false
        if (!secret.contentEquals(other.secret)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = data.contentHashCode()
        result = 31 * result + hash.contentHashCode()
        result = 31 * result + secret.contentHashCode()
        return result
    }
}

data class EncryptedPassportElement(
    val type: PassportElementType,
    val data: ByteArray,
    val frontSide: DatedFile,
    val reverseSide: DatedFile? = null,
    val selfie: DatedFile? = null,
    val translation: List<DatedFile>,
    val files: List<DatedFile>,
    val value: String,
    val hash: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EncryptedPassportElement

        if (type != other.type) return false
        if (!data.contentEquals(other.data)) return false
        if (frontSide != other.frontSide) return false
        if (reverseSide != other.reverseSide) return false
        if (selfie != other.selfie) return false
        if (translation != other.translation) return false
        if (files != other.files) return false
        if (value != other.value) return false
        if (hash != other.hash) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + data.contentHashCode()
        result = 31 * result + frontSide.hashCode()
        result = 31 * result + (reverseSide?.hashCode() ?: 0)
        result = 31 * result + (selfie?.hashCode() ?: 0)
        result = 31 * result + translation.hashCode()
        result = 31 * result + files.hashCode()
        result = 31 * result + value.hashCode()
        result = 31 * result + hash.hashCode()
        return result
    }
}

data class Error(
    val code: Int,
    val message: String
)

data class File(
    val id: Int,
    val size: Long,
    val expectedSize: Long,
    val local: LocalFile,
    val remote: RemoteFile
)

data class FormattedText(
    val text: String,
    val entities: List<TextEntity> = emptyList<TextEntity>()
)

data class ForumTopicIcon(
    val color: Int,
    val customEmojiId: Long
)

data class Game(
    val id: Long,
    val shortName: String,
    val title: String,
    val text: FormattedText,
    val description: String,
    val photo: Photo,
    val animation: Animation? = null
)

data class Gift(
    val id: Long,
    val publisherChatId: Long,
    val sticker: Sticker,
    val starCount: Long,
    val defaultSellStarCount: Long,
    val upgradeStarCount: Long,
    val isForBirthday: Boolean,
    val isPremium: Boolean,
    val nextSendDate: Int,
    val userLimits: GiftPurchaseLimits? = null,
    val overallLimits: GiftPurchaseLimits? = null,
    val firstSendDate: Int,
    val lastSendDate: Int
)

data class GiftChatTheme(
    val gift: UpgradedGift,
    val lightSettings: ThemeSettings,
    val darkSettings: ThemeSettings
)

data class GiftPurchaseLimits(
    val totalCount: Int,
    val remainingCount: Int
)

data class GiftResaleParameters(
    val starCount: Long,
    val toncoinCentCount: Long,
    val toncoinOnly: Boolean
)

sealed interface GiftResalePrice

data class GiftResalePriceStar(
    val starCount: Long
): GiftResalePrice

data class GiftResalePriceTon(
    val toncoinCentCount: Long
): GiftResalePrice

data class GiveawayParameters(
    val boostedChatId: Long,
    val additionalChatIds: LongArray,
    val winnersSelectionDate: Int,
    val onlyNewMembers: Boolean,
    val hasPublicWinners: Boolean,
    val countryCodes: List<String>,
    val prizeDescription: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GiveawayParameters

        if (boostedChatId != other.boostedChatId) return false
        if (winnersSelectionDate != other.winnersSelectionDate) return false
        if (onlyNewMembers != other.onlyNewMembers) return false
        if (hasPublicWinners != other.hasPublicWinners) return false
        if (!additionalChatIds.contentEquals(other.additionalChatIds)) return false
        if (countryCodes != other.countryCodes) return false
        if (prizeDescription != other.prizeDescription) return false

        return true
    }

    override fun hashCode(): Int {
        var result = boostedChatId.hashCode()
        result = 31 * result + winnersSelectionDate
        result = 31 * result + onlyNewMembers.hashCode()
        result = 31 * result + hasPublicWinners.hashCode()
        result = 31 * result + additionalChatIds.contentHashCode()
        result = 31 * result + countryCodes.hashCode()
        result = 31 * result + prizeDescription.hashCode()
        return result
    }
}

sealed interface GiveawayPrize

data class GiveawayPrizePremium(
    val monthCount: Int
): GiveawayPrize

data class GiveawayPrizeStars(
    val starCount: Long
): GiveawayPrize

sealed interface InviteLinkChatType

object InviteLinkChatTypeBasicGroup: InviteLinkChatType

object InviteLinkChatTypeChannel: InviteLinkChatType

object InviteLinkChatTypeSupergroup: InviteLinkChatType

data class LinkPreview(
    val url: String,
    val displayUrl: String,
    val siteName: String,
    val title: String,
    val description: FormattedText,
    val author: String,
    val type: LinkPreviewType,
    val hasLargeMedia: Boolean,
    val showLargeMedia: Boolean,
    val showMediaAboveDescription: Boolean,
    val skipConfirmation: Boolean,
    val showAboveText: Boolean,
    val instantViewVersion: Int
)

sealed interface LinkPreviewAlbumMedia

data class LinkPreviewAlbumMediaPhoto(
    val photo: Photo
): LinkPreviewAlbumMedia

data class LinkPreviewAlbumMediaVideo(
    val video: Video
): LinkPreviewAlbumMedia

data class LinkPreviewOptions(
    val isDisabled: Boolean,
    val url: String,
    val forceSmallMedia: Boolean,
    val forceLargeMedia: Boolean,
    val showAboveText: Boolean
)

sealed interface LinkPreviewType

data class LinkPreviewTypeAlbum(
    val media: List<LinkPreviewAlbumMedia>,
    val caption: String
): LinkPreviewType

data class LinkPreviewTypeAnimation(
    val animation: Animation
): LinkPreviewType

data class LinkPreviewTypeApp(
    val photo: Photo
): LinkPreviewType

data class LinkPreviewTypeArticle(
    val photo: Photo? = null
): LinkPreviewType

data class LinkPreviewTypeAudio(
    val audio: Audio
): LinkPreviewType

data class LinkPreviewTypeBackground(
    val document: Document? = null,
    val backgroundType: BackgroundType? = null
): LinkPreviewType

data class LinkPreviewTypeChannelBoost(
    val photo: ChatPhoto? = null
): LinkPreviewType

data class LinkPreviewTypeChat(
    val type: InviteLinkChatType,
    val photo: ChatPhoto? = null,
    val createsJoinRequest: Boolean
): LinkPreviewType

data class LinkPreviewTypeDirectMessagesChat(
    val photo: ChatPhoto? = null
): LinkPreviewType

data class LinkPreviewTypeDocument(
    val document: Document
): LinkPreviewType

data class LinkPreviewTypeEmbeddedAnimationPlayer(
    val url: String,
    val thumbnail: Photo? = null,
    val duration: Int,
    val width: Int,
    val height: Int
): LinkPreviewType

data class LinkPreviewTypeEmbeddedAudioPlayer(
    val url: String,
    val thumbnail: Photo? = null,
    val duration: Int,
    val width: Int,
    val height: Int
): LinkPreviewType

data class LinkPreviewTypeEmbeddedVideoPlayer(
    val url: String,
    val thumbnail: Photo? = null,
    val duration: Int,
    val width: Int,
    val height: Int
): LinkPreviewType

data class LinkPreviewTypeExternalAudio(
    val url: String,
    val mimeType: String,
    val duration: Int
): LinkPreviewType

data class LinkPreviewTypeExternalVideo(
    val url: String,
    val mimeType: String,
    val width: Int,
    val height: Int,
    val duration: Int
): LinkPreviewType

data class LinkPreviewTypeGiftCollection(
    val icons: List<Sticker>
): LinkPreviewType

object LinkPreviewTypeGroupCall: LinkPreviewType

object LinkPreviewTypeInvoice: LinkPreviewType

object LinkPreviewTypeMessage: LinkPreviewType

data class LinkPreviewTypePhoto(
    val photo: Photo
): LinkPreviewType

object LinkPreviewTypePremiumGiftCode: LinkPreviewType

object LinkPreviewTypeShareableChatFolder: LinkPreviewType

data class LinkPreviewTypeSticker(
    val sticker: Sticker
): LinkPreviewType

data class LinkPreviewTypeStickerSet(
    val stickers: List<Sticker>
): LinkPreviewType

data class LinkPreviewTypeStory(
    val storyPosterChatId: Long,
    val storyId: Int
): LinkPreviewType

data class LinkPreviewTypeStoryAlbum(
    val photoIcon: Photo? = null,
    val videoIcon: Video? = null
): LinkPreviewType

data class LinkPreviewTypeSupergroupBoost(
    val photo: ChatPhoto? = null
): LinkPreviewType

data class LinkPreviewTypeTheme(
    val documents: List<Document>,
    val settings: ThemeSettings? = null
): LinkPreviewType

object LinkPreviewTypeUnsupported: LinkPreviewType

data class LinkPreviewTypeUpgradedGift(
    val gift: UpgradedGift
): LinkPreviewType

data class LinkPreviewTypeUser(
    val photo: ChatPhoto? = null,
    val isBot: Boolean
): LinkPreviewType

data class LinkPreviewTypeVideo(
    val video: Video,
    val cover: Photo? = null,
    val startTimestamp: Int
): LinkPreviewType

data class LinkPreviewTypeVideoChat(
    val photo: ChatPhoto? = null,
    val isLiveStream: Boolean,
    val joinsAsSpeaker: Boolean
): LinkPreviewType

data class LinkPreviewTypeVideoNote(
    val videoNote: VideoNote
): LinkPreviewType

data class LinkPreviewTypeVoiceNote(
    val voiceNote: VoiceNote
): LinkPreviewType

data class LinkPreviewTypeWebApp(
    val photo: Photo? = null
): LinkPreviewType

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

sealed interface MaskPoint

object MaskPointChin: MaskPoint

object MaskPointEyes: MaskPoint

object MaskPointForehead: MaskPoint

object MaskPointMouth: MaskPoint

data class MaskPosition(
    val point: MaskPoint,
    val xShift: Double,
    val yShift: Double,
    val scale: Double
)

data class MessageAnimatedEmoji(
    val animatedEmoji: AnimatedEmoji,
    val emoji: String
): MessageContent

data class MessageAnimation(
    val animation: Animation,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean,
    val hasSpoiler: Boolean,
    val isSecret: Boolean
): MessageContent

data class MessageAudio(
    val audio: Audio,
    val caption: FormattedText
): MessageContent

data class MessageBasicGroupChatCreate(
    val title: String,
    val memberUserIds: LongArray
): MessageContent {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessageBasicGroupChatCreate

        if (title != other.title) return false
        if (!memberUserIds.contentEquals(other.memberUserIds)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + memberUserIds.contentHashCode()
        return result
    }
}

data class MessageBotWriteAccessAllowed(
    val reason: BotWriteAccessAllowReason
): MessageContent

data class MessageCall(
    val isVideo: Boolean,
    val discardReason: CallDiscardReason,
    val duration: Int
): MessageContent

data class MessageChatAddMembers(
    val memberUserIds: LongArray
): MessageContent {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessageChatAddMembers

        if (!memberUserIds.contentEquals(other.memberUserIds)) return false

        return true
    }

    override fun hashCode(): Int {
        return memberUserIds.contentHashCode()
    }
}

data class MessageChatBoost(
    val boostCount: Int
): MessageContent

data class MessageChatChangePhoto(
    val photo: ChatPhoto
): MessageContent

data class MessageChatChangeTitle(
    val title: String
): MessageContent

data class MessageChatDeleteMember(
    val userId: Long
): MessageContent

object MessageChatDeletePhoto: MessageContent

object MessageChatJoinByLink: MessageContent

object MessageChatJoinByRequest: MessageContent

data class MessageChatSetBackground(
    val oldBackgroundMessageId: Long,
    val background: ChatBackground,
    val onlyForSelf: Boolean
): MessageContent

data class MessageChatSetMessageAutoDeleteTime(
    val messageAutoDeleteTime: Int,
    val fromUserId: Long
): MessageContent

data class MessageChatSetTheme(
    val theme: ChatTheme? = null
): MessageContent

data class MessageChatShared(
    val chat: SharedChat,
    val buttonId: Int
): MessageContent

data class MessageChatUpgradeFrom(
    val title: String,
    val basicGroupId: Long
): MessageContent

data class MessageChatUpgradeTo(
    val supergroupId: Long
): MessageContent

data class MessageChecklist(
    val list: Checklist
): MessageContent

data class MessageChecklistTasksAdded(
    val checklistMessageId: Long,
    val tasks: List<ChecklistTask>
): MessageContent

data class MessageChecklistTasksDone(
    val checklistMessageId: Long,
    val markedAsDoneTaskIds: IntArray,
    val markedAsNotDoneTaskIds: IntArray
): MessageContent {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessageChecklistTasksDone

        if (checklistMessageId != other.checklistMessageId) return false
        if (!markedAsDoneTaskIds.contentEquals(other.markedAsDoneTaskIds)) return false
        if (!markedAsNotDoneTaskIds.contentEquals(other.markedAsNotDoneTaskIds)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = checklistMessageId.hashCode()
        result = 31 * result + markedAsDoneTaskIds.contentHashCode()
        result = 31 * result + markedAsNotDoneTaskIds.contentHashCode()
        return result
    }
}

data class MessageContact(
    val contact: Contact
): MessageContent

object MessageContactRegistered: MessageContent

sealed interface MessageContent

data class MessageCustomServiceAction(
    val text: String
): MessageContent

data class MessageDice(
    val initialState: DiceStickers? = null,
    val finalState: DiceStickers? = null,
    val emoji: String,
    val value: Int,
    val successAnimationFrameNumber: Int
): MessageContent

data class MessageDirectMessagePriceChanged(
    val isEnabled: Boolean,
    val paidMessageStarCount: Long
): MessageContent

data class MessageDocument(
    val document: Document,
    val caption: FormattedText
): MessageContent

object MessageExpiredPhoto: MessageContent

object MessageExpiredVideo: MessageContent

object MessageExpiredVideoNote: MessageContent

object MessageExpiredVoiceNote: MessageContent

data class MessageForumTopicCreated(
    val name: String,
    val icon: ForumTopicIcon
): MessageContent

data class MessageForumTopicEdited(
    val name: String,
    val editIconCustomEmojiId: Boolean,
    val iconCustomEmojiId: Long
): MessageContent

data class MessageForumTopicIsClosedToggled(
    val isClosed: Boolean
): MessageContent

data class MessageForumTopicIsHiddenToggled(
    val isHidden: Boolean
): MessageContent

data class MessageGame(
    val game: Game
): MessageContent

data class MessageGameScore(
    val gameMessageId: Long,
    val gameId: Long,
    val score: Int
): MessageContent

data class MessageGift(
    val gift: Gift,
    val senderId: MessageSender? = null,
    val receiverId: MessageSender,
    val receivedGiftId: String,
    val text: FormattedText,
    val sellStarCount: Long,
    val prepaidUpgradeStarCount: Long,
    val isUpgradeSeparate: Boolean,
    val isPrivate: Boolean,
    val isSaved: Boolean,
    val isPrepaidUpgrade: Boolean,
    val canBeUpgraded: Boolean,
    val wasConverted: Boolean,
    val wasUpgraded: Boolean,
    val wasRefunded: Boolean,
    val upgradedReceivedGiftId: String,
    val prepaidUpgradeHash: String
): MessageContent

data class MessageGiftedPremium(
    val gifterUserId: Long,
    val receiverUserId: Long,
    val text: FormattedText,
    val currency: String,
    val amount: Long,
    val cryptocurrency: String,
    val cryptocurrencyAmount: Long,
    val monthCount: Int,
    val sticker: Sticker? = null
): MessageContent

data class MessageGiftedStars(
    val gifterUserId: Long,
    val receiverUserId: Long,
    val currency: String,
    val amount: Long,
    val cryptocurrency: String,
    val cryptocurrencyAmount: Long,
    val starCount: Long,
    val transactionId: String,
    val sticker: Sticker? = null
): MessageContent

data class MessageGiftedTon(
    val gifterUserId: Long,
    val receiverUserId: Long,
    val tonAmount: Long,
    val transactionId: String,
    val sticker: Sticker? = null
): MessageContent

data class MessageGiveaway(
    val parameters: GiveawayParameters,
    val winnerCount: Int,
    val prize: GiveawayPrize,
    val sticker: Sticker? = null
): MessageContent

data class MessageGiveawayCompleted(
    val giveawayMessageId: Long,
    val winnerCount: Int,
    val isStarGiveaway: Boolean,
    val unclaimedPrizeCount: Int
): MessageContent

data class MessageGiveawayCreated(
    val starCount: Long
): MessageContent

data class MessageGiveawayPrizeStars(
    val starCount: Long,
    val transactionId: String,
    val boostedChatId: Long,
    val giveawayMessageId: Long,
    val isUnclaimed: Boolean,
    val sticker: Sticker? = null
): MessageContent

data class MessageGiveawayWinners(
    val boostedChatId: Long,
    val giveawayMessageId: Long,
    val additionalChatCount: Int,
    val actualWinnersSelectionDate: Int,
    val onlyNewMembers: Boolean,
    val wasRefunded: Boolean,
    val prize: GiveawayPrize,
    val prizeDescription: String,
    val winnerCount: Int,
    val winnerUserIds: LongArray,
    val unclaimedPrizeCount: Int
): MessageContent {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessageGiveawayWinners

        if (boostedChatId != other.boostedChatId) return false
        if (giveawayMessageId != other.giveawayMessageId) return false
        if (additionalChatCount != other.additionalChatCount) return false
        if (actualWinnersSelectionDate != other.actualWinnersSelectionDate) return false
        if (onlyNewMembers != other.onlyNewMembers) return false
        if (wasRefunded != other.wasRefunded) return false
        if (winnerCount != other.winnerCount) return false
        if (unclaimedPrizeCount != other.unclaimedPrizeCount) return false
        if (prize != other.prize) return false
        if (prizeDescription != other.prizeDescription) return false
        if (!winnerUserIds.contentEquals(other.winnerUserIds)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = boostedChatId.hashCode()
        result = 31 * result + giveawayMessageId.hashCode()
        result = 31 * result + additionalChatCount
        result = 31 * result + actualWinnersSelectionDate
        result = 31 * result + onlyNewMembers.hashCode()
        result = 31 * result + wasRefunded.hashCode()
        result = 31 * result + winnerCount
        result = 31 * result + unclaimedPrizeCount
        result = 31 * result + prize.hashCode()
        result = 31 * result + prizeDescription.hashCode()
        result = 31 * result + winnerUserIds.contentHashCode()
        return result
    }
}

data class MessageGroupCall(
    val isActive: Boolean,
    val wasMissed: Boolean,
    val isVideo: Boolean,
    val duration: Int,
    val otherParticipantIds: List<MessageSender>
): MessageContent

data class MessageInviteVideoChatParticipants(
    val groupCallId: Int,
    val userIds: LongArray
): MessageContent {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessageInviteVideoChatParticipants

        if (groupCallId != other.groupCallId) return false
        if (!userIds.contentEquals(other.userIds)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = groupCallId
        result = 31 * result + userIds.contentHashCode()
        return result
    }
}

data class MessageInvoice(
    val productInfo: ProductInfo,
    val currency: String,
    val totalAmount: Long,
    val startParameter: String,
    val isTest: Boolean,
    val needShippingAddress: Boolean,
    val receiptMessageId: Long,
    val paidMedia: PaidMedia? = null,
    val paidMediaCaption: FormattedText? = null
): MessageContent

data class MessageLocation(
    val location: Location,
    val livePeriod: Int,
    val expiresIn: Int,
    val heading: Int,
    val proximityAlertRadius: Int
): MessageContent

data class MessagePaidMedia(
    val starCount: Long,
    val media: List<PaidMedia>,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean
): MessageContent

data class MessagePaidMessagePriceChanged(
    val paidMessageStarCount: Long
): MessageContent

data class MessagePaidMessagesRefunded(
    val messageCount: Int,
    val starCount: Long
): MessageContent

data class MessagePassportDataReceived(
    val elements: List<EncryptedPassportElement>,
    val credentials: EncryptedCredentials
): MessageContent

data class MessagePassportDataSent(
    val types: List<PassportElementType>
): MessageContent

data class MessagePaymentRefunded(
    val ownerId: MessageSender,
    val currency: String,
    val totalAmount: Long,
    val invoicePayload: ByteArray,
    val telegramPaymentChargeId: String,
    val providerPaymentChargeId: String
): MessageContent {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessagePaymentRefunded

        if (totalAmount != other.totalAmount) return false
        if (ownerId != other.ownerId) return false
        if (currency != other.currency) return false
        if (!invoicePayload.contentEquals(other.invoicePayload)) return false
        if (telegramPaymentChargeId != other.telegramPaymentChargeId) return false
        if (providerPaymentChargeId != other.providerPaymentChargeId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = totalAmount.hashCode()
        result = 31 * result + ownerId.hashCode()
        result = 31 * result + currency.hashCode()
        result = 31 * result + invoicePayload.contentHashCode()
        result = 31 * result + telegramPaymentChargeId.hashCode()
        result = 31 * result + providerPaymentChargeId.hashCode()
        return result
    }
}

data class MessagePaymentSuccessful(
    val invoiceChatId: Long,
    val invoiceMessageId: Long,
    val currency: String,
    val totalAmount: Long,
    val subscriptionUntilDate: Int,
    val isRecurring: Boolean,
    val isFirstRecurring: Boolean,
    val invoiceName: String
): MessageContent

data class MessagePaymentSuccessfulBot(
    val currency: String,
    val totalAmount: Long,
    val subscriptionUntilDate: Int,
    val isRecurring: Boolean,
    val isFirstRecurring: Boolean,
    val invoicePayload: ByteArray,
    val shippingOptionId: String,
    val orderInfo: OrderInfo? = null,
    val telegramPaymentChargeId: String,
    val providerPaymentChargeId: String
): MessageContent {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessagePaymentSuccessfulBot

        if (totalAmount != other.totalAmount) return false
        if (subscriptionUntilDate != other.subscriptionUntilDate) return false
        if (isRecurring != other.isRecurring) return false
        if (isFirstRecurring != other.isFirstRecurring) return false
        if (currency != other.currency) return false
        if (!invoicePayload.contentEquals(other.invoicePayload)) return false
        if (shippingOptionId != other.shippingOptionId) return false
        if (orderInfo != other.orderInfo) return false
        if (telegramPaymentChargeId != other.telegramPaymentChargeId) return false
        if (providerPaymentChargeId != other.providerPaymentChargeId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = totalAmount.hashCode()
        result = 31 * result + subscriptionUntilDate
        result = 31 * result + isRecurring.hashCode()
        result = 31 * result + isFirstRecurring.hashCode()
        result = 31 * result + currency.hashCode()
        result = 31 * result + invoicePayload.contentHashCode()
        result = 31 * result + shippingOptionId.hashCode()
        result = 31 * result + (orderInfo?.hashCode() ?: 0)
        result = 31 * result + telegramPaymentChargeId.hashCode()
        result = 31 * result + providerPaymentChargeId.hashCode()
        return result
    }
}

data class MessagePhoto(
    val photo: Photo,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean,
    val hasSpoiler: Boolean,
    val isSecret: Boolean
): MessageContent

data class MessagePinMessage(
    val messageId: Long
): MessageContent

data class MessagePoll(
    val poll: Poll
): MessageContent

data class MessagePremiumGiftCode(
    val creatorId: MessageSender? = null,
    val text: FormattedText,
    val isFromGiveaway: Boolean,
    val isUnclaimed: Boolean,
    val currency: String,
    val amount: Long,
    val cryptocurrency: String,
    val cryptocurrencyAmount: Long,
    val monthCount: Int,
    val sticker: Sticker? = null,
    val code: String
): MessageContent

data class MessageProximityAlertTriggered(
    val travelerId: MessageSender,
    val watcherId: MessageSender,
    val distance: Int
): MessageContent

data class MessageRefundedUpgradedGift(
    val gift: Gift,
    val senderId: MessageSender,
    val receiverId: MessageSender,
    val origin: UpgradedGiftOrigin
): MessageContent

object MessageScreenshotTaken: MessageContent

sealed interface MessageSender

data class MessageSenderChat(
    val chatId: Long
): MessageSender

data class MessageSenderUser(
    val userId: Long
): MessageSender

data class MessageSticker(
    val sticker: Sticker,
    val isPremium: Boolean
): MessageContent

data class MessageStory(
    val storyPosterChatId: Long,
    val storyId: Int,
    val viaMention: Boolean
): MessageContent

data class MessageSuggestProfilePhoto(
    val photo: ChatPhoto
): MessageContent

data class MessageSuggestedPostApprovalFailed(
    val suggestedPostMessageId: Long,
    val price: SuggestedPostPrice
): MessageContent

data class MessageSuggestedPostApproved(
    val suggestedPostMessageId: Long,
    val price: SuggestedPostPrice? = null,
    val sendDate: Int
): MessageContent

data class MessageSuggestedPostDeclined(
    val suggestedPostMessageId: Long,
    val comment: String
): MessageContent

data class MessageSuggestedPostPaid(
    val suggestedPostMessageId: Long,
    val starAmount: StarAmount,
    val tonAmount: Long
): MessageContent

data class MessageSuggestedPostRefunded(
    val suggestedPostMessageId: Long,
    val reason: SuggestedPostRefundReason
): MessageContent

data class MessageSupergroupChatCreate(
    val title: String
): MessageContent

data class MessageText(
    val text: FormattedText,
    val linkPreview: LinkPreview? = null,
    val linkPreviewOptions: LinkPreviewOptions? = null
): MessageContent

object MessageUnsupported: MessageContent

data class MessageUpgradedGift(
    val gift: UpgradedGift,
    val senderId: MessageSender? = null,
    val receiverId: MessageSender,
    val origin: UpgradedGiftOrigin,
    val receivedGiftId: String,
    val isSaved: Boolean,
    val canBeTransferred: Boolean,
    val wasTransferred: Boolean,
    val transferStarCount: Long,
    val nextTransferDate: Int,
    val nextResaleDate: Int,
    val exportDate: Int
): MessageContent

data class MessageUsersShared(
    val users: List<SharedUser>,
    val buttonId: Int
): MessageContent

data class MessageVenue(
    val venue: Venue
): MessageContent

data class MessageVideo(
    val video: Video,
    val alternativeVideos: List<AlternativeVideo>,
    val storyboards: List<VideoStoryboard>,
    val cover: Photo? = null,
    val startTimestamp: Int,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean,
    val hasSpoiler: Boolean,
    val isSecret: Boolean
): MessageContent

data class MessageVideoChatEnded(
    val duration: Int
): MessageContent

data class MessageVideoChatScheduled(
    val groupCallId: Int,
    val startDate: Int
): MessageContent

data class MessageVideoChatStarted(
    val groupCallId: Int
): MessageContent

data class MessageVideoNote(
    val videoNote: VideoNote,
    val isViewed: Boolean,
    val isSecret: Boolean
): MessageContent

data class MessageVoiceNote(
    val voiceNote: VoiceNote,
    val caption: FormattedText,
    val isListened: Boolean
): MessageContent

data class MessageWebAppDataReceived(
    val buttonText: String,
    val data: String
): MessageContent

data class MessageWebAppDataSent(
    val buttonText: String
): MessageContent

data class Minithumbnail(
    val width: Int,
    val height: Int,
    val data: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Minithumbnail

        if (width != other.width) return false
        if (height != other.height) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        result = 31 * result + data.contentHashCode()
        return result
    }
}

data class OrderInfo(
    val name: String,
    val phoneNumber: String,
    val emailAddress: String,
    val shippingAddress: Address? = null
)

sealed interface PaidMedia

data class PaidMediaPhoto(
    val photo: Photo
): PaidMedia

data class PaidMediaPreview(
    val width: Int,
    val height: Int,
    val duration: Int,
    val minithumbnail: Minithumbnail? = null
): PaidMedia

object PaidMediaUnsupported: PaidMedia

data class PaidMediaVideo(
    val video: Video,
    val cover: Photo? = null,
    val startTimestamp: Int
): PaidMedia

sealed interface PassportElementType

object PassportElementTypeAddress: PassportElementType

object PassportElementTypeBankStatement: PassportElementType

object PassportElementTypeDriverLicense: PassportElementType

object PassportElementTypeEmailAddress: PassportElementType

object PassportElementTypeIdentityCard: PassportElementType

object PassportElementTypeInternalPassport: PassportElementType

object PassportElementTypePassport: PassportElementType

object PassportElementTypePassportRegistration: PassportElementType

object PassportElementTypePersonalDetails: PassportElementType

object PassportElementTypePhoneNumber: PassportElementType

object PassportElementTypeRentalAgreement: PassportElementType

object PassportElementTypeTemporaryRegistration: PassportElementType

object PassportElementTypeUtilityBill: PassportElementType

data class Photo(
    val hasStickers: Boolean,
    val minithumbnail: Minithumbnail? = null,
    val sizes: List<PhotoSize>
)

data class PhotoSize(
    val type: String,
    val photo: File,
    val width: Int,
    val height: Int,
    val progressiveSizes: IntArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PhotoSize

        if (width != other.width) return false
        if (height != other.height) return false
        if (type != other.type) return false
        if (photo != other.photo) return false
        if (!progressiveSizes.contentEquals(other.progressiveSizes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        result = 31 * result + type.hashCode()
        result = 31 * result + photo.hashCode()
        result = 31 * result + progressiveSizes.contentHashCode()
        return result
    }
}

data class Poll(
    val id: Long,
    val question: FormattedText,
    val options: List<PollOption>,
    val totalVoterCount: Int,
    val recentVoterIds: List<MessageSender>,
    val isAnonymous: Boolean,
    val type: PollType,
    val openPeriod: Int,
    val closeDate: Int,
    val isClosed: Boolean
)

data class PollOption(
    val text: FormattedText,
    val voterCount: Int,
    val votePercentage: Int,
    val isChosen: Boolean,
    val isBeingChosen: Boolean
)

sealed interface PollType

data class PollTypeQuiz(
    val correctOptionId: Int,
    val explanation: FormattedText
): PollType

data class PollTypeRegular(
    val allowMultipleAnswers: Boolean
): PollType

data class ProductInfo(
    val title: String,
    val description: FormattedText,
    val photo: Photo? = null
)

data class RemoteFile(
    val id: String,
    val uniqueId: String,
    val isUploadingActive: Boolean,
    val isUploadingCompleted: Boolean,
    val uploadedSize: Long
)

data class SharedChat(
    val chatId: Long,
    val title: String,
    val username: String,
    val photo: Photo? = null
)

data class SharedUser(
    val userId: Long,
    val firstName: String,
    val lastName: String,
    val username: String,
    val photo: Photo? = null
)

sealed interface SpeechRecognitionResult

data class SpeechRecognitionResultError(
    val error: Error
): SpeechRecognitionResult

data class SpeechRecognitionResultPending(
    val partialText: String
): SpeechRecognitionResult

data class SpeechRecognitionResultText(
    val text: String
): SpeechRecognitionResult

data class StarAmount(
    val starCount: Long,
    val nanostarCount: Int
)

data class Sticker(
    val id: Long,
    val setId: Long,
    val width: Int,
    val height: Int,
    val emoji: String,
    val format: StickerFormat,
    val fullType: StickerFullType,
    val thumbnail: Thumbnail? = null,
    val sticker: File
)

sealed interface StickerFormat

object StickerFormatTgs: StickerFormat

object StickerFormatWebm: StickerFormat

object StickerFormatWebp: StickerFormat

sealed interface StickerFullType

data class StickerFullTypeCustomEmoji(
    val customEmojiId: Long,
    val needsRepainting: Boolean
): StickerFullType

data class StickerFullTypeMask(
    val maskPosition: MaskPosition? = null
): StickerFullType

data class StickerFullTypeRegular(
    val premiumAnimation: File? = null
): StickerFullType

sealed interface SuggestedPostPrice

data class SuggestedPostPriceStar(
    val starCount: Long
): SuggestedPostPrice

data class SuggestedPostPriceTon(
    val toncoinCentCount: Long
): SuggestedPostPrice

sealed interface SuggestedPostRefundReason

object SuggestedPostRefundReasonPaymentRefunded: SuggestedPostRefundReason

object SuggestedPostRefundReasonPostDeleted: SuggestedPostRefundReason

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

data class ThemeSettings(
    val baseTheme: BuiltInTheme,
    val accentColor: Int,
    val background: Background? = null,
    val outgoingMessageFill: BackgroundFill? = null,
    val animateOutgoingMessageFill: Boolean,
    val outgoingMessageAccentColor: Int
)

data class Thumbnail(
    val format: ThumbnailFormat,
    val width: Int,
    val height: Int,
    val file: File
)

sealed interface ThumbnailFormat

object ThumbnailFormatGif: ThumbnailFormat

object ThumbnailFormatJpeg: ThumbnailFormat

object ThumbnailFormatMpeg4: ThumbnailFormat

object ThumbnailFormatPng: ThumbnailFormat

object ThumbnailFormatTgs: ThumbnailFormat

object ThumbnailFormatWebm: ThumbnailFormat

object ThumbnailFormatWebp: ThumbnailFormat

data class UpgradedGift(
    val id: Long,
    val regularGiftId: Long,
    val publisherChatId: Long,
    val title: String,
    val name: String,
    val number: Int,
    val totalUpgradedCount: Int,
    val maxUpgradedCount: Int,
    val isPremium: Boolean,
    val isThemeAvailable: Boolean,
    val usedThemeChatId: Long,
    val ownerId: MessageSender? = null,
    val ownerAddress: String,
    val ownerName: String,
    val giftAddress: String,
    val model: UpgradedGiftModel,
    val symbol: UpgradedGiftSymbol,
    val backdrop: UpgradedGiftBackdrop,
    val originalDetails: UpgradedGiftOriginalDetails? = null,
    val resaleParameters: GiftResaleParameters? = null,
    val valueCurrency: String,
    val valueAmount: Long
)

data class UpgradedGiftBackdrop(
    val id: Int,
    val name: String,
    val colors: UpgradedGiftBackdropColors,
    val rarityPerMille: Int
)

data class UpgradedGiftBackdropColors(
    val centerColor: Int,
    val edgeColor: Int,
    val symbolColor: Int,
    val textColor: Int
)

data class UpgradedGiftModel(
    val name: String,
    val sticker: Sticker,
    val rarityPerMille: Int
)

sealed interface UpgradedGiftOrigin

object UpgradedGiftOriginPrepaidUpgrade: UpgradedGiftOrigin

data class UpgradedGiftOriginResale(
    val price: GiftResalePrice
): UpgradedGiftOrigin

object UpgradedGiftOriginTransfer: UpgradedGiftOrigin

data class UpgradedGiftOriginUpgrade(
    val giftMessageId: Long
): UpgradedGiftOrigin

data class UpgradedGiftOriginalDetails(
    val senderId: MessageSender? = null,
    val receiverId: MessageSender,
    val text: FormattedText,
    val date: Int
)

data class UpgradedGiftSymbol(
    val name: String,
    val sticker: Sticker,
    val rarityPerMille: Int
)

data class Venue(
    val location: Location,
    val title: String,
    val address: String,
    val provider: String,
    val id: String,
    val type: String
)

data class Video(
    val duration: Int,
    val width: Int,
    val height: Int,
    val fileName: String,
    val mimeType: String,
    val hasStickers: Boolean,
    val supportsStreaming: Boolean,
    val minithumbnail: Minithumbnail? = null,
    val thumbnail: Thumbnail? = null,
    val video: File
)

data class VideoNote(
    val duration: Int,
    val waveform: ByteArray,
    val length: Int,
    val minithumbnail: Minithumbnail? = null,
    val thumbnail: Thumbnail? = null,
    val speechRecognitionResult: SpeechRecognitionResult? = null,
    val video: File
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VideoNote

        if (duration != other.duration) return false
        if (length != other.length) return false
        if (!waveform.contentEquals(other.waveform)) return false
        if (minithumbnail != other.minithumbnail) return false
        if (thumbnail != other.thumbnail) return false
        if (speechRecognitionResult != other.speechRecognitionResult) return false
        if (video != other.video) return false

        return true
    }

    override fun hashCode(): Int {
        var result = duration
        result = 31 * result + length
        result = 31 * result + waveform.contentHashCode()
        result = 31 * result + (minithumbnail?.hashCode() ?: 0)
        result = 31 * result + (thumbnail?.hashCode() ?: 0)
        result = 31 * result + (speechRecognitionResult?.hashCode() ?: 0)
        result = 31 * result + video.hashCode()
        return result
    }
}

data class VideoStoryboard(
    val storyboardFile: File,
    val width: Int,
    val height: Int,
    val mapFile: File
)

data class VoiceNote(
    val duration: Int,
    val waveform: ByteArray,
    val mimeType: String,
    val speechRecognitionResult: SpeechRecognitionResult? = null,
    val voice: File
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VoiceNote

        if (duration != other.duration) return false
        if (!waveform.contentEquals(other.waveform)) return false
        if (mimeType != other.mimeType) return false
        if (speechRecognitionResult != other.speechRecognitionResult) return false
        if (voice != other.voice) return false

        return true
    }

    override fun hashCode(): Int {
        var result = duration
        result = 31 * result + waveform.contentHashCode()
        result = 31 * result + mimeType.hashCode()
        result = 31 * result + (speechRecognitionResult?.hashCode() ?: 0)
        result = 31 * result + voice.hashCode()
        return result
    }
}

data class WebApp(
    val shortName: String,
    val title: String,
    val description: String,
    val photo: Photo,
    val animation: Animation? = null
)