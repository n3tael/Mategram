package com.xxcactussell.data.utils.todata

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.messages.model.*

// Mappers: Domain -> TdApi

fun Address.toData(): TdApi.Address = TdApi.Address(
    this.countryCode,
    this.state,
    this.city,
    this.streetLine1,
    this.streetLine2,
    this.postalCode
)

fun AlternativeVideo.toData(): TdApi.AlternativeVideo = TdApi.AlternativeVideo(
    this.id,
    this.width,
    this.height,
    this.codec,
    this.hlsFile.toData(),
    this.video.toData()
)

fun AnimatedChatPhoto.toData(): TdApi.AnimatedChatPhoto = TdApi.AnimatedChatPhoto(
    this.length,
    this.file.toData(),
    this.mainFrameTimestamp
)

fun AnimatedEmoji.toData(): TdApi.AnimatedEmoji = TdApi.AnimatedEmoji(
    this.sticker?.toData(),
    this.stickerWidth,
    this.stickerHeight,
    this.fitzpatrickType,
    this.sound?.toData()
)

fun Animation.toData(): TdApi.Animation = TdApi.Animation(
    this.duration,
    this.width,
    this.height,
    this.fileName,
    this.mimeType,
    this.hasStickers,
    this.minithumbnail?.toData(),
    this.thumbnail?.toData(),
    this.animation.toData()
)

fun Audio.toData(): TdApi.Audio = TdApi.Audio(
    this.duration,
    this.title,
    this.performer,
    this.fileName,
    this.mimeType,
    this.albumCoverMinithumbnail?.toData(),
    this.albumCoverThumbnail?.toData(),
    this.externalAlbumCovers.map { it.toData() }.toTypedArray(),
    this.audio.toData()
)

fun Background.toData(): TdApi.Background = TdApi.Background(
    this.id,
    this.isDefault,
    this.isDark,
    this.name,
    this.document?.toData(),
    this.type.toData()
)

fun BackgroundFill.toData(): TdApi.BackgroundFill = when(this) {
    is BackgroundFillSolid -> this.toData()
    is BackgroundFillGradient -> this.toData()
    is BackgroundFillFreeformGradient -> this.toData()
}

fun BackgroundFillFreeformGradient.toData(): TdApi.BackgroundFillFreeformGradient = TdApi.BackgroundFillFreeformGradient(
    this.colors
)

fun BackgroundFillGradient.toData(): TdApi.BackgroundFillGradient = TdApi.BackgroundFillGradient(
    this.topColor,
    this.bottomColor,
    this.rotationAngle
)

fun BackgroundFillSolid.toData(): TdApi.BackgroundFillSolid = TdApi.BackgroundFillSolid(
    this.color
)

fun BackgroundType.toData(): TdApi.BackgroundType = when(this) {
    is BackgroundTypeWallpaper -> this.toData()
    is BackgroundTypePattern -> this.toData()
    is BackgroundTypeFill -> this.toData()
    is BackgroundTypeChatTheme -> this.toData()
}

fun BackgroundTypeChatTheme.toData(): TdApi.BackgroundTypeChatTheme = TdApi.BackgroundTypeChatTheme(
    this.themeName
)

fun BackgroundTypeFill.toData(): TdApi.BackgroundTypeFill = TdApi.BackgroundTypeFill(
    this.fill.toData()
)

fun BackgroundTypePattern.toData(): TdApi.BackgroundTypePattern = TdApi.BackgroundTypePattern(
    this.fill.toData(),
    this.intensity,
    this.isInverted,
    this.isMoving
)

fun BackgroundTypeWallpaper.toData(): TdApi.BackgroundTypeWallpaper = TdApi.BackgroundTypeWallpaper(
    this.isBlurred,
    this.isMoving
)

fun BotWriteAccessAllowReason.toData(): TdApi.BotWriteAccessAllowReason = when(this) {
    is BotWriteAccessAllowReasonConnectedWebsite -> this.toData()
    is BotWriteAccessAllowReasonAddedToAttachmentMenu -> this.toData()
    is BotWriteAccessAllowReasonLaunchedWebApp -> this.toData()
    is BotWriteAccessAllowReasonAcceptedRequest -> this.toData()
}

fun BotWriteAccessAllowReasonAcceptedRequest.toData(): TdApi.BotWriteAccessAllowReasonAcceptedRequest = TdApi.BotWriteAccessAllowReasonAcceptedRequest(
)

fun BotWriteAccessAllowReasonAddedToAttachmentMenu.toData(): TdApi.BotWriteAccessAllowReasonAddedToAttachmentMenu = TdApi.BotWriteAccessAllowReasonAddedToAttachmentMenu(
)

fun BotWriteAccessAllowReasonConnectedWebsite.toData(): TdApi.BotWriteAccessAllowReasonConnectedWebsite = TdApi.BotWriteAccessAllowReasonConnectedWebsite(
    this.domainName
)

fun BotWriteAccessAllowReasonLaunchedWebApp.toData(): TdApi.BotWriteAccessAllowReasonLaunchedWebApp = TdApi.BotWriteAccessAllowReasonLaunchedWebApp(
    this.webApp.toData()
)

fun BuiltInTheme.toData(): TdApi.BuiltInTheme = when(this) {
    is BuiltInThemeClassic -> this.toData()
    is BuiltInThemeDay -> this.toData()
    is BuiltInThemeNight -> this.toData()
    is BuiltInThemeTinted -> this.toData()
    is BuiltInThemeArctic -> this.toData()
}

fun BuiltInThemeArctic.toData(): TdApi.BuiltInThemeArctic = TdApi.BuiltInThemeArctic(
)

fun BuiltInThemeClassic.toData(): TdApi.BuiltInThemeClassic = TdApi.BuiltInThemeClassic(
)

fun BuiltInThemeDay.toData(): TdApi.BuiltInThemeDay = TdApi.BuiltInThemeDay(
)

fun BuiltInThemeNight.toData(): TdApi.BuiltInThemeNight = TdApi.BuiltInThemeNight(
)

fun BuiltInThemeTinted.toData(): TdApi.BuiltInThemeTinted = TdApi.BuiltInThemeTinted(
)

fun CallDiscardReason.toData(): TdApi.CallDiscardReason = when(this) {
    is CallDiscardReasonEmpty -> this.toData()
    is CallDiscardReasonMissed -> this.toData()
    is CallDiscardReasonDeclined -> this.toData()
    is CallDiscardReasonDisconnected -> this.toData()
    is CallDiscardReasonHungUp -> this.toData()
    is CallDiscardReasonUpgradeToGroupCall -> this.toData()
}

fun CallDiscardReasonDeclined.toData(): TdApi.CallDiscardReasonDeclined = TdApi.CallDiscardReasonDeclined(
)

fun CallDiscardReasonDisconnected.toData(): TdApi.CallDiscardReasonDisconnected = TdApi.CallDiscardReasonDisconnected(
)

fun CallDiscardReasonEmpty.toData(): TdApi.CallDiscardReasonEmpty = TdApi.CallDiscardReasonEmpty(
)

fun CallDiscardReasonHungUp.toData(): TdApi.CallDiscardReasonHungUp = TdApi.CallDiscardReasonHungUp(
)

fun CallDiscardReasonMissed.toData(): TdApi.CallDiscardReasonMissed = TdApi.CallDiscardReasonMissed(
)

fun CallDiscardReasonUpgradeToGroupCall.toData(): TdApi.CallDiscardReasonUpgradeToGroupCall = TdApi.CallDiscardReasonUpgradeToGroupCall(
    this.inviteLink
)

fun ChatBackground.toData(): TdApi.ChatBackground = TdApi.ChatBackground(
    this.background.toData(),
    this.darkThemeDimming
)

fun ChatPhoto.toData(): TdApi.ChatPhoto = TdApi.ChatPhoto(
    this.id,
    this.addedDate,
    this.minithumbnail?.toData(),
    this.sizes.map { it.toData() }.toTypedArray(),
    this.animation?.toData(),
    this.smallAnimation?.toData(),
    this.sticker?.toData()
)

fun ChatPhotoSticker.toData(): TdApi.ChatPhotoSticker = TdApi.ChatPhotoSticker(
    this.type.toData(),
    this.backgroundFill.toData()
)

fun ChatPhotoStickerType.toData(): TdApi.ChatPhotoStickerType = when(this) {
    is ChatPhotoStickerTypeRegularOrMask -> this.toData()
    is ChatPhotoStickerTypeCustomEmoji -> this.toData()
}

fun ChatPhotoStickerTypeCustomEmoji.toData(): TdApi.ChatPhotoStickerTypeCustomEmoji = TdApi.ChatPhotoStickerTypeCustomEmoji(
    this.customEmojiId
)

fun ChatPhotoStickerTypeRegularOrMask.toData(): TdApi.ChatPhotoStickerTypeRegularOrMask = TdApi.ChatPhotoStickerTypeRegularOrMask(
    this.stickerSetId,
    this.stickerId
)

fun ChatTheme.toData(): TdApi.ChatTheme = when(this) {
    is ChatThemeEmoji -> this.toData()
    is ChatThemeGift -> this.toData()
}

fun ChatThemeEmoji.toData(): TdApi.ChatThemeEmoji = TdApi.ChatThemeEmoji(
    this.name
)

fun ChatThemeGift.toData(): TdApi.ChatThemeGift = TdApi.ChatThemeGift(
    this.giftTheme.toData()
)

fun Checklist.toData(): TdApi.Checklist = TdApi.Checklist(
    this.title.toData(),
    this.tasks.map { it.toData() }.toTypedArray(),
    this.othersCanAddTasks,
    this.canAddTasks,
    this.othersCanMarkTasksAsDone,
    this.canMarkTasksAsDone
)

fun ChecklistTask.toData(): TdApi.ChecklistTask = TdApi.ChecklistTask(
    this.id,
    this.text.toData(),
    this.completedByUserId,
    this.completionDate
)

fun Contact.toData(): TdApi.Contact = TdApi.Contact(
    this.phoneNumber,
    this.firstName,
    this.lastName,
    this.vcard,
    this.userId
)

fun DatedFile.toData(): TdApi.DatedFile = TdApi.DatedFile(
    this.file.toData(),
    this.date
)

fun DiceStickers.toData(): TdApi.DiceStickers = when(this) {
    is DiceStickersRegular -> this.toData()
    is DiceStickersSlotMachine -> this.toData()
}

fun DiceStickersRegular.toData(): TdApi.DiceStickersRegular = TdApi.DiceStickersRegular(
    this.sticker.toData()
)

fun DiceStickersSlotMachine.toData(): TdApi.DiceStickersSlotMachine = TdApi.DiceStickersSlotMachine(
    this.background.toData(),
    this.lever.toData(),
    this.leftReel.toData(),
    this.centerReel.toData(),
    this.rightReel.toData()
)

fun Document.toData(): TdApi.Document = TdApi.Document(
    this.fileName,
    this.mimeType,
    this.minithumbnail?.toData(),
    this.thumbnail?.toData(),
    this.document.toData()
)

fun EncryptedCredentials.toData(): TdApi.EncryptedCredentials = TdApi.EncryptedCredentials(
    this.data,
    this.hash,
    this.secret
)

fun EncryptedPassportElement.toData(): TdApi.EncryptedPassportElement = TdApi.EncryptedPassportElement(
    this.type.toData(),
    this.data,
    this.frontSide.toData(),
    this.reverseSide?.toData(),
    this.selfie?.toData(),
    this.translation.map { it.toData() }.toTypedArray(),
    this.files.map { it.toData() }.toTypedArray(),
    this.value,
    this.hash
)

fun Error.toData(): TdApi.Error = TdApi.Error(
    this.code,
    this.message
)

fun File.toData(): TdApi.File = TdApi.File(
    this.id,
    this.size,
    this.expectedSize,
    this.local.toData(),
    this.remote.toData()
)

fun FormattedText.toData(): TdApi.FormattedText = TdApi.FormattedText(
    this.text,
    this.entities.map { it.toData() }.toTypedArray()
)

fun ForumTopicIcon.toData(): TdApi.ForumTopicIcon = TdApi.ForumTopicIcon(
    this.color,
    this.customEmojiId
)

fun Game.toData(): TdApi.Game = TdApi.Game(
    this.id,
    this.shortName,
    this.title,
    this.text.toData(),
    this.description,
    this.photo.toData(),
    this.animation?.toData()
)

fun Gift.toData(): TdApi.Gift = TdApi.Gift(
    this.id,
    this.publisherChatId,
    this.sticker.toData(),
    this.starCount,
    this.defaultSellStarCount,
    this.upgradeStarCount,
    this.isForBirthday,
    this.isPremium,
    this.nextSendDate,
    this.userLimits?.toData(),
    this.overallLimits?.toData(),
    this.firstSendDate,
    this.lastSendDate
)

fun GiftChatTheme.toData(): TdApi.GiftChatTheme = TdApi.GiftChatTheme(
    this.gift.toData(),
    this.lightSettings.toData(),
    this.darkSettings.toData()
)

fun GiftPurchaseLimits.toData(): TdApi.GiftPurchaseLimits = TdApi.GiftPurchaseLimits(
    this.totalCount,
    this.remainingCount
)

fun GiftResaleParameters.toData(): TdApi.GiftResaleParameters = TdApi.GiftResaleParameters(
    this.starCount,
    this.toncoinCentCount,
    this.toncoinOnly
)

fun GiftResalePrice.toData(): TdApi.GiftResalePrice = when(this) {
    is GiftResalePriceStar -> this.toData()
    is GiftResalePriceTon -> this.toData()
}

fun GiftResalePriceStar.toData(): TdApi.GiftResalePriceStar = TdApi.GiftResalePriceStar(
    this.starCount
)

fun GiftResalePriceTon.toData(): TdApi.GiftResalePriceTon = TdApi.GiftResalePriceTon(
    this.toncoinCentCount
)

fun GiveawayParameters.toData(): TdApi.GiveawayParameters = TdApi.GiveawayParameters(
    this.boostedChatId,
    this.additionalChatIds,
    this.winnersSelectionDate,
    this.onlyNewMembers,
    this.hasPublicWinners,
    this.countryCodes.toTypedArray(),
    this.prizeDescription
)

fun GiveawayPrize.toData(): TdApi.GiveawayPrize = when(this) {
    is GiveawayPrizePremium -> this.toData()
    is GiveawayPrizeStars -> this.toData()
}

fun GiveawayPrizePremium.toData(): TdApi.GiveawayPrizePremium = TdApi.GiveawayPrizePremium(
    this.monthCount
)

fun GiveawayPrizeStars.toData(): TdApi.GiveawayPrizeStars = TdApi.GiveawayPrizeStars(
    this.starCount
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

fun LinkPreview.toData(): TdApi.LinkPreview = TdApi.LinkPreview(
    this.url,
    this.displayUrl,
    this.siteName,
    this.title,
    this.description.toData(),
    this.author,
    this.type.toData(),
    this.hasLargeMedia,
    this.showLargeMedia,
    this.showMediaAboveDescription,
    this.skipConfirmation,
    this.showAboveText,
    this.instantViewVersion
)

fun LinkPreviewAlbumMedia.toData(): TdApi.LinkPreviewAlbumMedia = when(this) {
    is LinkPreviewAlbumMediaPhoto -> this.toData()
    is LinkPreviewAlbumMediaVideo -> this.toData()
}

fun LinkPreviewAlbumMediaPhoto.toData(): TdApi.LinkPreviewAlbumMediaPhoto = TdApi.LinkPreviewAlbumMediaPhoto(
    this.photo.toData()
)

fun LinkPreviewAlbumMediaVideo.toData(): TdApi.LinkPreviewAlbumMediaVideo = TdApi.LinkPreviewAlbumMediaVideo(
    this.video.toData()
)

fun LinkPreviewOptions.toData(): TdApi.LinkPreviewOptions = TdApi.LinkPreviewOptions(
    this.isDisabled,
    this.url,
    this.forceSmallMedia,
    this.forceLargeMedia,
    this.showAboveText
)

fun LinkPreviewType.toData(): TdApi.LinkPreviewType = when(this) {
    is LinkPreviewTypeAlbum -> this.toData()
    is LinkPreviewTypeAnimation -> this.toData()
    is LinkPreviewTypeApp -> this.toData()
    is LinkPreviewTypeArticle -> this.toData()
    is LinkPreviewTypeAudio -> this.toData()
    is LinkPreviewTypeBackground -> this.toData()
    is LinkPreviewTypeChannelBoost -> this.toData()
    is LinkPreviewTypeChat -> this.toData()
    is LinkPreviewTypeDirectMessagesChat -> this.toData()
    is LinkPreviewTypeDocument -> this.toData()
    is LinkPreviewTypeEmbeddedAnimationPlayer -> this.toData()
    is LinkPreviewTypeEmbeddedAudioPlayer -> this.toData()
    is LinkPreviewTypeEmbeddedVideoPlayer -> this.toData()
    is LinkPreviewTypeExternalAudio -> this.toData()
    is LinkPreviewTypeExternalVideo -> this.toData()
    is LinkPreviewTypeGiftCollection -> this.toData()
    is LinkPreviewTypeGroupCall -> this.toData()
    is LinkPreviewTypeInvoice -> this.toData()
    is LinkPreviewTypeMessage -> this.toData()
    is LinkPreviewTypePhoto -> this.toData()
    is LinkPreviewTypePremiumGiftCode -> this.toData()
    is LinkPreviewTypeShareableChatFolder -> this.toData()
    is LinkPreviewTypeSticker -> this.toData()
    is LinkPreviewTypeStickerSet -> this.toData()
    is LinkPreviewTypeStory -> this.toData()
    is LinkPreviewTypeStoryAlbum -> this.toData()
    is LinkPreviewTypeSupergroupBoost -> this.toData()
    is LinkPreviewTypeTheme -> this.toData()
    is LinkPreviewTypeUnsupported -> this.toData()
    is LinkPreviewTypeUpgradedGift -> this.toData()
    is LinkPreviewTypeUser -> this.toData()
    is LinkPreviewTypeVideo -> this.toData()
    is LinkPreviewTypeVideoChat -> this.toData()
    is LinkPreviewTypeVideoNote -> this.toData()
    is LinkPreviewTypeVoiceNote -> this.toData()
    is LinkPreviewTypeWebApp -> this.toData()
}

fun LinkPreviewTypeAlbum.toData(): TdApi.LinkPreviewTypeAlbum = TdApi.LinkPreviewTypeAlbum(
    this.media.map { it.toData() }.toTypedArray(),
    this.caption
)

fun LinkPreviewTypeAnimation.toData(): TdApi.LinkPreviewTypeAnimation = TdApi.LinkPreviewTypeAnimation(
    this.animation.toData()
)

fun LinkPreviewTypeApp.toData(): TdApi.LinkPreviewTypeApp = TdApi.LinkPreviewTypeApp(
    this.photo.toData()
)

fun LinkPreviewTypeArticle.toData(): TdApi.LinkPreviewTypeArticle = TdApi.LinkPreviewTypeArticle(
    this.photo?.toData()
)

fun LinkPreviewTypeAudio.toData(): TdApi.LinkPreviewTypeAudio = TdApi.LinkPreviewTypeAudio(
    this.audio.toData()
)

fun LinkPreviewTypeBackground.toData(): TdApi.LinkPreviewTypeBackground = TdApi.LinkPreviewTypeBackground(
    this.document?.toData(),
    this.backgroundType?.toData()
)

fun LinkPreviewTypeChannelBoost.toData(): TdApi.LinkPreviewTypeChannelBoost = TdApi.LinkPreviewTypeChannelBoost(
    this.photo?.toData()
)

fun LinkPreviewTypeChat.toData(): TdApi.LinkPreviewTypeChat = TdApi.LinkPreviewTypeChat(
    this.type.toData(),
    this.photo?.toData(),
    this.createsJoinRequest
)

fun LinkPreviewTypeDirectMessagesChat.toData(): TdApi.LinkPreviewTypeDirectMessagesChat = TdApi.LinkPreviewTypeDirectMessagesChat(
    this.photo?.toData()
)

fun LinkPreviewTypeDocument.toData(): TdApi.LinkPreviewTypeDocument = TdApi.LinkPreviewTypeDocument(
    this.document.toData()
)

fun LinkPreviewTypeEmbeddedAnimationPlayer.toData(): TdApi.LinkPreviewTypeEmbeddedAnimationPlayer = TdApi.LinkPreviewTypeEmbeddedAnimationPlayer(
    this.url,
    this.thumbnail?.toData(),
    this.duration,
    this.width,
    this.height
)

fun LinkPreviewTypeEmbeddedAudioPlayer.toData(): TdApi.LinkPreviewTypeEmbeddedAudioPlayer = TdApi.LinkPreviewTypeEmbeddedAudioPlayer(
    this.url,
    this.thumbnail?.toData(),
    this.duration,
    this.width,
    this.height
)

fun LinkPreviewTypeEmbeddedVideoPlayer.toData(): TdApi.LinkPreviewTypeEmbeddedVideoPlayer = TdApi.LinkPreviewTypeEmbeddedVideoPlayer(
    this.url,
    this.thumbnail?.toData(),
    this.duration,
    this.width,
    this.height
)

fun LinkPreviewTypeExternalAudio.toData(): TdApi.LinkPreviewTypeExternalAudio = TdApi.LinkPreviewTypeExternalAudio(
    this.url,
    this.mimeType,
    this.duration
)

fun LinkPreviewTypeExternalVideo.toData(): TdApi.LinkPreviewTypeExternalVideo = TdApi.LinkPreviewTypeExternalVideo(
    this.url,
    this.mimeType,
    this.width,
    this.height,
    this.duration
)

fun LinkPreviewTypeGiftCollection.toData(): TdApi.LinkPreviewTypeGiftCollection = TdApi.LinkPreviewTypeGiftCollection(
    this.icons.map { it.toData() }.toTypedArray()
)

fun LinkPreviewTypeGroupCall.toData(): TdApi.LinkPreviewTypeGroupCall = TdApi.LinkPreviewTypeGroupCall(
)

fun LinkPreviewTypeInvoice.toData(): TdApi.LinkPreviewTypeInvoice = TdApi.LinkPreviewTypeInvoice(
)

fun LinkPreviewTypeMessage.toData(): TdApi.LinkPreviewTypeMessage = TdApi.LinkPreviewTypeMessage(
)

fun LinkPreviewTypePhoto.toData(): TdApi.LinkPreviewTypePhoto = TdApi.LinkPreviewTypePhoto(
    this.photo.toData()
)

fun LinkPreviewTypePremiumGiftCode.toData(): TdApi.LinkPreviewTypePremiumGiftCode = TdApi.LinkPreviewTypePremiumGiftCode(
)

fun LinkPreviewTypeShareableChatFolder.toData(): TdApi.LinkPreviewTypeShareableChatFolder = TdApi.LinkPreviewTypeShareableChatFolder(
)

fun LinkPreviewTypeSticker.toData(): TdApi.LinkPreviewTypeSticker = TdApi.LinkPreviewTypeSticker(
    this.sticker.toData()
)

fun LinkPreviewTypeStickerSet.toData(): TdApi.LinkPreviewTypeStickerSet = TdApi.LinkPreviewTypeStickerSet(
    this.stickers.map { it.toData() }.toTypedArray()
)

fun LinkPreviewTypeStory.toData(): TdApi.LinkPreviewTypeStory = TdApi.LinkPreviewTypeStory(
    this.storyPosterChatId,
    this.storyId
)

fun LinkPreviewTypeStoryAlbum.toData(): TdApi.LinkPreviewTypeStoryAlbum = TdApi.LinkPreviewTypeStoryAlbum(
    this.photoIcon?.toData(),
    this.videoIcon?.toData()
)

fun LinkPreviewTypeSupergroupBoost.toData(): TdApi.LinkPreviewTypeSupergroupBoost = TdApi.LinkPreviewTypeSupergroupBoost(
    this.photo?.toData()
)

fun LinkPreviewTypeTheme.toData(): TdApi.LinkPreviewTypeTheme = TdApi.LinkPreviewTypeTheme(
    this.documents.map { it.toData() }.toTypedArray(),
    this.settings?.toData()
)

fun LinkPreviewTypeUnsupported.toData(): TdApi.LinkPreviewTypeUnsupported = TdApi.LinkPreviewTypeUnsupported(
)

fun LinkPreviewTypeUpgradedGift.toData(): TdApi.LinkPreviewTypeUpgradedGift = TdApi.LinkPreviewTypeUpgradedGift(
    this.gift.toData()
)

fun LinkPreviewTypeUser.toData(): TdApi.LinkPreviewTypeUser = TdApi.LinkPreviewTypeUser(
    this.photo?.toData(),
    this.isBot
)

fun LinkPreviewTypeVideo.toData(): TdApi.LinkPreviewTypeVideo = TdApi.LinkPreviewTypeVideo(
    this.video.toData(),
    this.cover?.toData(),
    this.startTimestamp
)

fun LinkPreviewTypeVideoChat.toData(): TdApi.LinkPreviewTypeVideoChat = TdApi.LinkPreviewTypeVideoChat(
    this.photo?.toData(),
    this.isLiveStream,
    this.joinsAsSpeaker
)

fun LinkPreviewTypeVideoNote.toData(): TdApi.LinkPreviewTypeVideoNote = TdApi.LinkPreviewTypeVideoNote(
    this.videoNote.toData()
)

fun LinkPreviewTypeVoiceNote.toData(): TdApi.LinkPreviewTypeVoiceNote = TdApi.LinkPreviewTypeVoiceNote(
    this.voiceNote.toData()
)

fun LinkPreviewTypeWebApp.toData(): TdApi.LinkPreviewTypeWebApp = TdApi.LinkPreviewTypeWebApp(
    this.photo?.toData()
)

fun LocalFile.toData(): TdApi.LocalFile = TdApi.LocalFile(
    this.path,
    this.canBeDownloaded,
    this.canBeDeleted,
    this.isDownloadingActive,
    this.isDownloadingCompleted,
    this.downloadOffset,
    this.downloadedPrefixSize,
    this.downloadedSize
)

fun Location.toData(): TdApi.Location = TdApi.Location(
    this.latitude,
    this.longitude,
    this.horizontalAccuracy
)

fun MaskPoint.toData(): TdApi.MaskPoint = when(this) {
    is MaskPointForehead -> this.toData()
    is MaskPointEyes -> this.toData()
    is MaskPointMouth -> this.toData()
    is MaskPointChin -> this.toData()
}

fun MaskPointChin.toData(): TdApi.MaskPointChin = TdApi.MaskPointChin(
)

fun MaskPointEyes.toData(): TdApi.MaskPointEyes = TdApi.MaskPointEyes(
)

fun MaskPointForehead.toData(): TdApi.MaskPointForehead = TdApi.MaskPointForehead(
)

fun MaskPointMouth.toData(): TdApi.MaskPointMouth = TdApi.MaskPointMouth(
)

fun MaskPosition.toData(): TdApi.MaskPosition = TdApi.MaskPosition(
    this.point.toData(),
    this.xShift,
    this.yShift,
    this.scale
)

fun MessageAnimatedEmoji.toData(): TdApi.MessageAnimatedEmoji = TdApi.MessageAnimatedEmoji(
    this.animatedEmoji.toData(),
    this.emoji
)

fun MessageAnimation.toData(): TdApi.MessageAnimation = TdApi.MessageAnimation(
    this.animation.toData(),
    this.caption.toData(),
    this.showCaptionAboveMedia,
    this.hasSpoiler,
    this.isSecret
)

fun MessageAudio.toData(): TdApi.MessageAudio = TdApi.MessageAudio(
    this.audio.toData(),
    this.caption.toData()
)

fun MessageBasicGroupChatCreate.toData(): TdApi.MessageBasicGroupChatCreate = TdApi.MessageBasicGroupChatCreate(
    this.title,
    this.memberUserIds
)

fun MessageBotWriteAccessAllowed.toData(): TdApi.MessageBotWriteAccessAllowed = TdApi.MessageBotWriteAccessAllowed(
    this.reason.toData()
)

fun MessageCall.toData(): TdApi.MessageCall = TdApi.MessageCall(
    this.isVideo,
    this.discardReason.toData(),
    this.duration
)

fun MessageChatAddMembers.toData(): TdApi.MessageChatAddMembers = TdApi.MessageChatAddMembers(
    this.memberUserIds
)

fun MessageChatBoost.toData(): TdApi.MessageChatBoost = TdApi.MessageChatBoost(
    this.boostCount
)

fun MessageChatChangePhoto.toData(): TdApi.MessageChatChangePhoto = TdApi.MessageChatChangePhoto(
    this.photo.toData()
)

fun MessageChatChangeTitle.toData(): TdApi.MessageChatChangeTitle = TdApi.MessageChatChangeTitle(
    this.title
)

fun MessageChatDeleteMember.toData(): TdApi.MessageChatDeleteMember = TdApi.MessageChatDeleteMember(
    this.userId
)

fun MessageChatDeletePhoto.toData(): TdApi.MessageChatDeletePhoto = TdApi.MessageChatDeletePhoto(
)

fun MessageChatJoinByLink.toData(): TdApi.MessageChatJoinByLink = TdApi.MessageChatJoinByLink(
)

fun MessageChatJoinByRequest.toData(): TdApi.MessageChatJoinByRequest = TdApi.MessageChatJoinByRequest(
)

fun MessageChatSetBackground.toData(): TdApi.MessageChatSetBackground = TdApi.MessageChatSetBackground(
    this.oldBackgroundMessageId,
    this.background.toData(),
    this.onlyForSelf
)

fun MessageChatSetMessageAutoDeleteTime.toData(): TdApi.MessageChatSetMessageAutoDeleteTime = TdApi.MessageChatSetMessageAutoDeleteTime(
    this.messageAutoDeleteTime,
    this.fromUserId
)

fun MessageChatSetTheme.toData(): TdApi.MessageChatSetTheme = TdApi.MessageChatSetTheme(
    this.theme?.toData()
)

fun MessageChatShared.toData(): TdApi.MessageChatShared = TdApi.MessageChatShared(
    this.chat.toData(),
    this.buttonId
)

fun MessageChatUpgradeFrom.toData(): TdApi.MessageChatUpgradeFrom = TdApi.MessageChatUpgradeFrom(
    this.title,
    this.basicGroupId
)

fun MessageChatUpgradeTo.toData(): TdApi.MessageChatUpgradeTo = TdApi.MessageChatUpgradeTo(
    this.supergroupId
)

fun MessageChecklist.toData(): TdApi.MessageChecklist = TdApi.MessageChecklist(
    this.list.toData()
)

fun MessageChecklistTasksAdded.toData(): TdApi.MessageChecklistTasksAdded = TdApi.MessageChecklistTasksAdded(
    this.checklistMessageId,
    this.tasks.map { it.toData() }.toTypedArray()
)

fun MessageChecklistTasksDone.toData(): TdApi.MessageChecklistTasksDone = TdApi.MessageChecklistTasksDone(
    this.checklistMessageId,
    this.markedAsDoneTaskIds,
    this.markedAsNotDoneTaskIds
)

fun MessageContact.toData(): TdApi.MessageContact = TdApi.MessageContact(
    this.contact.toData()
)

fun MessageContactRegistered.toData(): TdApi.MessageContactRegistered = TdApi.MessageContactRegistered(
)

fun MessageContent.toData(): TdApi.MessageContent = when(this) {
    is MessageText -> this.toData()
    is MessageAnimation -> this.toData()
    is MessageAudio -> this.toData()
    is MessageDocument -> this.toData()
    is MessagePaidMedia -> this.toData()
    is MessagePhoto -> this.toData()
    is MessageSticker -> this.toData()
    is MessageVideo -> this.toData()
    is MessageVideoNote -> this.toData()
    is MessageVoiceNote -> this.toData()
    is MessageExpiredPhoto -> this.toData()
    is MessageExpiredVideo -> this.toData()
    is MessageExpiredVideoNote -> this.toData()
    is MessageExpiredVoiceNote -> this.toData()
    is MessageLocation -> this.toData()
    is MessageVenue -> this.toData()
    is MessageContact -> this.toData()
    is MessageAnimatedEmoji -> this.toData()
    is MessageDice -> this.toData()
    is MessageGame -> this.toData()
    is MessagePoll -> this.toData()
    is MessageStory -> this.toData()
    is MessageChecklist -> this.toData()
    is MessageInvoice -> this.toData()
    is MessageCall -> this.toData()
    is MessageGroupCall -> this.toData()
    is MessageVideoChatScheduled -> this.toData()
    is MessageVideoChatStarted -> this.toData()
    is MessageVideoChatEnded -> this.toData()
    is MessageInviteVideoChatParticipants -> this.toData()
    is MessageBasicGroupChatCreate -> this.toData()
    is MessageSupergroupChatCreate -> this.toData()
    is MessageChatChangeTitle -> this.toData()
    is MessageChatChangePhoto -> this.toData()
    is MessageChatDeletePhoto -> this.toData()
    is MessageChatAddMembers -> this.toData()
    is MessageChatJoinByLink -> this.toData()
    is MessageChatJoinByRequest -> this.toData()
    is MessageChatDeleteMember -> this.toData()
    is MessageChatUpgradeTo -> this.toData()
    is MessageChatUpgradeFrom -> this.toData()
    is MessagePinMessage -> this.toData()
    is MessageScreenshotTaken -> this.toData()
    is MessageChatSetBackground -> this.toData()
    is MessageChatSetTheme -> this.toData()
    is MessageChatSetMessageAutoDeleteTime -> this.toData()
    is MessageChatBoost -> this.toData()
    is MessageForumTopicCreated -> this.toData()
    is MessageForumTopicEdited -> this.toData()
    is MessageForumTopicIsClosedToggled -> this.toData()
    is MessageForumTopicIsHiddenToggled -> this.toData()
    is MessageSuggestProfilePhoto -> this.toData()
    is MessageCustomServiceAction -> this.toData()
    is MessageGameScore -> this.toData()
    is MessagePaymentSuccessful -> this.toData()
    is MessagePaymentSuccessfulBot -> this.toData()
    is MessagePaymentRefunded -> this.toData()
    is MessageGiftedPremium -> this.toData()
    is MessagePremiumGiftCode -> this.toData()
    is MessageGiveawayCreated -> this.toData()
    is MessageGiveaway -> this.toData()
    is MessageGiveawayCompleted -> this.toData()
    is MessageGiveawayWinners -> this.toData()
    is MessageGiftedStars -> this.toData()
    is MessageGiftedTon -> this.toData()
    is MessageGiveawayPrizeStars -> this.toData()
    is MessageGift -> this.toData()
    is MessageUpgradedGift -> this.toData()
    is MessageRefundedUpgradedGift -> this.toData()
    is MessagePaidMessagesRefunded -> this.toData()
    is MessagePaidMessagePriceChanged -> this.toData()
    is MessageDirectMessagePriceChanged -> this.toData()
    is MessageChecklistTasksDone -> this.toData()
    is MessageChecklistTasksAdded -> this.toData()
    is MessageSuggestedPostApprovalFailed -> this.toData()
    is MessageSuggestedPostApproved -> this.toData()
    is MessageSuggestedPostDeclined -> this.toData()
    is MessageSuggestedPostPaid -> this.toData()
    is MessageSuggestedPostRefunded -> this.toData()
    is MessageContactRegistered -> this.toData()
    is MessageUsersShared -> this.toData()
    is MessageChatShared -> this.toData()
    is MessageBotWriteAccessAllowed -> this.toData()
    is MessageWebAppDataSent -> this.toData()
    is MessageWebAppDataReceived -> this.toData()
    is MessagePassportDataSent -> this.toData()
    is MessagePassportDataReceived -> this.toData()
    is MessageProximityAlertTriggered -> this.toData()
    is MessageUnsupported -> this.toData()
}

fun MessageCustomServiceAction.toData(): TdApi.MessageCustomServiceAction = TdApi.MessageCustomServiceAction(
    this.text
)

fun MessageDice.toData(): TdApi.MessageDice = TdApi.MessageDice(
    this.initialState?.toData(),
    this.finalState?.toData(),
    this.emoji,
    this.value,
    this.successAnimationFrameNumber
)

fun MessageDirectMessagePriceChanged.toData(): TdApi.MessageDirectMessagePriceChanged = TdApi.MessageDirectMessagePriceChanged(
    this.isEnabled,
    this.paidMessageStarCount
)

fun MessageDocument.toData(): TdApi.MessageDocument = TdApi.MessageDocument(
    this.document.toData(),
    this.caption.toData()
)

fun MessageExpiredPhoto.toData(): TdApi.MessageExpiredPhoto = TdApi.MessageExpiredPhoto(
)

fun MessageExpiredVideo.toData(): TdApi.MessageExpiredVideo = TdApi.MessageExpiredVideo(
)

fun MessageExpiredVideoNote.toData(): TdApi.MessageExpiredVideoNote = TdApi.MessageExpiredVideoNote(
)

fun MessageExpiredVoiceNote.toData(): TdApi.MessageExpiredVoiceNote = TdApi.MessageExpiredVoiceNote(
)

fun MessageForumTopicCreated.toData(): TdApi.MessageForumTopicCreated = TdApi.MessageForumTopicCreated(
    this.name,
    this.icon.toData()
)

fun MessageForumTopicEdited.toData(): TdApi.MessageForumTopicEdited = TdApi.MessageForumTopicEdited(
    this.name,
    this.editIconCustomEmojiId,
    this.iconCustomEmojiId
)

fun MessageForumTopicIsClosedToggled.toData(): TdApi.MessageForumTopicIsClosedToggled = TdApi.MessageForumTopicIsClosedToggled(
    this.isClosed
)

fun MessageForumTopicIsHiddenToggled.toData(): TdApi.MessageForumTopicIsHiddenToggled = TdApi.MessageForumTopicIsHiddenToggled(
    this.isHidden
)

fun MessageGame.toData(): TdApi.MessageGame = TdApi.MessageGame(
    this.game.toData()
)

fun MessageGameScore.toData(): TdApi.MessageGameScore = TdApi.MessageGameScore(
    this.gameMessageId,
    this.gameId,
    this.score
)

fun MessageGift.toData(): TdApi.MessageGift = TdApi.MessageGift(
    this.gift.toData(),
    this.senderId?.toData(),
    this.receiverId.toData(),
    this.receivedGiftId,
    this.text.toData(),
    this.sellStarCount,
    this.prepaidUpgradeStarCount,
    this.isUpgradeSeparate,
    this.isPrivate,
    this.isSaved,
    this.isPrepaidUpgrade,
    this.canBeUpgraded,
    this.wasConverted,
    this.wasUpgraded,
    this.wasRefunded,
    this.upgradedReceivedGiftId,
    this.prepaidUpgradeHash
)

fun MessageGiftedPremium.toData(): TdApi.MessageGiftedPremium = TdApi.MessageGiftedPremium(
    this.gifterUserId,
    this.receiverUserId,
    this.text.toData(),
    this.currency,
    this.amount,
    this.cryptocurrency,
    this.cryptocurrencyAmount,
    this.monthCount,
    this.sticker?.toData()
)

fun MessageGiftedStars.toData(): TdApi.MessageGiftedStars = TdApi.MessageGiftedStars(
    this.gifterUserId,
    this.receiverUserId,
    this.currency,
    this.amount,
    this.cryptocurrency,
    this.cryptocurrencyAmount,
    this.starCount,
    this.transactionId,
    this.sticker?.toData()
)

fun MessageGiftedTon.toData(): TdApi.MessageGiftedTon = TdApi.MessageGiftedTon(
    this.gifterUserId,
    this.receiverUserId,
    this.tonAmount,
    this.transactionId,
    this.sticker?.toData()
)

fun MessageGiveaway.toData(): TdApi.MessageGiveaway = TdApi.MessageGiveaway(
    this.parameters.toData(),
    this.winnerCount,
    this.prize.toData(),
    this.sticker?.toData()
)

fun MessageGiveawayCompleted.toData(): TdApi.MessageGiveawayCompleted = TdApi.MessageGiveawayCompleted(
    this.giveawayMessageId,
    this.winnerCount,
    this.isStarGiveaway,
    this.unclaimedPrizeCount
)

fun MessageGiveawayCreated.toData(): TdApi.MessageGiveawayCreated = TdApi.MessageGiveawayCreated(
    this.starCount
)

fun MessageGiveawayPrizeStars.toData(): TdApi.MessageGiveawayPrizeStars = TdApi.MessageGiveawayPrizeStars(
    this.starCount,
    this.transactionId,
    this.boostedChatId,
    this.giveawayMessageId,
    this.isUnclaimed,
    this.sticker?.toData()
)

fun MessageGiveawayWinners.toData(): TdApi.MessageGiveawayWinners = TdApi.MessageGiveawayWinners(
    this.boostedChatId,
    this.giveawayMessageId,
    this.additionalChatCount,
    this.actualWinnersSelectionDate,
    this.onlyNewMembers,
    this.wasRefunded,
    this.prize.toData(),
    this.prizeDescription,
    this.winnerCount,
    this.winnerUserIds,
    this.unclaimedPrizeCount
)

fun MessageGroupCall.toData(): TdApi.MessageGroupCall = TdApi.MessageGroupCall(
    this.isActive,
    this.wasMissed,
    this.isVideo,
    this.duration,
    this.otherParticipantIds.map { it.toData() }.toTypedArray()
)

fun MessageInviteVideoChatParticipants.toData(): TdApi.MessageInviteVideoChatParticipants = TdApi.MessageInviteVideoChatParticipants(
    this.groupCallId,
    this.userIds
)

fun MessageInvoice.toData(): TdApi.MessageInvoice = TdApi.MessageInvoice(
    this.productInfo.toData(),
    this.currency,
    this.totalAmount,
    this.startParameter,
    this.isTest,
    this.needShippingAddress,
    this.receiptMessageId,
    this.paidMedia?.toData(),
    this.paidMediaCaption?.toData()
)

fun MessageLocation.toData(): TdApi.MessageLocation = TdApi.MessageLocation(
    this.location.toData(),
    this.livePeriod,
    this.expiresIn,
    this.heading,
    this.proximityAlertRadius
)

fun MessagePaidMedia.toData(): TdApi.MessagePaidMedia = TdApi.MessagePaidMedia(
    this.starCount,
    this.media.map { it.toData() }.toTypedArray(),
    this.caption.toData(),
    this.showCaptionAboveMedia
)

fun MessagePaidMessagePriceChanged.toData(): TdApi.MessagePaidMessagePriceChanged = TdApi.MessagePaidMessagePriceChanged(
    this.paidMessageStarCount
)

fun MessagePaidMessagesRefunded.toData(): TdApi.MessagePaidMessagesRefunded = TdApi.MessagePaidMessagesRefunded(
    this.messageCount,
    this.starCount
)

fun MessagePassportDataReceived.toData(): TdApi.MessagePassportDataReceived = TdApi.MessagePassportDataReceived(
    this.elements.map { it.toData() }.toTypedArray(),
    this.credentials.toData()
)

fun MessagePassportDataSent.toData(): TdApi.MessagePassportDataSent = TdApi.MessagePassportDataSent(
    this.types.map { it.toData() }.toTypedArray()
)

fun MessagePaymentRefunded.toData(): TdApi.MessagePaymentRefunded = TdApi.MessagePaymentRefunded(
    this.ownerId.toData(),
    this.currency,
    this.totalAmount,
    this.invoicePayload,
    this.telegramPaymentChargeId,
    this.providerPaymentChargeId
)

fun MessagePaymentSuccessful.toData(): TdApi.MessagePaymentSuccessful = TdApi.MessagePaymentSuccessful(
    this.invoiceChatId,
    this.invoiceMessageId,
    this.currency,
    this.totalAmount,
    this.subscriptionUntilDate,
    this.isRecurring,
    this.isFirstRecurring,
    this.invoiceName
)

fun MessagePaymentSuccessfulBot.toData(): TdApi.MessagePaymentSuccessfulBot = TdApi.MessagePaymentSuccessfulBot(
    this.currency,
    this.totalAmount,
    this.subscriptionUntilDate,
    this.isRecurring,
    this.isFirstRecurring,
    this.invoicePayload,
    this.shippingOptionId,
    this.orderInfo?.toData(),
    this.telegramPaymentChargeId,
    this.providerPaymentChargeId
)

fun MessagePhoto.toData(): TdApi.MessagePhoto = TdApi.MessagePhoto(
    this.photo.toData(),
    this.caption.toData(),
    this.showCaptionAboveMedia,
    this.hasSpoiler,
    this.isSecret
)

fun MessagePinMessage.toData(): TdApi.MessagePinMessage = TdApi.MessagePinMessage(
    this.messageId
)

fun MessagePoll.toData(): TdApi.MessagePoll = TdApi.MessagePoll(
    this.poll.toData()
)

fun MessagePremiumGiftCode.toData(): TdApi.MessagePremiumGiftCode = TdApi.MessagePremiumGiftCode(
    this.creatorId?.toData(),
    this.text.toData(),
    this.isFromGiveaway,
    this.isUnclaimed,
    this.currency,
    this.amount,
    this.cryptocurrency,
    this.cryptocurrencyAmount,
    this.monthCount,
    this.sticker?.toData(),
    this.code
)

fun MessageProximityAlertTriggered.toData(): TdApi.MessageProximityAlertTriggered = TdApi.MessageProximityAlertTriggered(
    this.travelerId.toData(),
    this.watcherId.toData(),
    this.distance
)

fun MessageRefundedUpgradedGift.toData(): TdApi.MessageRefundedUpgradedGift = TdApi.MessageRefundedUpgradedGift(
    this.gift.toData(),
    this.senderId.toData(),
    this.receiverId.toData(),
    this.origin.toData()
)

fun MessageScreenshotTaken.toData(): TdApi.MessageScreenshotTaken = TdApi.MessageScreenshotTaken(
)

fun MessageSender.toData(): TdApi.MessageSender = when(this) {
    is MessageSenderUser -> this.toData()
    is MessageSenderChat -> this.toData()
}

fun MessageSenderChat.toData(): TdApi.MessageSenderChat = TdApi.MessageSenderChat(
    this.chatId
)

fun MessageSenderUser.toData(): TdApi.MessageSenderUser = TdApi.MessageSenderUser(
    this.userId
)

fun MessageSticker.toData(): TdApi.MessageSticker = TdApi.MessageSticker(
    this.sticker.toData(),
    this.isPremium
)

fun MessageStory.toData(): TdApi.MessageStory = TdApi.MessageStory(
    this.storyPosterChatId,
    this.storyId,
    this.viaMention
)

fun MessageSuggestProfilePhoto.toData(): TdApi.MessageSuggestProfilePhoto = TdApi.MessageSuggestProfilePhoto(
    this.photo.toData()
)

fun MessageSuggestedPostApprovalFailed.toData(): TdApi.MessageSuggestedPostApprovalFailed = TdApi.MessageSuggestedPostApprovalFailed(
    this.suggestedPostMessageId,
    this.price.toData()
)

fun MessageSuggestedPostApproved.toData(): TdApi.MessageSuggestedPostApproved = TdApi.MessageSuggestedPostApproved(
    this.suggestedPostMessageId,
    this.price?.toData(),
    this.sendDate
)

fun MessageSuggestedPostDeclined.toData(): TdApi.MessageSuggestedPostDeclined = TdApi.MessageSuggestedPostDeclined(
    this.suggestedPostMessageId,
    this.comment
)

fun MessageSuggestedPostPaid.toData(): TdApi.MessageSuggestedPostPaid = TdApi.MessageSuggestedPostPaid(
    this.suggestedPostMessageId,
    this.starAmount.toData(),
    this.tonAmount
)

fun MessageSuggestedPostRefunded.toData(): TdApi.MessageSuggestedPostRefunded = TdApi.MessageSuggestedPostRefunded(
    this.suggestedPostMessageId,
    this.reason.toData()
)

fun MessageSupergroupChatCreate.toData(): TdApi.MessageSupergroupChatCreate = TdApi.MessageSupergroupChatCreate(
    this.title
)

fun MessageText.toData(): TdApi.MessageText = TdApi.MessageText(
    this.text.toData(),
    this.linkPreview?.toData(),
    this.linkPreviewOptions?.toData()
)

fun MessageUnsupported.toData(): TdApi.MessageUnsupported = TdApi.MessageUnsupported(
)

fun MessageUpgradedGift.toData(): TdApi.MessageUpgradedGift = TdApi.MessageUpgradedGift(
    this.gift.toData(),
    this.senderId?.toData(),
    this.receiverId.toData(),
    this.origin.toData(),
    this.receivedGiftId,
    this.isSaved,
    this.canBeTransferred,
    this.wasTransferred,
    this.transferStarCount,
    this.nextTransferDate,
    this.nextResaleDate,
    this.exportDate
)

fun MessageUsersShared.toData(): TdApi.MessageUsersShared = TdApi.MessageUsersShared(
    this.users.map { it.toData() }.toTypedArray(),
    this.buttonId
)

fun MessageVenue.toData(): TdApi.MessageVenue = TdApi.MessageVenue(
    this.venue.toData()
)

fun MessageVideo.toData(): TdApi.MessageVideo = TdApi.MessageVideo(
    this.video.toData(),
    this.alternativeVideos.map { it.toData() }.toTypedArray(),
    this.storyboards.map { it.toData() }.toTypedArray(),
    this.cover?.toData(),
    this.startTimestamp,
    this.caption.toData(),
    this.showCaptionAboveMedia,
    this.hasSpoiler,
    this.isSecret
)

fun MessageVideoChatEnded.toData(): TdApi.MessageVideoChatEnded = TdApi.MessageVideoChatEnded(
    this.duration
)

fun MessageVideoChatScheduled.toData(): TdApi.MessageVideoChatScheduled = TdApi.MessageVideoChatScheduled(
    this.groupCallId,
    this.startDate
)

fun MessageVideoChatStarted.toData(): TdApi.MessageVideoChatStarted = TdApi.MessageVideoChatStarted(
    this.groupCallId
)

fun MessageVideoNote.toData(): TdApi.MessageVideoNote = TdApi.MessageVideoNote(
    this.videoNote.toData(),
    this.isViewed,
    this.isSecret
)

fun MessageVoiceNote.toData(): TdApi.MessageVoiceNote = TdApi.MessageVoiceNote(
    this.voiceNote.toData(),
    this.caption.toData(),
    this.isListened
)

fun MessageWebAppDataReceived.toData(): TdApi.MessageWebAppDataReceived = TdApi.MessageWebAppDataReceived(
    this.buttonText,
    this.data
)

fun MessageWebAppDataSent.toData(): TdApi.MessageWebAppDataSent = TdApi.MessageWebAppDataSent(
    this.buttonText
)

fun Minithumbnail.toData(): TdApi.Minithumbnail = TdApi.Minithumbnail(
    this.width,
    this.height,
    this.data
)

fun OrderInfo.toData(): TdApi.OrderInfo = TdApi.OrderInfo(
    this.name,
    this.phoneNumber,
    this.emailAddress,
    this.shippingAddress?.toData()
)

fun PaidMedia.toData(): TdApi.PaidMedia = when(this) {
    is PaidMediaPreview -> this.toData()
    is PaidMediaPhoto -> this.toData()
    is PaidMediaVideo -> this.toData()
    is PaidMediaUnsupported -> this.toData()
}

fun PaidMediaPhoto.toData(): TdApi.PaidMediaPhoto = TdApi.PaidMediaPhoto(
    this.photo.toData()
)

fun PaidMediaPreview.toData(): TdApi.PaidMediaPreview = TdApi.PaidMediaPreview(
    this.width,
    this.height,
    this.duration,
    this.minithumbnail?.toData()
)

fun PaidMediaUnsupported.toData(): TdApi.PaidMediaUnsupported = TdApi.PaidMediaUnsupported(
)

fun PaidMediaVideo.toData(): TdApi.PaidMediaVideo = TdApi.PaidMediaVideo(
    this.video.toData(),
    this.cover?.toData(),
    this.startTimestamp
)

fun PassportElementType.toData(): TdApi.PassportElementType = when(this) {
    is PassportElementTypePersonalDetails -> this.toData()
    is PassportElementTypePassport -> this.toData()
    is PassportElementTypeDriverLicense -> this.toData()
    is PassportElementTypeIdentityCard -> this.toData()
    is PassportElementTypeInternalPassport -> this.toData()
    is PassportElementTypeAddress -> this.toData()
    is PassportElementTypeUtilityBill -> this.toData()
    is PassportElementTypeBankStatement -> this.toData()
    is PassportElementTypeRentalAgreement -> this.toData()
    is PassportElementTypePassportRegistration -> this.toData()
    is PassportElementTypeTemporaryRegistration -> this.toData()
    is PassportElementTypePhoneNumber -> this.toData()
    is PassportElementTypeEmailAddress -> this.toData()
}

fun PassportElementTypeAddress.toData(): TdApi.PassportElementTypeAddress = TdApi.PassportElementTypeAddress(
)

fun PassportElementTypeBankStatement.toData(): TdApi.PassportElementTypeBankStatement = TdApi.PassportElementTypeBankStatement(
)

fun PassportElementTypeDriverLicense.toData(): TdApi.PassportElementTypeDriverLicense = TdApi.PassportElementTypeDriverLicense(
)

fun PassportElementTypeEmailAddress.toData(): TdApi.PassportElementTypeEmailAddress = TdApi.PassportElementTypeEmailAddress(
)

fun PassportElementTypeIdentityCard.toData(): TdApi.PassportElementTypeIdentityCard = TdApi.PassportElementTypeIdentityCard(
)

fun PassportElementTypeInternalPassport.toData(): TdApi.PassportElementTypeInternalPassport = TdApi.PassportElementTypeInternalPassport(
)

fun PassportElementTypePassport.toData(): TdApi.PassportElementTypePassport = TdApi.PassportElementTypePassport(
)

fun PassportElementTypePassportRegistration.toData(): TdApi.PassportElementTypePassportRegistration = TdApi.PassportElementTypePassportRegistration(
)

fun PassportElementTypePersonalDetails.toData(): TdApi.PassportElementTypePersonalDetails = TdApi.PassportElementTypePersonalDetails(
)

fun PassportElementTypePhoneNumber.toData(): TdApi.PassportElementTypePhoneNumber = TdApi.PassportElementTypePhoneNumber(
)

fun PassportElementTypeRentalAgreement.toData(): TdApi.PassportElementTypeRentalAgreement = TdApi.PassportElementTypeRentalAgreement(
)

fun PassportElementTypeTemporaryRegistration.toData(): TdApi.PassportElementTypeTemporaryRegistration = TdApi.PassportElementTypeTemporaryRegistration(
)

fun PassportElementTypeUtilityBill.toData(): TdApi.PassportElementTypeUtilityBill = TdApi.PassportElementTypeUtilityBill(
)

fun Photo.toData(): TdApi.Photo = TdApi.Photo(
    this.hasStickers,
    this.minithumbnail?.toData(),
    this.sizes.map { it.toData() }.toTypedArray()
)

fun PhotoSize.toData(): TdApi.PhotoSize = TdApi.PhotoSize(
    this.type,
    this.photo.toData(),
    this.width,
    this.height,
    this.progressiveSizes
)

fun Poll.toData(): TdApi.Poll = TdApi.Poll(
    this.id,
    this.question.toData(),
    this.options.map { it.toData() }.toTypedArray(),
    this.totalVoterCount,
    this.recentVoterIds.map { it.toData() }.toTypedArray(),
    this.isAnonymous,
    this.type.toData(),
    this.openPeriod,
    this.closeDate,
    this.isClosed
)

fun PollOption.toData(): TdApi.PollOption = TdApi.PollOption(
    this.text.toData(),
    this.voterCount,
    this.votePercentage,
    this.isChosen,
    this.isBeingChosen
)

fun PollType.toData(): TdApi.PollType = when(this) {
    is PollTypeRegular -> this.toData()
    is PollTypeQuiz -> this.toData()
}

fun PollTypeQuiz.toData(): TdApi.PollTypeQuiz = TdApi.PollTypeQuiz(
    this.correctOptionId,
    this.explanation.toData()
)

fun PollTypeRegular.toData(): TdApi.PollTypeRegular = TdApi.PollTypeRegular(
    this.allowMultipleAnswers
)

fun ProductInfo.toData(): TdApi.ProductInfo = TdApi.ProductInfo(
    this.title,
    this.description.toData(),
    this.photo?.toData()
)

fun RemoteFile.toData(): TdApi.RemoteFile = TdApi.RemoteFile(
    this.id,
    this.uniqueId,
    this.isUploadingActive,
    this.isUploadingCompleted,
    this.uploadedSize
)

fun SharedChat.toData(): TdApi.SharedChat = TdApi.SharedChat(
    this.chatId,
    this.title,
    this.username,
    this.photo?.toData()
)

fun SharedUser.toData(): TdApi.SharedUser = TdApi.SharedUser(
    this.userId,
    this.firstName,
    this.lastName,
    this.username,
    this.photo?.toData()
)

fun SpeechRecognitionResult.toData(): TdApi.SpeechRecognitionResult = when(this) {
    is SpeechRecognitionResultPending -> this.toData()
    is SpeechRecognitionResultText -> this.toData()
    is SpeechRecognitionResultError -> this.toData()
}

fun SpeechRecognitionResultError.toData(): TdApi.SpeechRecognitionResultError = TdApi.SpeechRecognitionResultError(
    this.error.toData()
)

fun SpeechRecognitionResultPending.toData(): TdApi.SpeechRecognitionResultPending = TdApi.SpeechRecognitionResultPending(
    this.partialText
)

fun SpeechRecognitionResultText.toData(): TdApi.SpeechRecognitionResultText = TdApi.SpeechRecognitionResultText(
    this.text
)

fun StarAmount.toData(): TdApi.StarAmount = TdApi.StarAmount(
    this.starCount,
    this.nanostarCount
)

fun Sticker.toData(): TdApi.Sticker = TdApi.Sticker(
    this.id,
    this.setId,
    this.width,
    this.height,
    this.emoji,
    this.format.toData(),
    this.fullType.toData(),
    this.thumbnail?.toData(),
    this.sticker.toData()
)

fun StickerFormat.toData(): TdApi.StickerFormat = when(this) {
    is StickerFormatWebp -> this.toData()
    is StickerFormatTgs -> this.toData()
    is StickerFormatWebm -> this.toData()
}

fun StickerFormatTgs.toData(): TdApi.StickerFormatTgs = TdApi.StickerFormatTgs(
)

fun StickerFormatWebm.toData(): TdApi.StickerFormatWebm = TdApi.StickerFormatWebm(
)

fun StickerFormatWebp.toData(): TdApi.StickerFormatWebp = TdApi.StickerFormatWebp(
)

fun StickerFullType.toData(): TdApi.StickerFullType = when(this) {
    is StickerFullTypeRegular -> this.toData()
    is StickerFullTypeMask -> this.toData()
    is StickerFullTypeCustomEmoji -> this.toData()
}

fun StickerFullTypeCustomEmoji.toData(): TdApi.StickerFullTypeCustomEmoji = TdApi.StickerFullTypeCustomEmoji(
    this.customEmojiId,
    this.needsRepainting
)

fun StickerFullTypeMask.toData(): TdApi.StickerFullTypeMask = TdApi.StickerFullTypeMask(
    this.maskPosition?.toData()
)

fun StickerFullTypeRegular.toData(): TdApi.StickerFullTypeRegular = TdApi.StickerFullTypeRegular(
    this.premiumAnimation?.toData()
)

fun SuggestedPostPrice.toData(): TdApi.SuggestedPostPrice = when(this) {
    is SuggestedPostPriceStar -> this.toData()
    is SuggestedPostPriceTon -> this.toData()
}

fun SuggestedPostPriceStar.toData(): TdApi.SuggestedPostPriceStar = TdApi.SuggestedPostPriceStar(
    this.starCount
)

fun SuggestedPostPriceTon.toData(): TdApi.SuggestedPostPriceTon = TdApi.SuggestedPostPriceTon(
    this.toncoinCentCount
)

fun SuggestedPostRefundReason.toData(): TdApi.SuggestedPostRefundReason = when(this) {
    is SuggestedPostRefundReasonPostDeleted -> this.toData()
    is SuggestedPostRefundReasonPaymentRefunded -> this.toData()
}

fun SuggestedPostRefundReasonPaymentRefunded.toData(): TdApi.SuggestedPostRefundReasonPaymentRefunded = TdApi.SuggestedPostRefundReasonPaymentRefunded(
)

fun SuggestedPostRefundReasonPostDeleted.toData(): TdApi.SuggestedPostRefundReasonPostDeleted = TdApi.SuggestedPostRefundReasonPostDeleted(
)

fun TextEntity.toData(): TdApi.TextEntity = TdApi.TextEntity(
    this.offset,
    this.length,
    this.type.toData()
)

fun TextEntityType.toData(): TdApi.TextEntityType = when(this) {
    is TextEntityTypeMention -> this.toData()
    is TextEntityTypeHashtag -> this.toData()
    is TextEntityTypeCashtag -> this.toData()
    is TextEntityTypeBotCommand -> this.toData()
    is TextEntityTypeUrl -> this.toData()
    is TextEntityTypeEmailAddress -> this.toData()
    is TextEntityTypePhoneNumber -> this.toData()
    is TextEntityTypeBankCardNumber -> this.toData()
    is TextEntityTypeBold -> this.toData()
    is TextEntityTypeItalic -> this.toData()
    is TextEntityTypeUnderline -> this.toData()
    is TextEntityTypeStrikethrough -> this.toData()
    is TextEntityTypeSpoiler -> this.toData()
    is TextEntityTypeCode -> this.toData()
    is TextEntityTypePre -> this.toData()
    is TextEntityTypePreCode -> this.toData()
    is TextEntityTypeBlockQuote -> this.toData()
    is TextEntityTypeExpandableBlockQuote -> this.toData()
    is TextEntityTypeTextUrl -> this.toData()
    is TextEntityTypeMentionName -> this.toData()
    is TextEntityTypeCustomEmoji -> this.toData()
    is TextEntityTypeMediaTimestamp -> this.toData()
}

fun TextEntityTypeBankCardNumber.toData(): TdApi.TextEntityTypeBankCardNumber = TdApi.TextEntityTypeBankCardNumber(
)

fun TextEntityTypeBlockQuote.toData(): TdApi.TextEntityTypeBlockQuote = TdApi.TextEntityTypeBlockQuote(
)

fun TextEntityTypeBold.toData(): TdApi.TextEntityTypeBold = TdApi.TextEntityTypeBold(
)

fun TextEntityTypeBotCommand.toData(): TdApi.TextEntityTypeBotCommand = TdApi.TextEntityTypeBotCommand(
)

fun TextEntityTypeCashtag.toData(): TdApi.TextEntityTypeCashtag = TdApi.TextEntityTypeCashtag(
)

fun TextEntityTypeCode.toData(): TdApi.TextEntityTypeCode = TdApi.TextEntityTypeCode(
)

fun TextEntityTypeCustomEmoji.toData(): TdApi.TextEntityTypeCustomEmoji = TdApi.TextEntityTypeCustomEmoji(
    this.customEmojiId
)

fun TextEntityTypeEmailAddress.toData(): TdApi.TextEntityTypeEmailAddress = TdApi.TextEntityTypeEmailAddress(
)

fun TextEntityTypeExpandableBlockQuote.toData(): TdApi.TextEntityTypeExpandableBlockQuote = TdApi.TextEntityTypeExpandableBlockQuote(
)

fun TextEntityTypeHashtag.toData(): TdApi.TextEntityTypeHashtag = TdApi.TextEntityTypeHashtag(
)

fun TextEntityTypeItalic.toData(): TdApi.TextEntityTypeItalic = TdApi.TextEntityTypeItalic(
)

fun TextEntityTypeMediaTimestamp.toData(): TdApi.TextEntityTypeMediaTimestamp = TdApi.TextEntityTypeMediaTimestamp(
    this.mediaTimestamp
)

fun TextEntityTypeMention.toData(): TdApi.TextEntityTypeMention = TdApi.TextEntityTypeMention(
)

fun TextEntityTypeMentionName.toData(): TdApi.TextEntityTypeMentionName = TdApi.TextEntityTypeMentionName(
    this.userId
)

fun TextEntityTypePhoneNumber.toData(): TdApi.TextEntityTypePhoneNumber = TdApi.TextEntityTypePhoneNumber(
)

fun TextEntityTypePre.toData(): TdApi.TextEntityTypePre = TdApi.TextEntityTypePre(
)

fun TextEntityTypePreCode.toData(): TdApi.TextEntityTypePreCode = TdApi.TextEntityTypePreCode(
    this.language
)

fun TextEntityTypeSpoiler.toData(): TdApi.TextEntityTypeSpoiler = TdApi.TextEntityTypeSpoiler(
)

fun TextEntityTypeStrikethrough.toData(): TdApi.TextEntityTypeStrikethrough = TdApi.TextEntityTypeStrikethrough(
)

fun TextEntityTypeTextUrl.toData(): TdApi.TextEntityTypeTextUrl = TdApi.TextEntityTypeTextUrl(
    this.url
)

fun TextEntityTypeUnderline.toData(): TdApi.TextEntityTypeUnderline = TdApi.TextEntityTypeUnderline(
)

fun TextEntityTypeUrl.toData(): TdApi.TextEntityTypeUrl = TdApi.TextEntityTypeUrl(
)

fun ThemeSettings.toData(): TdApi.ThemeSettings = TdApi.ThemeSettings(
    this.baseTheme.toData(),
    this.accentColor,
    this.background?.toData(),
    this.outgoingMessageFill?.toData(),
    this.animateOutgoingMessageFill,
    this.outgoingMessageAccentColor
)

fun Thumbnail.toData(): TdApi.Thumbnail = TdApi.Thumbnail(
    this.format.toData(),
    this.width,
    this.height,
    this.file.toData()
)

fun ThumbnailFormat.toData(): TdApi.ThumbnailFormat = when(this) {
    is ThumbnailFormatJpeg -> this.toData()
    is ThumbnailFormatGif -> this.toData()
    is ThumbnailFormatMpeg4 -> this.toData()
    is ThumbnailFormatPng -> this.toData()
    is ThumbnailFormatTgs -> this.toData()
    is ThumbnailFormatWebm -> this.toData()
    is ThumbnailFormatWebp -> this.toData()
}

fun ThumbnailFormatGif.toData(): TdApi.ThumbnailFormatGif = TdApi.ThumbnailFormatGif(
)

fun ThumbnailFormatJpeg.toData(): TdApi.ThumbnailFormatJpeg = TdApi.ThumbnailFormatJpeg(
)

fun ThumbnailFormatMpeg4.toData(): TdApi.ThumbnailFormatMpeg4 = TdApi.ThumbnailFormatMpeg4(
)

fun ThumbnailFormatPng.toData(): TdApi.ThumbnailFormatPng = TdApi.ThumbnailFormatPng(
)

fun ThumbnailFormatTgs.toData(): TdApi.ThumbnailFormatTgs = TdApi.ThumbnailFormatTgs(
)

fun ThumbnailFormatWebm.toData(): TdApi.ThumbnailFormatWebm = TdApi.ThumbnailFormatWebm(
)

fun ThumbnailFormatWebp.toData(): TdApi.ThumbnailFormatWebp = TdApi.ThumbnailFormatWebp(
)

fun UpgradedGift.toData(): TdApi.UpgradedGift = TdApi.UpgradedGift(
    this.id,
    this.regularGiftId,
    this.publisherChatId,
    this.title,
    this.name,
    this.number,
    this.totalUpgradedCount,
    this.maxUpgradedCount,
    this.isPremium,
    this.isThemeAvailable,
    this.usedThemeChatId,
    this.ownerId?.toData(),
    this.ownerAddress,
    this.ownerName,
    this.giftAddress,
    this.model.toData(),
    this.symbol.toData(),
    this.backdrop.toData(),
    this.originalDetails?.toData(),
    this.resaleParameters?.toData(),
    this.valueCurrency,
    this.valueAmount
)

fun UpgradedGiftBackdrop.toData(): TdApi.UpgradedGiftBackdrop = TdApi.UpgradedGiftBackdrop(
    this.id,
    this.name,
    this.colors.toData(),
    this.rarityPerMille
)

fun UpgradedGiftBackdropColors.toData(): TdApi.UpgradedGiftBackdropColors = TdApi.UpgradedGiftBackdropColors(
    this.centerColor,
    this.edgeColor,
    this.symbolColor,
    this.textColor
)

fun UpgradedGiftModel.toData(): TdApi.UpgradedGiftModel = TdApi.UpgradedGiftModel(
    this.name,
    this.sticker.toData(),
    this.rarityPerMille
)

fun UpgradedGiftOrigin.toData(): TdApi.UpgradedGiftOrigin = when(this) {
    is UpgradedGiftOriginUpgrade -> this.toData()
    is UpgradedGiftOriginTransfer -> this.toData()
    is UpgradedGiftOriginResale -> this.toData()
    is UpgradedGiftOriginPrepaidUpgrade -> this.toData()
}

fun UpgradedGiftOriginPrepaidUpgrade.toData(): TdApi.UpgradedGiftOriginPrepaidUpgrade = TdApi.UpgradedGiftOriginPrepaidUpgrade(
)

fun UpgradedGiftOriginResale.toData(): TdApi.UpgradedGiftOriginResale = TdApi.UpgradedGiftOriginResale(
    this.price.toData()
)

fun UpgradedGiftOriginTransfer.toData(): TdApi.UpgradedGiftOriginTransfer = TdApi.UpgradedGiftOriginTransfer(
)

fun UpgradedGiftOriginUpgrade.toData(): TdApi.UpgradedGiftOriginUpgrade = TdApi.UpgradedGiftOriginUpgrade(
    this.giftMessageId
)

fun UpgradedGiftOriginalDetails.toData(): TdApi.UpgradedGiftOriginalDetails = TdApi.UpgradedGiftOriginalDetails(
    this.senderId?.toData(),
    this.receiverId.toData(),
    this.text.toData(),
    this.date
)

fun UpgradedGiftSymbol.toData(): TdApi.UpgradedGiftSymbol = TdApi.UpgradedGiftSymbol(
    this.name,
    this.sticker.toData(),
    this.rarityPerMille
)

fun Venue.toData(): TdApi.Venue = TdApi.Venue(
    this.location.toData(),
    this.title,
    this.address,
    this.provider,
    this.id,
    this.type
)

fun Video.toData(): TdApi.Video = TdApi.Video(
    this.duration,
    this.width,
    this.height,
    this.fileName,
    this.mimeType,
    this.hasStickers,
    this.supportsStreaming,
    this.minithumbnail?.toData(),
    this.thumbnail?.toData(),
    this.video.toData()
)

fun VideoNote.toData(): TdApi.VideoNote = TdApi.VideoNote(
    this.duration,
    this.waveform,
    this.length,
    this.minithumbnail?.toData(),
    this.thumbnail?.toData(),
    this.speechRecognitionResult?.toData(),
    this.video.toData()
)

fun VideoStoryboard.toData(): TdApi.VideoStoryboard = TdApi.VideoStoryboard(
    this.storyboardFile.toData(),
    this.width,
    this.height,
    this.mapFile.toData()
)

fun VoiceNote.toData(): TdApi.VoiceNote = TdApi.VoiceNote(
    this.duration,
    this.waveform,
    this.mimeType,
    this.speechRecognitionResult?.toData(),
    this.voice.toData()
)

fun WebApp.toData(): TdApi.WebApp = TdApi.WebApp(
    this.shortName,
    this.title,
    this.description,
    this.photo.toData(),
    this.animation?.toData()
)

