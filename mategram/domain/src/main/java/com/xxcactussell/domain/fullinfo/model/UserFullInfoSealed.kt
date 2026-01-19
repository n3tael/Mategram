package com.xxcactussell.domain.fullinfo.model

// Domain Models (Pure Kotlin)

data class AcceptedGiftTypes(
    val unlimitedGifts: Boolean,
    val limitedGifts: Boolean,
    val upgradedGifts: Boolean,
    val premiumSubscription: Boolean
)

data class AffiliateProgramInfo(
    val parameters: AffiliateProgramParameters,
    val endDate: Int,
    val dailyRevenuePerUserAmount: StarAmount
)

data class AffiliateProgramParameters(
    val commissionPerMille: Int,
    val monthCount: Int
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

data class Birthdate(
    val day: Int,
    val month: Int,
    val year: Int
)

sealed interface BlockList 

object BlockListMain: BlockList

object BlockListStories: BlockList

data class BotInfo(
    val shortDescription: String,
    val description: String,
    val photo: Photo? = null,
    val animation: Animation? = null,
    val menuButton: BotMenuButton? = null,
    val commands: List<BotCommand>,
    val privacyPolicyUrl: String,
    val defaultGroupAdministratorRights: ChatAdministratorRights? = null,
    val defaultChannelAdministratorRights: ChatAdministratorRights? = null,
    val affiliateProgram: AffiliateProgramInfo? = null,
    val webAppBackgroundLightColor: Int,
    val webAppBackgroundDarkColor: Int,
    val webAppHeaderLightColor: Int,
    val webAppHeaderDarkColor: Int,
    val verificationParameters: BotVerificationParameters? = null,
    val canGetRevenueStatistics: Boolean,
    val canManageEmojiStatus: Boolean,
    val hasMediaPreviews: Boolean,
    val editCommandsLink: InternalLinkType? = null,
    val editDescriptionLink: InternalLinkType? = null,
    val editDescriptionMediaLink: InternalLinkType? = null,
    val editSettingsLink: InternalLinkType? = null
)

data class BotMenuButton(
    val text: String,
    val url: String
)

data class BotVerificationParameters(
    val iconCustomEmojiId: Long,
    val organizationName: String,
    val defaultCustomDescription: FormattedText? = null,
    val canSetCustomDescription: Boolean
)

sealed interface BusinessAwayMessageSchedule 

object BusinessAwayMessageScheduleAlways: BusinessAwayMessageSchedule

data class BusinessAwayMessageScheduleCustom(
    val startDate: Int,
    val endDate: Int
): BusinessAwayMessageSchedule

object BusinessAwayMessageScheduleOutsideOfOpeningHours: BusinessAwayMessageSchedule

data class BusinessAwayMessageSettings(
    val shortcutId: Int,
    val recipients: BusinessRecipients,
    val schedule: BusinessAwayMessageSchedule,
    val offlineOnly: Boolean
)

data class BusinessGreetingMessageSettings(
    val shortcutId: Int,
    val recipients: BusinessRecipients,
    val inactivityDays: Int
)

data class BusinessInfo(
    val location: BusinessLocation? = null,
    val openingHours: BusinessOpeningHours? = null,
    val localOpeningHours: BusinessOpeningHours? = null,
    val nextOpenIn: Int,
    val nextCloseIn: Int,
    val greetingMessageSettings: BusinessGreetingMessageSettings? = null,
    val awayMessageSettings: BusinessAwayMessageSettings? = null,
    val startPage: BusinessStartPage? = null
)

data class BusinessLocation(
    val location: Location? = null,
    val address: String
)

data class BusinessOpeningHours(
    val timeZoneId: String,
    val openingHours: List<BusinessOpeningHoursInterval>
)

data class BusinessOpeningHoursInterval(
    val startMinute: Int,
    val endMinute: Int
)

data class BusinessRecipients(
    val chatIds: LongArray,
    val excludedChatIds: LongArray,
    val selectExistingChats: Boolean,
    val selectNewChats: Boolean,
    val selectContacts: Boolean,
    val selectNonContacts: Boolean,
    val excludeSelected: Boolean
)

data class BusinessStartPage(
    val title: String,
    val message: String,
    val sticker: Sticker? = null
)

data class GiftSettings(
    val showGiftButton: Boolean,
    val acceptedGiftTypes: AcceptedGiftTypes
)

sealed interface InternalLinkType 

object InternalLinkTypeActiveSessions: InternalLinkType

data class InternalLinkTypeAttachmentMenuBot(
    val targetChat: TargetChat,
    val botUsername: String,
    val url: String
): InternalLinkType

data class InternalLinkTypeAuthenticationCode(
    val code: String
): InternalLinkType

data class InternalLinkTypeBackground(
    val backgroundName: String
): InternalLinkType

data class InternalLinkTypeBotAddToChannel(
    val botUsername: String,
    val administratorRights: ChatAdministratorRights
): InternalLinkType

data class InternalLinkTypeBotStart(
    val botUsername: String,
    val startParameter: String,
    val autostart: Boolean
): InternalLinkType

data class InternalLinkTypeBotStartInGroup(
    val botUsername: String,
    val startParameter: String,
    val administratorRights: ChatAdministratorRights? = null
): InternalLinkType

data class InternalLinkTypeBusinessChat(
    val linkName: String
): InternalLinkType

data class InternalLinkTypeBuyStars(
    val starCount: Long,
    val purpose: String
): InternalLinkType

object InternalLinkTypeChangePhoneNumber: InternalLinkType

data class InternalLinkTypeChatAffiliateProgram(
    val username: String,
    val referrer: String
): InternalLinkType

data class InternalLinkTypeChatBoost(
    val url: String
): InternalLinkType

data class InternalLinkTypeChatFolderInvite(
    val inviteLink: String
): InternalLinkType

object InternalLinkTypeChatFolderSettings: InternalLinkType

data class InternalLinkTypeChatInvite(
    val inviteLink: String
): InternalLinkType

object InternalLinkTypeDefaultMessageAutoDeleteTimerSettings: InternalLinkType

data class InternalLinkTypeDirectMessagesChat(
    val channelUsername: String
): InternalLinkType

object InternalLinkTypeEditProfileSettings: InternalLinkType

data class InternalLinkTypeGame(
    val botUsername: String,
    val gameShortName: String
): InternalLinkType

data class InternalLinkTypeGiftCollection(
    val giftOwnerUsername: String,
    val collectionId: Int
): InternalLinkType

data class InternalLinkTypeGroupCall(
    val inviteLink: String
): InternalLinkType

data class InternalLinkTypeInstantView(
    val url: String,
    val fallbackUrl: String
): InternalLinkType

data class InternalLinkTypeInvoice(
    val invoiceName: String
): InternalLinkType

data class InternalLinkTypeLanguagePack(
    val languagePackId: String
): InternalLinkType

object InternalLinkTypeLanguageSettings: InternalLinkType

data class InternalLinkTypeMainWebApp(
    val botUsername: String,
    val startParameter: String,
    val mode: WebAppOpenMode
): InternalLinkType

data class InternalLinkTypeMessage(
    val url: String
): InternalLinkType

data class InternalLinkTypeMessageDraft(
    val text: FormattedText,
    val containsLink: Boolean
): InternalLinkType

object InternalLinkTypeMyStars: InternalLinkType

object InternalLinkTypeMyToncoins: InternalLinkType

data class InternalLinkTypePassportDataRequest(
    val botUserId: Long,
    val scope: String,
    val publicKey: String,
    val nonce: String,
    val callbackUrl: String
): InternalLinkType

data class InternalLinkTypePhoneNumberConfirmation(
    val hash: String,
    val phoneNumber: String
): InternalLinkType

data class InternalLinkTypePremiumFeatures(
    val referrer: String
): InternalLinkType

data class InternalLinkTypePremiumGift(
    val referrer: String
): InternalLinkType

data class InternalLinkTypePremiumGiftCode(
    val code: String
): InternalLinkType

object InternalLinkTypePrivacyAndSecuritySettings: InternalLinkType

data class InternalLinkTypeProxy(
    val server: String,
    val port: Int,
    val type: ProxyType
): InternalLinkType

data class InternalLinkTypePublicChat(
    val chatUsername: String,
    val draftText: String,
    val openProfile: Boolean
): InternalLinkType

object InternalLinkTypeQrCodeAuthentication: InternalLinkType

object InternalLinkTypeRestorePurchases: InternalLinkType

object InternalLinkTypeSettings: InternalLinkType

data class InternalLinkTypeStickerSet(
    val stickerSetName: String,
    val expectCustomEmoji: Boolean
): InternalLinkType

data class InternalLinkTypeStory(
    val storyPosterUsername: String,
    val storyId: Int
): InternalLinkType

data class InternalLinkTypeStoryAlbum(
    val storyAlbumOwnerUsername: String,
    val storyAlbumId: Int
): InternalLinkType

data class InternalLinkTypeTheme(
    val themeName: String
): InternalLinkType

object InternalLinkTypeThemeSettings: InternalLinkType

data class InternalLinkTypeUnknownDeepLink(
    val link: String
): InternalLinkType

object InternalLinkTypeUnsupportedProxy: InternalLinkType

data class InternalLinkTypeUpgradedGift(
    val name: String
): InternalLinkType

data class InternalLinkTypeUserPhoneNumber(
    val phoneNumber: String,
    val draftText: String,
    val openProfile: Boolean
): InternalLinkType

data class InternalLinkTypeUserToken(
    val token: String
): InternalLinkType

data class InternalLinkTypeVideoChat(
    val chatUsername: String,
    val inviteHash: String,
    val isLiveStream: Boolean
): InternalLinkType

data class InternalLinkTypeWebApp(
    val botUsername: String,
    val webAppShortName: String,
    val startParameter: String,
    val mode: WebAppOpenMode
): InternalLinkType

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

data class Photo(
    val hasStickers: Boolean,
    val minithumbnail: Minithumbnail? = null,
    val sizes: List<PhotoSize>
)

sealed interface ProxyType 

data class ProxyTypeHttp(
    val username: String,
    val password: String,
    val httpOnly: Boolean
): ProxyType

data class ProxyTypeMtproto(
    val secret: String
): ProxyType

data class ProxyTypeSocks5(
    val username: String,
    val password: String
): ProxyType

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

sealed interface TargetChat 

data class TargetChatChosen(
    val types: TargetChatTypes
): TargetChat

object TargetChatCurrent: TargetChat

data class TargetChatInternalLink(
    val link: InternalLinkType
): TargetChat

data class TargetChatTypes(
    val allowUserChats: Boolean,
    val allowBotChats: Boolean,
    val allowGroupChats: Boolean,
    val allowChannelChats: Boolean
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

data class UserFullInfo(
    val personalPhoto: ChatPhoto? = null,
    val photo: ChatPhoto? = null,
    val publicPhoto: ChatPhoto? = null,
    val blockList: BlockList? = null,
    val canBeCalled: Boolean,
    val supportsVideoCalls: Boolean,
    val hasPrivateCalls: Boolean,
    val hasPrivateForwards: Boolean,
    val hasRestrictedVoiceAndVideoNoteMessages: Boolean,
    val hasPostedToProfileStories: Boolean,
    val hasSponsoredMessagesEnabled: Boolean,
    val needPhoneNumberPrivacyException: Boolean,
    val setChatBackground: Boolean,
    val bio: FormattedText? = null,
    val birthdate: Birthdate? = null,
    val personalChatId: Long,
    val giftCount: Int,
    val groupInCommonCount: Int,
    val incomingPaidMessageStarCount: Long,
    val outgoingPaidMessageStarCount: Long,
    val giftSettings: GiftSettings,
    val botVerification: BotVerification? = null,
    val mainProfileTab: ProfileTab? = null,
    val firstProfileAudio: Audio? = null,
    val rating: UserRating? = null,
    val pendingRating: UserRating? = null,
    val pendingRatingDate: Int,
    val businessInfo: BusinessInfo? = null,
    val botInfo: BotInfo? = null
)

data class UserRating(
    val level: Int,
    val isMaximumLevelReached: Boolean,
    val rating: Long,
    val currentLevelRating: Long,
    val nextLevelRating: Long
)

sealed interface WebAppOpenMode 

object WebAppOpenModeCompact: WebAppOpenMode

object WebAppOpenModeFullScreen: WebAppOpenMode

object WebAppOpenModeFullSize: WebAppOpenMode

