package com.xxcactussell.data.utils.todomain

import com.xxcactussell.domain.messages.model.*
import org.drinkless.tdlib.TdApi

fun TdApi.Address.toDomain(): Address = Address(
    countryCode = this.countryCode,
    state = this.state,
    city = this.city,
    streetLine1 = this.streetLine1,
    streetLine2 = this.streetLine2,
    postalCode = this.postalCode
)

fun TdApi.AlternativeVideo.toDomain(): AlternativeVideo = AlternativeVideo(
    id = this.id,
    width = this.width,
    height = this.height,
    codec = this.codec,
    hlsFile = this.hlsFile.toDomain(),
    video = this.video.toDomain()
)

fun TdApi.AnimatedChatPhoto.toDomain(): AnimatedChatPhoto = AnimatedChatPhoto(
    length = this.length,
    file = this.file.toDomain(),
    mainFrameTimestamp = this.mainFrameTimestamp
)

fun TdApi.AnimatedEmoji.toDomain(): AnimatedEmoji = AnimatedEmoji(
    sticker = this.sticker?.toDomain(),
    stickerWidth = this.stickerWidth,
    stickerHeight = this.stickerHeight,
    fitzpatrickType = this.fitzpatrickType,
    sound = this.sound?.toDomain()
)

fun TdApi.Animation.toDomain(): Animation = Animation(
    duration = this.duration,
    width = this.width,
    height = this.height,
    fileName = this.fileName,
    mimeType = this.mimeType,
    hasStickers = this.hasStickers,
    minithumbnail = this.minithumbnail?.toDomain(),
    thumbnail = this.thumbnail?.toDomain(),
    animation = this.animation.toDomain()
)

fun TdApi.Audio.toDomain(): Audio = Audio(
    duration = this.duration,
    title = this.title,
    performer = this.performer,
    fileName = this.fileName,
    mimeType = this.mimeType,
    albumCoverMinithumbnail = this.albumCoverMinithumbnail?.toDomain(),
    albumCoverThumbnail = this.albumCoverThumbnail?.toDomain(),
    externalAlbumCovers = this.externalAlbumCovers.map { it.toDomain() },
    audio = this.audio.toDomain()
)

fun TdApi.Background.toDomain(): Background = Background(
    id = this.id,
    isDefault = this.isDefault,
    isDark = this.isDark,
    name = this.name,
    document = this.document?.toDomain(),
    type = this.type.toDomain()
)

fun TdApi.BackgroundFill.toDomain(): BackgroundFill = when(this) {
    is TdApi.BackgroundFillSolid -> this.toDomain()
    is TdApi.BackgroundFillGradient -> this.toDomain()
    is TdApi.BackgroundFillFreeformGradient -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.BackgroundFillFreeformGradient.toDomain(): BackgroundFillFreeformGradient = BackgroundFillFreeformGradient(
    colors = this.colors
)

fun TdApi.BackgroundFillGradient.toDomain(): BackgroundFillGradient = BackgroundFillGradient(
    topColor = this.topColor,
    bottomColor = this.bottomColor,
    rotationAngle = this.rotationAngle
)

fun TdApi.BackgroundFillSolid.toDomain(): BackgroundFillSolid = BackgroundFillSolid(
    color = this.color
)

fun TdApi.BackgroundType.toDomain(): BackgroundType = when(this) {
    is TdApi.BackgroundTypeWallpaper -> this.toDomain()
    is TdApi.BackgroundTypePattern -> this.toDomain()
    is TdApi.BackgroundTypeFill -> this.toDomain()
    is TdApi.BackgroundTypeChatTheme -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.BackgroundTypeChatTheme.toDomain(): BackgroundTypeChatTheme = BackgroundTypeChatTheme(
    themeName = this.themeName
)

fun TdApi.BackgroundTypeFill.toDomain(): BackgroundTypeFill = BackgroundTypeFill(
    fill = this.fill.toDomain()
)

fun TdApi.BackgroundTypePattern.toDomain(): BackgroundTypePattern = BackgroundTypePattern(
    fill = this.fill.toDomain(),
    intensity = this.intensity,
    isInverted = this.isInverted,
    isMoving = this.isMoving
)

fun TdApi.BackgroundTypeWallpaper.toDomain(): BackgroundTypeWallpaper = BackgroundTypeWallpaper(
    isBlurred = this.isBlurred,
    isMoving = this.isMoving
)

fun TdApi.BotWriteAccessAllowReason.toDomain(): BotWriteAccessAllowReason = when(this) {
    is TdApi.BotWriteAccessAllowReasonConnectedWebsite -> this.toDomain()
    is TdApi.BotWriteAccessAllowReasonAddedToAttachmentMenu -> this.toDomain()
    is TdApi.BotWriteAccessAllowReasonLaunchedWebApp -> this.toDomain()
    is TdApi.BotWriteAccessAllowReasonAcceptedRequest -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.BotWriteAccessAllowReasonAcceptedRequest.toDomain(): BotWriteAccessAllowReasonAcceptedRequest = BotWriteAccessAllowReasonAcceptedRequest

fun TdApi.BotWriteAccessAllowReasonAddedToAttachmentMenu.toDomain(): BotWriteAccessAllowReasonAddedToAttachmentMenu = BotWriteAccessAllowReasonAddedToAttachmentMenu

fun TdApi.BotWriteAccessAllowReasonConnectedWebsite.toDomain(): BotWriteAccessAllowReasonConnectedWebsite = BotWriteAccessAllowReasonConnectedWebsite(
    domainName = this.domainName
)

fun TdApi.BotWriteAccessAllowReasonLaunchedWebApp.toDomain(): BotWriteAccessAllowReasonLaunchedWebApp = BotWriteAccessAllowReasonLaunchedWebApp(
    webApp = this.webApp.toDomain()
)

fun TdApi.BuiltInTheme.toDomain(): BuiltInTheme = when(this) {
    is TdApi.BuiltInThemeClassic -> this.toDomain()
    is TdApi.BuiltInThemeDay -> this.toDomain()
    is TdApi.BuiltInThemeNight -> this.toDomain()
    is TdApi.BuiltInThemeTinted -> this.toDomain()
    is TdApi.BuiltInThemeArctic -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.BuiltInThemeArctic.toDomain(): BuiltInThemeArctic = BuiltInThemeArctic

fun TdApi.BuiltInThemeClassic.toDomain(): BuiltInThemeClassic = BuiltInThemeClassic

fun TdApi.BuiltInThemeDay.toDomain(): BuiltInThemeDay = BuiltInThemeDay

fun TdApi.BuiltInThemeNight.toDomain(): BuiltInThemeNight = BuiltInThemeNight

fun TdApi.BuiltInThemeTinted.toDomain(): BuiltInThemeTinted = BuiltInThemeTinted

fun TdApi.CallDiscardReason.toDomain(): CallDiscardReason = when(this) {
    is TdApi.CallDiscardReasonEmpty -> this.toDomain()
    is TdApi.CallDiscardReasonMissed -> this.toDomain()
    is TdApi.CallDiscardReasonDeclined -> this.toDomain()
    is TdApi.CallDiscardReasonDisconnected -> this.toDomain()
    is TdApi.CallDiscardReasonHungUp -> this.toDomain()
    is TdApi.CallDiscardReasonUpgradeToGroupCall -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.CallDiscardReasonDeclined.toDomain(): CallDiscardReasonDeclined = CallDiscardReasonDeclined

fun TdApi.CallDiscardReasonDisconnected.toDomain(): CallDiscardReasonDisconnected = CallDiscardReasonDisconnected

fun TdApi.CallDiscardReasonEmpty.toDomain(): CallDiscardReasonEmpty = CallDiscardReasonEmpty

fun TdApi.CallDiscardReasonHungUp.toDomain(): CallDiscardReasonHungUp = CallDiscardReasonHungUp

fun TdApi.CallDiscardReasonMissed.toDomain(): CallDiscardReasonMissed = CallDiscardReasonMissed

fun TdApi.CallDiscardReasonUpgradeToGroupCall.toDomain(): CallDiscardReasonUpgradeToGroupCall = CallDiscardReasonUpgradeToGroupCall(
    inviteLink = this.inviteLink
)

fun TdApi.ChatBackground.toDomain(): ChatBackground = ChatBackground(
    background = this.background.toDomain(),
    darkThemeDimming = this.darkThemeDimming
)

fun TdApi.ChatPhoto.toDomain(): ChatPhoto = ChatPhoto(
    id = this.id,
    addedDate = this.addedDate,
    minithumbnail = this.minithumbnail?.toDomain(),
    sizes = this.sizes.map { it.toDomain() },
    animation = this.animation?.toDomain(),
    smallAnimation = this.smallAnimation?.toDomain(),
    sticker = this.sticker?.toDomain()
)

fun TdApi.ChatPhotoSticker.toDomain(): ChatPhotoSticker = ChatPhotoSticker(
    type = this.type.toDomain(),
    backgroundFill = this.backgroundFill.toDomain()
)

fun TdApi.ChatPhotoStickerType.toDomain(): ChatPhotoStickerType = when(this) {
    is TdApi.ChatPhotoStickerTypeRegularOrMask -> this.toDomain()
    is TdApi.ChatPhotoStickerTypeCustomEmoji -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatPhotoStickerTypeCustomEmoji.toDomain(): ChatPhotoStickerTypeCustomEmoji = ChatPhotoStickerTypeCustomEmoji(
    customEmojiId = this.customEmojiId
)

fun TdApi.ChatPhotoStickerTypeRegularOrMask.toDomain(): ChatPhotoStickerTypeRegularOrMask = ChatPhotoStickerTypeRegularOrMask(
    stickerSetId = this.stickerSetId,
    stickerId = this.stickerId
)

fun TdApi.ChatTheme.toDomain(): ChatTheme = when(this) {
    is TdApi.ChatThemeEmoji -> this.toDomain()
    is TdApi.ChatThemeGift -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ChatThemeEmoji.toDomain(): ChatThemeEmoji = ChatThemeEmoji(
    name = this.name
)

fun TdApi.ChatThemeGift.toDomain(): ChatThemeGift = ChatThemeGift(
    giftTheme = this.giftTheme.toDomain()
)

fun TdApi.Checklist.toDomain(): Checklist = Checklist(
    title = this.title.toDomain(),
    tasks = this.tasks.map { it.toDomain() },
    othersCanAddTasks = this.othersCanAddTasks,
    canAddTasks = this.canAddTasks,
    othersCanMarkTasksAsDone = this.othersCanMarkTasksAsDone,
    canMarkTasksAsDone = this.canMarkTasksAsDone
)

fun TdApi.ChecklistTask.toDomain(): ChecklistTask = ChecklistTask(
    id = this.id,
    text = this.text.toDomain(),
    completedByUserId = this.completedByUserId,
    completionDate = this.completionDate
)

fun TdApi.Contact.toDomain(): Contact = Contact(
    phoneNumber = this.phoneNumber,
    firstName = this.firstName,
    lastName = this.lastName,
    vcard = this.vcard,
    userId = this.userId
)

fun TdApi.DatedFile.toDomain(): DatedFile = DatedFile(
    file = this.file.toDomain(),
    date = this.date
)

fun TdApi.DiceStickers.toDomain(): DiceStickers = when(this) {
    is TdApi.DiceStickersRegular -> this.toDomain()
    is TdApi.DiceStickersSlotMachine -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.DiceStickersRegular.toDomain(): DiceStickersRegular = DiceStickersRegular(
    sticker = this.sticker.toDomain()
)

fun TdApi.DiceStickersSlotMachine.toDomain(): DiceStickersSlotMachine = DiceStickersSlotMachine(
    background = this.background.toDomain(),
    lever = this.lever.toDomain(),
    leftReel = this.leftReel.toDomain(),
    centerReel = this.centerReel.toDomain(),
    rightReel = this.rightReel.toDomain()
)

fun TdApi.Document.toDomain(): Document = Document(
    fileName = this.fileName,
    mimeType = this.mimeType,
    minithumbnail = this.minithumbnail?.toDomain(),
    thumbnail = this.thumbnail?.toDomain(),
    document = this.document.toDomain()
)

fun TdApi.EncryptedCredentials.toDomain(): EncryptedCredentials = EncryptedCredentials(
    data = this.data,
    hash = this.hash,
    secret = this.secret
)

fun TdApi.EncryptedPassportElement.toDomain(): EncryptedPassportElement = EncryptedPassportElement(
    type = this.type.toDomain(),
    data = this.data,
    frontSide = this.frontSide.toDomain(),
    reverseSide = this.reverseSide?.toDomain(),
    selfie = this.selfie?.toDomain(),
    translation = this.translation.map { it.toDomain() },
    files = this.files.map { it.toDomain() },
    value = this.value,
    hash = this.hash
)

fun TdApi.Error.toDomain(): Error = Error(
    code = this.code,
    message = this.message
)

fun TdApi.File.toDomain(): File = File(
    id = this.id,
    size = this.size,
    expectedSize = this.expectedSize,
    local = this.local.toDomain(),
    remote = this.remote.toDomain()
)

fun TdApi.FormattedText.toDomain(): FormattedText = FormattedText(
    text = this.text,
    entities = this.entities.map { it.toDomain() }
)

fun TdApi.ForumTopicIcon.toDomain(): ForumTopicIcon = ForumTopicIcon(
    color = this.color,
    customEmojiId = this.customEmojiId
)

fun TdApi.Game.toDomain(): Game = Game(
    id = this.id,
    shortName = this.shortName,
    title = this.title,
    text = this.text.toDomain(),
    description = this.description,
    photo = this.photo.toDomain(),
    animation = this.animation?.toDomain()
)

fun TdApi.Gift.toDomain(): Gift = Gift(
    id = this.id,
    publisherChatId = this.publisherChatId,
    sticker = this.sticker.toDomain(),
    starCount = this.starCount,
    defaultSellStarCount = this.defaultSellStarCount,
    upgradeStarCount = this.upgradeStarCount,
    isForBirthday = this.isForBirthday,
    isPremium = this.isPremium,
    nextSendDate = this.nextSendDate,
    userLimits = this.userLimits?.toDomain(),
    overallLimits = this.overallLimits?.toDomain(),
    firstSendDate = this.firstSendDate,
    lastSendDate = this.lastSendDate
)

fun TdApi.GiftChatTheme.toDomain(): GiftChatTheme = GiftChatTheme(
    gift = this.gift.toDomain(),
    lightSettings = this.lightSettings.toDomain(),
    darkSettings = this.darkSettings.toDomain()
)

fun TdApi.GiftPurchaseLimits.toDomain(): GiftPurchaseLimits = GiftPurchaseLimits(
    totalCount = this.totalCount,
    remainingCount = this.remainingCount
)

fun TdApi.GiftResaleParameters.toDomain(): GiftResaleParameters = GiftResaleParameters(
    starCount = this.starCount,
    toncoinCentCount = this.toncoinCentCount,
    toncoinOnly = this.toncoinOnly
)

fun TdApi.GiftResalePrice.toDomain(): GiftResalePrice = when(this) {
    is TdApi.GiftResalePriceStar -> this.toDomain()
    is TdApi.GiftResalePriceTon -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.GiftResalePriceStar.toDomain(): GiftResalePriceStar = GiftResalePriceStar(
    starCount = this.starCount
)

fun TdApi.GiftResalePriceTon.toDomain(): GiftResalePriceTon = GiftResalePriceTon(
    toncoinCentCount = this.toncoinCentCount
)

fun TdApi.GiveawayParameters.toDomain(): GiveawayParameters = GiveawayParameters(
    boostedChatId = this.boostedChatId,
    additionalChatIds = this.additionalChatIds,
    winnersSelectionDate = this.winnersSelectionDate,
    onlyNewMembers = this.onlyNewMembers,
    hasPublicWinners = this.hasPublicWinners,
    countryCodes = this.countryCodes.toList(),
    prizeDescription = this.prizeDescription
)

fun TdApi.GiveawayPrize.toDomain(): GiveawayPrize = when(this) {
    is TdApi.GiveawayPrizePremium -> this.toDomain()
    is TdApi.GiveawayPrizeStars -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.GiveawayPrizePremium.toDomain(): GiveawayPrizePremium = GiveawayPrizePremium(
    monthCount = this.monthCount
)

fun TdApi.GiveawayPrizeStars.toDomain(): GiveawayPrizeStars = GiveawayPrizeStars(
    starCount = this.starCount
)

fun TdApi.InviteLinkChatType.toDomain(): InviteLinkChatType = when(this) {
    is TdApi.InviteLinkChatTypeBasicGroup -> this.toDomain()
    is TdApi.InviteLinkChatTypeSupergroup -> this.toDomain()
    is TdApi.InviteLinkChatTypeChannel -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InviteLinkChatTypeBasicGroup.toDomain(): InviteLinkChatTypeBasicGroup = InviteLinkChatTypeBasicGroup

fun TdApi.InviteLinkChatTypeChannel.toDomain(): InviteLinkChatTypeChannel = InviteLinkChatTypeChannel

fun TdApi.InviteLinkChatTypeSupergroup.toDomain(): InviteLinkChatTypeSupergroup = InviteLinkChatTypeSupergroup

fun TdApi.LinkPreview.toDomain(): LinkPreview = LinkPreview(
    url = this.url,
    displayUrl = this.displayUrl,
    siteName = this.siteName,
    title = this.title,
    description = this.description.toDomain(),
    author = this.author,
    type = this.type.toDomain(),
    hasLargeMedia = this.hasLargeMedia,
    showLargeMedia = this.showLargeMedia,
    showMediaAboveDescription = this.showMediaAboveDescription,
    skipConfirmation = this.skipConfirmation,
    showAboveText = this.showAboveText,
    instantViewVersion = this.instantViewVersion
)

fun TdApi.LinkPreviewAlbumMedia.toDomain(): LinkPreviewAlbumMedia = when(this) {
    is TdApi.LinkPreviewAlbumMediaPhoto -> this.toDomain()
    is TdApi.LinkPreviewAlbumMediaVideo -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.LinkPreviewAlbumMediaPhoto.toDomain(): LinkPreviewAlbumMediaPhoto = LinkPreviewAlbumMediaPhoto(
    photo = this.photo.toDomain()
)

fun TdApi.LinkPreviewAlbumMediaVideo.toDomain(): LinkPreviewAlbumMediaVideo = LinkPreviewAlbumMediaVideo(
    video = this.video.toDomain()
)

fun TdApi.LinkPreviewOptions.toDomain(): LinkPreviewOptions = LinkPreviewOptions(
    isDisabled = this.isDisabled,
    url = this.url,
    forceSmallMedia = this.forceSmallMedia,
    forceLargeMedia = this.forceLargeMedia,
    showAboveText = this.showAboveText
)

fun TdApi.LinkPreviewType.toDomain(): LinkPreviewType = when(this) {
    is TdApi.LinkPreviewTypeAlbum -> this.toDomain()
    is TdApi.LinkPreviewTypeAnimation -> this.toDomain()
    is TdApi.LinkPreviewTypeApp -> this.toDomain()
    is TdApi.LinkPreviewTypeArticle -> this.toDomain()
    is TdApi.LinkPreviewTypeAudio -> this.toDomain()
    is TdApi.LinkPreviewTypeBackground -> this.toDomain()
    is TdApi.LinkPreviewTypeChannelBoost -> this.toDomain()
    is TdApi.LinkPreviewTypeChat -> this.toDomain()
    is TdApi.LinkPreviewTypeDirectMessagesChat -> this.toDomain()
    is TdApi.LinkPreviewTypeDocument -> this.toDomain()
    is TdApi.LinkPreviewTypeEmbeddedAnimationPlayer -> this.toDomain()
    is TdApi.LinkPreviewTypeEmbeddedAudioPlayer -> this.toDomain()
    is TdApi.LinkPreviewTypeEmbeddedVideoPlayer -> this.toDomain()
    is TdApi.LinkPreviewTypeExternalAudio -> this.toDomain()
    is TdApi.LinkPreviewTypeExternalVideo -> this.toDomain()
    is TdApi.LinkPreviewTypeGiftCollection -> this.toDomain()
    is TdApi.LinkPreviewTypeGroupCall -> this.toDomain()
    is TdApi.LinkPreviewTypeInvoice -> this.toDomain()
    is TdApi.LinkPreviewTypeMessage -> this.toDomain()
    is TdApi.LinkPreviewTypePhoto -> this.toDomain()
    is TdApi.LinkPreviewTypePremiumGiftCode -> this.toDomain()
    is TdApi.LinkPreviewTypeShareableChatFolder -> this.toDomain()
    is TdApi.LinkPreviewTypeSticker -> this.toDomain()
    is TdApi.LinkPreviewTypeStickerSet -> this.toDomain()
    is TdApi.LinkPreviewTypeStory -> this.toDomain()
    is TdApi.LinkPreviewTypeStoryAlbum -> this.toDomain()
    is TdApi.LinkPreviewTypeSupergroupBoost -> this.toDomain()
    is TdApi.LinkPreviewTypeTheme -> this.toDomain()
    is TdApi.LinkPreviewTypeUnsupported -> this.toDomain()
    is TdApi.LinkPreviewTypeUpgradedGift -> this.toDomain()
    is TdApi.LinkPreviewTypeUser -> this.toDomain()
    is TdApi.LinkPreviewTypeVideo -> this.toDomain()
    is TdApi.LinkPreviewTypeVideoChat -> this.toDomain()
    is TdApi.LinkPreviewTypeVideoNote -> this.toDomain()
    is TdApi.LinkPreviewTypeVoiceNote -> this.toDomain()
    is TdApi.LinkPreviewTypeWebApp -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.LinkPreviewTypeAlbum.toDomain(): LinkPreviewTypeAlbum = LinkPreviewTypeAlbum(
    media = this.media.map { it.toDomain() },
    caption = this.caption
)

fun TdApi.LinkPreviewTypeAnimation.toDomain(): LinkPreviewTypeAnimation = LinkPreviewTypeAnimation(
    animation = this.animation.toDomain()
)

fun TdApi.LinkPreviewTypeApp.toDomain(): LinkPreviewTypeApp = LinkPreviewTypeApp(
    photo = this.photo.toDomain()
)

fun TdApi.LinkPreviewTypeArticle.toDomain(): LinkPreviewTypeArticle = LinkPreviewTypeArticle(
    photo = this.photo?.toDomain()
)

fun TdApi.LinkPreviewTypeAudio.toDomain(): LinkPreviewTypeAudio = LinkPreviewTypeAudio(
    audio = this.audio.toDomain()
)

fun TdApi.LinkPreviewTypeBackground.toDomain(): LinkPreviewTypeBackground = LinkPreviewTypeBackground(
    document = this.document?.toDomain(),
    backgroundType = this.backgroundType?.toDomain()
)

fun TdApi.LinkPreviewTypeChannelBoost.toDomain(): LinkPreviewTypeChannelBoost = LinkPreviewTypeChannelBoost(
    photo = this.photo?.toDomain()
)

fun TdApi.LinkPreviewTypeChat.toDomain(): LinkPreviewTypeChat = LinkPreviewTypeChat(
    type = this.type.toDomain(),
    photo = this.photo?.toDomain(),
    createsJoinRequest = this.createsJoinRequest
)

fun TdApi.LinkPreviewTypeDirectMessagesChat.toDomain(): LinkPreviewTypeDirectMessagesChat = LinkPreviewTypeDirectMessagesChat(
    photo = this.photo?.toDomain()
)

fun TdApi.LinkPreviewTypeDocument.toDomain(): LinkPreviewTypeDocument = LinkPreviewTypeDocument(
    document = this.document.toDomain()
)

fun TdApi.LinkPreviewTypeEmbeddedAnimationPlayer.toDomain(): LinkPreviewTypeEmbeddedAnimationPlayer = LinkPreviewTypeEmbeddedAnimationPlayer(
    url = this.url,
    thumbnail = this.thumbnail?.toDomain(),
    duration = this.duration,
    width = this.width,
    height = this.height
)

fun TdApi.LinkPreviewTypeEmbeddedAudioPlayer.toDomain(): LinkPreviewTypeEmbeddedAudioPlayer = LinkPreviewTypeEmbeddedAudioPlayer(
    url = this.url,
    thumbnail = this.thumbnail?.toDomain(),
    duration = this.duration,
    width = this.width,
    height = this.height
)

fun TdApi.LinkPreviewTypeEmbeddedVideoPlayer.toDomain(): LinkPreviewTypeEmbeddedVideoPlayer = LinkPreviewTypeEmbeddedVideoPlayer(
    url = this.url,
    thumbnail = this.thumbnail?.toDomain(),
    duration = this.duration,
    width = this.width,
    height = this.height
)

fun TdApi.LinkPreviewTypeExternalAudio.toDomain(): LinkPreviewTypeExternalAudio = LinkPreviewTypeExternalAudio(
    url = this.url,
    mimeType = this.mimeType,
    duration = this.duration
)

fun TdApi.LinkPreviewTypeExternalVideo.toDomain(): LinkPreviewTypeExternalVideo = LinkPreviewTypeExternalVideo(
    url = this.url,
    mimeType = this.mimeType,
    width = this.width,
    height = this.height,
    duration = this.duration
)

fun TdApi.LinkPreviewTypeGiftCollection.toDomain(): LinkPreviewTypeGiftCollection = LinkPreviewTypeGiftCollection(
    icons = this.icons.map { it.toDomain() }
)

fun TdApi.LinkPreviewTypeGroupCall.toDomain(): LinkPreviewTypeGroupCall = LinkPreviewTypeGroupCall

fun TdApi.LinkPreviewTypeInvoice.toDomain(): LinkPreviewTypeInvoice = LinkPreviewTypeInvoice

fun TdApi.LinkPreviewTypeMessage.toDomain(): LinkPreviewTypeMessage = LinkPreviewTypeMessage

fun TdApi.LinkPreviewTypePhoto.toDomain(): LinkPreviewTypePhoto = LinkPreviewTypePhoto(
    photo = this.photo.toDomain()
)

fun TdApi.LinkPreviewTypePremiumGiftCode.toDomain(): LinkPreviewTypePremiumGiftCode = LinkPreviewTypePremiumGiftCode

fun TdApi.LinkPreviewTypeShareableChatFolder.toDomain(): LinkPreviewTypeShareableChatFolder = LinkPreviewTypeShareableChatFolder

fun TdApi.LinkPreviewTypeSticker.toDomain(): LinkPreviewTypeSticker = LinkPreviewTypeSticker(
    sticker = this.sticker.toDomain()
)

fun TdApi.LinkPreviewTypeStickerSet.toDomain(): LinkPreviewTypeStickerSet = LinkPreviewTypeStickerSet(
    stickers = this.stickers.map { it.toDomain() }
)

fun TdApi.LinkPreviewTypeStory.toDomain(): LinkPreviewTypeStory = LinkPreviewTypeStory(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId
)

fun TdApi.LinkPreviewTypeStoryAlbum.toDomain(): LinkPreviewTypeStoryAlbum = LinkPreviewTypeStoryAlbum(
    photoIcon = this.photoIcon?.toDomain(),
    videoIcon = this.videoIcon?.toDomain()
)

fun TdApi.LinkPreviewTypeSupergroupBoost.toDomain(): LinkPreviewTypeSupergroupBoost = LinkPreviewTypeSupergroupBoost(
    photo = this.photo?.toDomain()
)

fun TdApi.LinkPreviewTypeTheme.toDomain(): LinkPreviewTypeTheme = LinkPreviewTypeTheme(
    documents = this.documents.map { it.toDomain() },
    settings = this.settings?.toDomain()
)

fun TdApi.LinkPreviewTypeUnsupported.toDomain(): LinkPreviewTypeUnsupported = LinkPreviewTypeUnsupported

fun TdApi.LinkPreviewTypeUpgradedGift.toDomain(): LinkPreviewTypeUpgradedGift = LinkPreviewTypeUpgradedGift(
    gift = this.gift.toDomain()
)

fun TdApi.LinkPreviewTypeUser.toDomain(): LinkPreviewTypeUser = LinkPreviewTypeUser(
    photo = this.photo?.toDomain(),
    isBot = this.isBot
)

fun TdApi.LinkPreviewTypeVideo.toDomain(): LinkPreviewTypeVideo = LinkPreviewTypeVideo(
    video = this.video.toDomain(),
    cover = this.cover?.toDomain(),
    startTimestamp = this.startTimestamp
)

fun TdApi.LinkPreviewTypeVideoChat.toDomain(): LinkPreviewTypeVideoChat = LinkPreviewTypeVideoChat(
    photo = this.photo?.toDomain(),
    isLiveStream = this.isLiveStream,
    joinsAsSpeaker = this.joinsAsSpeaker
)

fun TdApi.LinkPreviewTypeVideoNote.toDomain(): LinkPreviewTypeVideoNote = LinkPreviewTypeVideoNote(
    videoNote = this.videoNote.toDomain()
)

fun TdApi.LinkPreviewTypeVoiceNote.toDomain(): LinkPreviewTypeVoiceNote = LinkPreviewTypeVoiceNote(
    voiceNote = this.voiceNote.toDomain()
)

fun TdApi.LinkPreviewTypeWebApp.toDomain(): LinkPreviewTypeWebApp = LinkPreviewTypeWebApp(
    photo = this.photo?.toDomain()
)

fun TdApi.LocalFile.toDomain(): LocalFile = LocalFile(
    path = this.path,
    canBeDownloaded = this.canBeDownloaded,
    canBeDeleted = this.canBeDeleted,
    isDownloadingActive = this.isDownloadingActive,
    isDownloadingCompleted = this.isDownloadingCompleted,
    downloadOffset = this.downloadOffset,
    downloadedPrefixSize = this.downloadedPrefixSize,
    downloadedSize = this.downloadedSize
)

fun TdApi.Location.toDomain(): Location = Location(
    latitude = this.latitude,
    longitude = this.longitude,
    horizontalAccuracy = this.horizontalAccuracy
)

fun TdApi.MaskPoint.toDomain(): MaskPoint = when(this) {
    is TdApi.MaskPointForehead -> this.toDomain()
    is TdApi.MaskPointEyes -> this.toDomain()
    is TdApi.MaskPointMouth -> this.toDomain()
    is TdApi.MaskPointChin -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MaskPointChin.toDomain(): MaskPointChin = MaskPointChin

fun TdApi.MaskPointEyes.toDomain(): MaskPointEyes = MaskPointEyes

fun TdApi.MaskPointForehead.toDomain(): MaskPointForehead = MaskPointForehead

fun TdApi.MaskPointMouth.toDomain(): MaskPointMouth = MaskPointMouth

fun TdApi.MaskPosition.toDomain(): MaskPosition = MaskPosition(
    point = this.point.toDomain(),
    xShift = this.xShift,
    yShift = this.yShift,
    scale = this.scale
)

fun TdApi.MessageAnimatedEmoji.toDomain(): MessageAnimatedEmoji = MessageAnimatedEmoji(
    animatedEmoji = this.animatedEmoji.toDomain(),
    emoji = this.emoji
)

fun TdApi.MessageAnimation.toDomain(): MessageAnimation = MessageAnimation(
    animation = this.animation.toDomain(),
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia,
    hasSpoiler = this.hasSpoiler,
    isSecret = this.isSecret
)

fun TdApi.MessageAudio.toDomain(): MessageAudio = MessageAudio(
    audio = this.audio.toDomain(),
    caption = this.caption.toDomain()
)

fun TdApi.MessageBasicGroupChatCreate.toDomain(): MessageBasicGroupChatCreate = MessageBasicGroupChatCreate(
    title = this.title,
    memberUserIds = this.memberUserIds
)

fun TdApi.MessageBotWriteAccessAllowed.toDomain(): MessageBotWriteAccessAllowed = MessageBotWriteAccessAllowed(
    reason = this.reason.toDomain()
)

fun TdApi.MessageCall.toDomain(): MessageCall = MessageCall(
    isVideo = this.isVideo,
    discardReason = this.discardReason.toDomain(),
    duration = this.duration
)

fun TdApi.MessageChatAddMembers.toDomain(): MessageChatAddMembers = MessageChatAddMembers(
    memberUserIds = this.memberUserIds
)

fun TdApi.MessageChatBoost.toDomain(): MessageChatBoost = MessageChatBoost(
    boostCount = this.boostCount
)

fun TdApi.MessageChatChangePhoto.toDomain(): MessageChatChangePhoto = MessageChatChangePhoto(
    photo = this.photo.toDomain()
)

fun TdApi.MessageChatChangeTitle.toDomain(): MessageChatChangeTitle = MessageChatChangeTitle(
    title = this.title
)

fun TdApi.MessageChatDeleteMember.toDomain(): MessageChatDeleteMember = MessageChatDeleteMember(
    userId = this.userId
)

fun TdApi.MessageChatDeletePhoto.toDomain(): MessageChatDeletePhoto = MessageChatDeletePhoto

fun TdApi.MessageChatJoinByLink.toDomain(): MessageChatJoinByLink = MessageChatJoinByLink

fun TdApi.MessageChatJoinByRequest.toDomain(): MessageChatJoinByRequest = MessageChatJoinByRequest

fun TdApi.MessageChatSetBackground.toDomain(): MessageChatSetBackground = MessageChatSetBackground(
    oldBackgroundMessageId = this.oldBackgroundMessageId,
    background = this.background.toDomain(),
    onlyForSelf = this.onlyForSelf
)

fun TdApi.MessageChatSetMessageAutoDeleteTime.toDomain(): MessageChatSetMessageAutoDeleteTime = MessageChatSetMessageAutoDeleteTime(
    messageAutoDeleteTime = this.messageAutoDeleteTime,
    fromUserId = this.fromUserId
)

fun TdApi.MessageChatSetTheme.toDomain(): MessageChatSetTheme = MessageChatSetTheme(
    theme = this.theme?.toDomain()
)

fun TdApi.MessageChatShared.toDomain(): MessageChatShared = MessageChatShared(
    chat = this.chat.toDomain(),
    buttonId = this.buttonId
)

fun TdApi.MessageChatUpgradeFrom.toDomain(): MessageChatUpgradeFrom = MessageChatUpgradeFrom(
    title = this.title,
    basicGroupId = this.basicGroupId
)

fun TdApi.MessageChatUpgradeTo.toDomain(): MessageChatUpgradeTo = MessageChatUpgradeTo(
    supergroupId = this.supergroupId
)

fun TdApi.MessageChecklist.toDomain(): MessageChecklist = MessageChecklist(
    list = this.list.toDomain()
)

fun TdApi.MessageChecklistTasksAdded.toDomain(): MessageChecklistTasksAdded = MessageChecklistTasksAdded(
    checklistMessageId = this.checklistMessageId,
    tasks = this.tasks.map { it.toDomain() }
)

fun TdApi.MessageChecklistTasksDone.toDomain(): MessageChecklistTasksDone = MessageChecklistTasksDone(
    checklistMessageId = this.checklistMessageId,
    markedAsDoneTaskIds = this.markedAsDoneTaskIds,
    markedAsNotDoneTaskIds = this.markedAsNotDoneTaskIds
)

fun TdApi.MessageContact.toDomain(): MessageContact = MessageContact(
    contact = this.contact.toDomain()
)

fun TdApi.MessageContactRegistered.toDomain(): MessageContactRegistered = MessageContactRegistered

fun TdApi.MessageContent.toDomain(): MessageContent = when(this) {
    is TdApi.MessageText -> this.toDomain()
    is TdApi.MessageAnimation -> this.toDomain()
    is TdApi.MessageAudio -> this.toDomain()
    is TdApi.MessageDocument -> this.toDomain()
    is TdApi.MessagePaidMedia -> this.toDomain()
    is TdApi.MessagePhoto -> this.toDomain()
    is TdApi.MessageSticker -> this.toDomain()
    is TdApi.MessageVideo -> this.toDomain()
    is TdApi.MessageVideoNote -> this.toDomain()
    is TdApi.MessageVoiceNote -> this.toDomain()
    is TdApi.MessageExpiredPhoto -> this.toDomain()
    is TdApi.MessageExpiredVideo -> this.toDomain()
    is TdApi.MessageExpiredVideoNote -> this.toDomain()
    is TdApi.MessageExpiredVoiceNote -> this.toDomain()
    is TdApi.MessageLocation -> this.toDomain()
    is TdApi.MessageVenue -> this.toDomain()
    is TdApi.MessageContact -> this.toDomain()
    is TdApi.MessageAnimatedEmoji -> this.toDomain()
    is TdApi.MessageDice -> this.toDomain()
    is TdApi.MessageGame -> this.toDomain()
    is TdApi.MessagePoll -> this.toDomain()
    is TdApi.MessageStory -> this.toDomain()
    is TdApi.MessageChecklist -> this.toDomain()
    is TdApi.MessageInvoice -> this.toDomain()
    is TdApi.MessageCall -> this.toDomain()
    is TdApi.MessageGroupCall -> this.toDomain()
    is TdApi.MessageVideoChatScheduled -> this.toDomain()
    is TdApi.MessageVideoChatStarted -> this.toDomain()
    is TdApi.MessageVideoChatEnded -> this.toDomain()
    is TdApi.MessageInviteVideoChatParticipants -> this.toDomain()
    is TdApi.MessageBasicGroupChatCreate -> this.toDomain()
    is TdApi.MessageSupergroupChatCreate -> this.toDomain()
    is TdApi.MessageChatChangeTitle -> this.toDomain()
    is TdApi.MessageChatChangePhoto -> this.toDomain()
    is TdApi.MessageChatDeletePhoto -> this.toDomain()
    is TdApi.MessageChatAddMembers -> this.toDomain()
    is TdApi.MessageChatJoinByLink -> this.toDomain()
    is TdApi.MessageChatJoinByRequest -> this.toDomain()
    is TdApi.MessageChatDeleteMember -> this.toDomain()
    is TdApi.MessageChatUpgradeTo -> this.toDomain()
    is TdApi.MessageChatUpgradeFrom -> this.toDomain()
    is TdApi.MessagePinMessage -> this.toDomain()
    is TdApi.MessageScreenshotTaken -> this.toDomain()
    is TdApi.MessageChatSetBackground -> this.toDomain()
    is TdApi.MessageChatSetTheme -> this.toDomain()
    is TdApi.MessageChatSetMessageAutoDeleteTime -> this.toDomain()
    is TdApi.MessageChatBoost -> this.toDomain()
    is TdApi.MessageForumTopicCreated -> this.toDomain()
    is TdApi.MessageForumTopicEdited -> this.toDomain()
    is TdApi.MessageForumTopicIsClosedToggled -> this.toDomain()
    is TdApi.MessageForumTopicIsHiddenToggled -> this.toDomain()
    is TdApi.MessageSuggestProfilePhoto -> this.toDomain()
    is TdApi.MessageCustomServiceAction -> this.toDomain()
    is TdApi.MessageGameScore -> this.toDomain()
    is TdApi.MessagePaymentSuccessful -> this.toDomain()
    is TdApi.MessagePaymentSuccessfulBot -> this.toDomain()
    is TdApi.MessagePaymentRefunded -> this.toDomain()
    is TdApi.MessageGiftedPremium -> this.toDomain()
    is TdApi.MessagePremiumGiftCode -> this.toDomain()
    is TdApi.MessageGiveawayCreated -> this.toDomain()
    is TdApi.MessageGiveaway -> this.toDomain()
    is TdApi.MessageGiveawayCompleted -> this.toDomain()
    is TdApi.MessageGiveawayWinners -> this.toDomain()
    is TdApi.MessageGiftedStars -> this.toDomain()
    is TdApi.MessageGiftedTon -> this.toDomain()
    is TdApi.MessageGiveawayPrizeStars -> this.toDomain()
    is TdApi.MessageGift -> this.toDomain()
    is TdApi.MessageUpgradedGift -> this.toDomain()
    is TdApi.MessageRefundedUpgradedGift -> this.toDomain()
    is TdApi.MessagePaidMessagesRefunded -> this.toDomain()
    is TdApi.MessagePaidMessagePriceChanged -> this.toDomain()
    is TdApi.MessageDirectMessagePriceChanged -> this.toDomain()
    is TdApi.MessageChecklistTasksDone -> this.toDomain()
    is TdApi.MessageChecklistTasksAdded -> this.toDomain()
    is TdApi.MessageSuggestedPostApprovalFailed -> this.toDomain()
    is TdApi.MessageSuggestedPostApproved -> this.toDomain()
    is TdApi.MessageSuggestedPostDeclined -> this.toDomain()
    is TdApi.MessageSuggestedPostPaid -> this.toDomain()
    is TdApi.MessageSuggestedPostRefunded -> this.toDomain()
    is TdApi.MessageContactRegistered -> this.toDomain()
    is TdApi.MessageUsersShared -> this.toDomain()
    is TdApi.MessageChatShared -> this.toDomain()
    is TdApi.MessageBotWriteAccessAllowed -> this.toDomain()
    is TdApi.MessageWebAppDataSent -> this.toDomain()
    is TdApi.MessageWebAppDataReceived -> this.toDomain()
    is TdApi.MessagePassportDataSent -> this.toDomain()
    is TdApi.MessagePassportDataReceived -> this.toDomain()
    is TdApi.MessageProximityAlertTriggered -> this.toDomain()
    is TdApi.MessageUnsupported -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageCustomServiceAction.toDomain(): MessageCustomServiceAction = MessageCustomServiceAction(
    text = this.text
)

fun TdApi.MessageDice.toDomain(): MessageDice = MessageDice(
    initialState = this.initialState?.toDomain(),
    finalState = this.finalState?.toDomain(),
    emoji = this.emoji,
    value = this.value,
    successAnimationFrameNumber = this.successAnimationFrameNumber
)

fun TdApi.MessageDirectMessagePriceChanged.toDomain(): MessageDirectMessagePriceChanged = MessageDirectMessagePriceChanged(
    isEnabled = this.isEnabled,
    paidMessageStarCount = this.paidMessageStarCount
)

fun TdApi.MessageDocument.toDomain(): MessageDocument = MessageDocument(
    document = this.document.toDomain(),
    caption = this.caption.toDomain()
)

fun TdApi.MessageExpiredPhoto.toDomain(): MessageExpiredPhoto = MessageExpiredPhoto

fun TdApi.MessageExpiredVideo.toDomain(): MessageExpiredVideo = MessageExpiredVideo

fun TdApi.MessageExpiredVideoNote.toDomain(): MessageExpiredVideoNote = MessageExpiredVideoNote

fun TdApi.MessageExpiredVoiceNote.toDomain(): MessageExpiredVoiceNote = MessageExpiredVoiceNote

fun TdApi.MessageForumTopicCreated.toDomain(): MessageForumTopicCreated = MessageForumTopicCreated(
    name = this.name,
    icon = this.icon.toDomain()
)

fun TdApi.MessageForumTopicEdited.toDomain(): MessageForumTopicEdited = MessageForumTopicEdited(
    name = this.name,
    editIconCustomEmojiId = this.editIconCustomEmojiId,
    iconCustomEmojiId = this.iconCustomEmojiId
)

fun TdApi.MessageForumTopicIsClosedToggled.toDomain(): MessageForumTopicIsClosedToggled = MessageForumTopicIsClosedToggled(
    isClosed = this.isClosed
)

fun TdApi.MessageForumTopicIsHiddenToggled.toDomain(): MessageForumTopicIsHiddenToggled = MessageForumTopicIsHiddenToggled(
    isHidden = this.isHidden
)

fun TdApi.MessageGame.toDomain(): MessageGame = MessageGame(
    game = this.game.toDomain()
)

fun TdApi.MessageGameScore.toDomain(): MessageGameScore = MessageGameScore(
    gameMessageId = this.gameMessageId,
    gameId = this.gameId,
    score = this.score
)

fun TdApi.MessageGift.toDomain(): MessageGift = MessageGift(
    gift = this.gift.toDomain(),
    senderId = this.senderId?.toDomain(),
    receiverId = this.receiverId.toDomain(),
    receivedGiftId = this.receivedGiftId,
    text = this.text.toDomain(),
    sellStarCount = this.sellStarCount,
    prepaidUpgradeStarCount = this.prepaidUpgradeStarCount,
    isUpgradeSeparate = this.isUpgradeSeparate,
    isPrivate = this.isPrivate,
    isSaved = this.isSaved,
    isPrepaidUpgrade = this.isPrepaidUpgrade,
    canBeUpgraded = this.canBeUpgraded,
    wasConverted = this.wasConverted,
    wasUpgraded = this.wasUpgraded,
    wasRefunded = this.wasRefunded,
    upgradedReceivedGiftId = this.upgradedReceivedGiftId,
    prepaidUpgradeHash = this.prepaidUpgradeHash
)

fun TdApi.MessageGiftedPremium.toDomain(): MessageGiftedPremium = MessageGiftedPremium(
    gifterUserId = this.gifterUserId,
    receiverUserId = this.receiverUserId,
    text = this.text.toDomain(),
    currency = this.currency,
    amount = this.amount,
    cryptocurrency = this.cryptocurrency,
    cryptocurrencyAmount = this.cryptocurrencyAmount,
    monthCount = this.monthCount,
    sticker = this.sticker?.toDomain()
)

fun TdApi.MessageGiftedStars.toDomain(): MessageGiftedStars = MessageGiftedStars(
    gifterUserId = this.gifterUserId,
    receiverUserId = this.receiverUserId,
    currency = this.currency,
    amount = this.amount,
    cryptocurrency = this.cryptocurrency,
    cryptocurrencyAmount = this.cryptocurrencyAmount,
    starCount = this.starCount,
    transactionId = this.transactionId,
    sticker = this.sticker?.toDomain()
)

fun TdApi.MessageGiftedTon.toDomain(): MessageGiftedTon = MessageGiftedTon(
    gifterUserId = this.gifterUserId,
    receiverUserId = this.receiverUserId,
    tonAmount = this.tonAmount,
    transactionId = this.transactionId,
    sticker = this.sticker?.toDomain()
)

fun TdApi.MessageGiveaway.toDomain(): MessageGiveaway = MessageGiveaway(
    parameters = this.parameters.toDomain(),
    winnerCount = this.winnerCount,
    prize = this.prize.toDomain(),
    sticker = this.sticker?.toDomain()
)

fun TdApi.MessageGiveawayCompleted.toDomain(): MessageGiveawayCompleted = MessageGiveawayCompleted(
    giveawayMessageId = this.giveawayMessageId,
    winnerCount = this.winnerCount,
    isStarGiveaway = this.isStarGiveaway,
    unclaimedPrizeCount = this.unclaimedPrizeCount
)

fun TdApi.MessageGiveawayCreated.toDomain(): MessageGiveawayCreated = MessageGiveawayCreated(
    starCount = this.starCount
)

fun TdApi.MessageGiveawayPrizeStars.toDomain(): MessageGiveawayPrizeStars = MessageGiveawayPrizeStars(
    starCount = this.starCount,
    transactionId = this.transactionId,
    boostedChatId = this.boostedChatId,
    giveawayMessageId = this.giveawayMessageId,
    isUnclaimed = this.isUnclaimed,
    sticker = this.sticker?.toDomain()
)

fun TdApi.MessageGiveawayWinners.toDomain(): MessageGiveawayWinners = MessageGiveawayWinners(
    boostedChatId = this.boostedChatId,
    giveawayMessageId = this.giveawayMessageId,
    additionalChatCount = this.additionalChatCount,
    actualWinnersSelectionDate = this.actualWinnersSelectionDate,
    onlyNewMembers = this.onlyNewMembers,
    wasRefunded = this.wasRefunded,
    prize = this.prize.toDomain(),
    prizeDescription = this.prizeDescription,
    winnerCount = this.winnerCount,
    winnerUserIds = this.winnerUserIds,
    unclaimedPrizeCount = this.unclaimedPrizeCount
)

fun TdApi.MessageGroupCall.toDomain(): MessageGroupCall = MessageGroupCall(
    isActive = this.isActive,
    wasMissed = this.wasMissed,
    isVideo = this.isVideo,
    duration = this.duration,
    otherParticipantIds = this.otherParticipantIds.map { it.toDomain() }
)

fun TdApi.MessageInviteVideoChatParticipants.toDomain(): MessageInviteVideoChatParticipants = MessageInviteVideoChatParticipants(
    groupCallId = this.groupCallId,
    userIds = this.userIds
)

fun TdApi.MessageInvoice.toDomain(): MessageInvoice = MessageInvoice(
    productInfo = this.productInfo.toDomain(),
    currency = this.currency,
    totalAmount = this.totalAmount,
    startParameter = this.startParameter,
    isTest = this.isTest,
    needShippingAddress = this.needShippingAddress,
    receiptMessageId = this.receiptMessageId,
    paidMedia = this.paidMedia?.toDomain(),
    paidMediaCaption = this.paidMediaCaption?.toDomain()
)

fun TdApi.MessageLocation.toDomain(): MessageLocation = MessageLocation(
    location = this.location.toDomain(),
    livePeriod = this.livePeriod,
    expiresIn = this.expiresIn,
    heading = this.heading,
    proximityAlertRadius = this.proximityAlertRadius
)

fun TdApi.MessagePaidMedia.toDomain(): MessagePaidMedia = MessagePaidMedia(
    starCount = this.starCount,
    media = this.media.map { it.toDomain() },
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia
)

fun TdApi.MessagePaidMessagePriceChanged.toDomain(): MessagePaidMessagePriceChanged = MessagePaidMessagePriceChanged(
    paidMessageStarCount = this.paidMessageStarCount
)

fun TdApi.MessagePaidMessagesRefunded.toDomain(): MessagePaidMessagesRefunded = MessagePaidMessagesRefunded(
    messageCount = this.messageCount,
    starCount = this.starCount
)

fun TdApi.MessagePassportDataReceived.toDomain(): MessagePassportDataReceived = MessagePassportDataReceived(
    elements = this.elements.map { it.toDomain() },
    credentials = this.credentials.toDomain()
)

fun TdApi.MessagePassportDataSent.toDomain(): MessagePassportDataSent = MessagePassportDataSent(
    types = this.types.map { it.toDomain() }
)

fun TdApi.MessagePaymentRefunded.toDomain(): MessagePaymentRefunded = MessagePaymentRefunded(
    ownerId = this.ownerId.toDomain(),
    currency = this.currency,
    totalAmount = this.totalAmount,
    invoicePayload = this.invoicePayload,
    telegramPaymentChargeId = this.telegramPaymentChargeId,
    providerPaymentChargeId = this.providerPaymentChargeId
)

fun TdApi.MessagePaymentSuccessful.toDomain(): MessagePaymentSuccessful = MessagePaymentSuccessful(
    invoiceChatId = this.invoiceChatId,
    invoiceMessageId = this.invoiceMessageId,
    currency = this.currency,
    totalAmount = this.totalAmount,
    subscriptionUntilDate = this.subscriptionUntilDate,
    isRecurring = this.isRecurring,
    isFirstRecurring = this.isFirstRecurring,
    invoiceName = this.invoiceName
)

fun TdApi.MessagePaymentSuccessfulBot.toDomain(): MessagePaymentSuccessfulBot = MessagePaymentSuccessfulBot(
    currency = this.currency,
    totalAmount = this.totalAmount,
    subscriptionUntilDate = this.subscriptionUntilDate,
    isRecurring = this.isRecurring,
    isFirstRecurring = this.isFirstRecurring,
    invoicePayload = this.invoicePayload,
    shippingOptionId = this.shippingOptionId,
    orderInfo = this.orderInfo?.toDomain(),
    telegramPaymentChargeId = this.telegramPaymentChargeId,
    providerPaymentChargeId = this.providerPaymentChargeId
)

fun TdApi.MessagePhoto.toDomain(): MessagePhoto = MessagePhoto(
    photo = this.photo.toDomain(),
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia,
    hasSpoiler = this.hasSpoiler,
    isSecret = this.isSecret
)

fun TdApi.MessagePinMessage.toDomain(): MessagePinMessage = MessagePinMessage(
    messageId = this.messageId
)

fun TdApi.MessagePoll.toDomain(): MessagePoll = MessagePoll(
    poll = this.poll.toDomain()
)

fun TdApi.MessagePremiumGiftCode.toDomain(): MessagePremiumGiftCode = MessagePremiumGiftCode(
    creatorId = this.creatorId?.toDomain(),
    text = this.text.toDomain(),
    isFromGiveaway = this.isFromGiveaway,
    isUnclaimed = this.isUnclaimed,
    currency = this.currency,
    amount = this.amount,
    cryptocurrency = this.cryptocurrency,
    cryptocurrencyAmount = this.cryptocurrencyAmount,
    monthCount = this.monthCount,
    sticker = this.sticker?.toDomain(),
    code = this.code
)

fun TdApi.MessageProximityAlertTriggered.toDomain(): MessageProximityAlertTriggered = MessageProximityAlertTriggered(
    travelerId = this.travelerId.toDomain(),
    watcherId = this.watcherId.toDomain(),
    distance = this.distance
)

fun TdApi.MessageRefundedUpgradedGift.toDomain(): MessageRefundedUpgradedGift = MessageRefundedUpgradedGift(
    gift = this.gift.toDomain(),
    senderId = this.senderId.toDomain(),
    receiverId = this.receiverId.toDomain(),
    origin = this.origin.toDomain()
)

fun TdApi.MessageScreenshotTaken.toDomain(): MessageScreenshotTaken = MessageScreenshotTaken

fun TdApi.MessageSender.toDomain(): MessageSender = when(this) {
    is TdApi.MessageSenderUser -> this.toDomain()
    is TdApi.MessageSenderChat -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageSenderChat.toDomain(): MessageSenderChat = MessageSenderChat(
    chatId = this.chatId
)

fun TdApi.MessageSenderUser.toDomain(): MessageSenderUser = MessageSenderUser(
    userId = this.userId
)

fun TdApi.MessageSticker.toDomain(): MessageSticker = MessageSticker(
    sticker = this.sticker.toDomain(),
    isPremium = this.isPremium
)

fun TdApi.MessageStory.toDomain(): MessageStory = MessageStory(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId,
    viaMention = this.viaMention
)

fun TdApi.MessageSuggestProfilePhoto.toDomain(): MessageSuggestProfilePhoto = MessageSuggestProfilePhoto(
    photo = this.photo.toDomain()
)

fun TdApi.MessageSuggestedPostApprovalFailed.toDomain(): MessageSuggestedPostApprovalFailed = MessageSuggestedPostApprovalFailed(
    suggestedPostMessageId = this.suggestedPostMessageId,
    price = this.price.toDomain()
)

fun TdApi.MessageSuggestedPostApproved.toDomain(): MessageSuggestedPostApproved = MessageSuggestedPostApproved(
    suggestedPostMessageId = this.suggestedPostMessageId,
    price = this.price?.toDomain(),
    sendDate = this.sendDate
)

fun TdApi.MessageSuggestedPostDeclined.toDomain(): MessageSuggestedPostDeclined = MessageSuggestedPostDeclined(
    suggestedPostMessageId = this.suggestedPostMessageId,
    comment = this.comment
)

fun TdApi.MessageSuggestedPostPaid.toDomain(): MessageSuggestedPostPaid = MessageSuggestedPostPaid(
    suggestedPostMessageId = this.suggestedPostMessageId,
    starAmount = this.starAmount.toDomain(),
    tonAmount = this.tonAmount
)

fun TdApi.MessageSuggestedPostRefunded.toDomain(): MessageSuggestedPostRefunded = MessageSuggestedPostRefunded(
    suggestedPostMessageId = this.suggestedPostMessageId,
    reason = this.reason.toDomain()
)

fun TdApi.MessageSupergroupChatCreate.toDomain(): MessageSupergroupChatCreate = MessageSupergroupChatCreate(
    title = this.title
)

fun TdApi.MessageText.toDomain(): MessageText = MessageText(
    text = this.text.toDomain(),
    linkPreview = this.linkPreview?.toDomain(),
    linkPreviewOptions = this.linkPreviewOptions?.toDomain()
)

fun TdApi.MessageUnsupported.toDomain(): MessageUnsupported = MessageUnsupported

fun TdApi.MessageUpgradedGift.toDomain(): MessageUpgradedGift = MessageUpgradedGift(
    gift = this.gift.toDomain(),
    senderId = this.senderId?.toDomain(),
    receiverId = this.receiverId.toDomain(),
    origin = this.origin.toDomain(),
    receivedGiftId = this.receivedGiftId,
    isSaved = this.isSaved,
    canBeTransferred = this.canBeTransferred,
    wasTransferred = this.wasTransferred,
    transferStarCount = this.transferStarCount,
    nextTransferDate = this.nextTransferDate,
    nextResaleDate = this.nextResaleDate,
    exportDate = this.exportDate
)

fun TdApi.MessageUsersShared.toDomain(): MessageUsersShared = MessageUsersShared(
    users = this.users.map { it.toDomain() },
    buttonId = this.buttonId
)

fun TdApi.MessageVenue.toDomain(): MessageVenue = MessageVenue(
    venue = this.venue.toDomain()
)

fun TdApi.MessageVideo.toDomain(): MessageVideo = MessageVideo(
    video = this.video.toDomain(),
    alternativeVideos = this.alternativeVideos.map { it.toDomain() },
    storyboards = this.storyboards.map { it.toDomain() },
    cover = this.cover?.toDomain(),
    startTimestamp = this.startTimestamp,
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia,
    hasSpoiler = this.hasSpoiler,
    isSecret = this.isSecret
)

fun TdApi.MessageVideoChatEnded.toDomain(): MessageVideoChatEnded = MessageVideoChatEnded(
    duration = this.duration
)

fun TdApi.MessageVideoChatScheduled.toDomain(): MessageVideoChatScheduled = MessageVideoChatScheduled(
    groupCallId = this.groupCallId,
    startDate = this.startDate
)

fun TdApi.MessageVideoChatStarted.toDomain(): MessageVideoChatStarted = MessageVideoChatStarted(
    groupCallId = this.groupCallId
)

fun TdApi.MessageVideoNote.toDomain(): MessageVideoNote = MessageVideoNote(
    videoNote = this.videoNote.toDomain(),
    isViewed = this.isViewed,
    isSecret = this.isSecret
)

fun TdApi.MessageVoiceNote.toDomain(): MessageVoiceNote = MessageVoiceNote(
    voiceNote = this.voiceNote.toDomain(),
    caption = this.caption.toDomain(),
    isListened = this.isListened
)

fun TdApi.MessageWebAppDataReceived.toDomain(): MessageWebAppDataReceived = MessageWebAppDataReceived(
    buttonText = this.buttonText,
    data = this.data
)

fun TdApi.MessageWebAppDataSent.toDomain(): MessageWebAppDataSent = MessageWebAppDataSent(
    buttonText = this.buttonText
)

fun TdApi.Minithumbnail.toDomain(): Minithumbnail = Minithumbnail(
    width = this.width,
    height = this.height,
    data = this.data
)

fun TdApi.OrderInfo.toDomain(): OrderInfo = OrderInfo(
    name = this.name,
    phoneNumber = this.phoneNumber,
    emailAddress = this.emailAddress,
    shippingAddress = this.shippingAddress?.toDomain()
)

fun TdApi.PaidMedia.toDomain(): PaidMedia = when(this) {
    is TdApi.PaidMediaPreview -> this.toDomain()
    is TdApi.PaidMediaPhoto -> this.toDomain()
    is TdApi.PaidMediaVideo -> this.toDomain()
    is TdApi.PaidMediaUnsupported -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PaidMediaPhoto.toDomain(): PaidMediaPhoto = PaidMediaPhoto(
    photo = this.photo.toDomain()
)

fun TdApi.PaidMediaPreview.toDomain(): PaidMediaPreview = PaidMediaPreview(
    width = this.width,
    height = this.height,
    duration = this.duration,
    minithumbnail = this.minithumbnail?.toDomain()
)

fun TdApi.PaidMediaUnsupported.toDomain(): PaidMediaUnsupported = PaidMediaUnsupported

fun TdApi.PaidMediaVideo.toDomain(): PaidMediaVideo = PaidMediaVideo(
    video = this.video.toDomain(),
    cover = this.cover?.toDomain(),
    startTimestamp = this.startTimestamp
)

fun TdApi.PassportElementType.toDomain(): PassportElementType = when(this) {
    is TdApi.PassportElementTypePersonalDetails -> this.toDomain()
    is TdApi.PassportElementTypePassport -> this.toDomain()
    is TdApi.PassportElementTypeDriverLicense -> this.toDomain()
    is TdApi.PassportElementTypeIdentityCard -> this.toDomain()
    is TdApi.PassportElementTypeInternalPassport -> this.toDomain()
    is TdApi.PassportElementTypeAddress -> this.toDomain()
    is TdApi.PassportElementTypeUtilityBill -> this.toDomain()
    is TdApi.PassportElementTypeBankStatement -> this.toDomain()
    is TdApi.PassportElementTypeRentalAgreement -> this.toDomain()
    is TdApi.PassportElementTypePassportRegistration -> this.toDomain()
    is TdApi.PassportElementTypeTemporaryRegistration -> this.toDomain()
    is TdApi.PassportElementTypePhoneNumber -> this.toDomain()
    is TdApi.PassportElementTypeEmailAddress -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PassportElementTypeAddress.toDomain(): PassportElementTypeAddress = PassportElementTypeAddress

fun TdApi.PassportElementTypeBankStatement.toDomain(): PassportElementTypeBankStatement = PassportElementTypeBankStatement

fun TdApi.PassportElementTypeDriverLicense.toDomain(): PassportElementTypeDriverLicense = PassportElementTypeDriverLicense

fun TdApi.PassportElementTypeEmailAddress.toDomain(): PassportElementTypeEmailAddress = PassportElementTypeEmailAddress

fun TdApi.PassportElementTypeIdentityCard.toDomain(): PassportElementTypeIdentityCard = PassportElementTypeIdentityCard

fun TdApi.PassportElementTypeInternalPassport.toDomain(): PassportElementTypeInternalPassport = PassportElementTypeInternalPassport

fun TdApi.PassportElementTypePassport.toDomain(): PassportElementTypePassport = PassportElementTypePassport

fun TdApi.PassportElementTypePassportRegistration.toDomain(): PassportElementTypePassportRegistration = PassportElementTypePassportRegistration

fun TdApi.PassportElementTypePersonalDetails.toDomain(): PassportElementTypePersonalDetails = PassportElementTypePersonalDetails

fun TdApi.PassportElementTypePhoneNumber.toDomain(): PassportElementTypePhoneNumber = PassportElementTypePhoneNumber

fun TdApi.PassportElementTypeRentalAgreement.toDomain(): PassportElementTypeRentalAgreement = PassportElementTypeRentalAgreement

fun TdApi.PassportElementTypeTemporaryRegistration.toDomain(): PassportElementTypeTemporaryRegistration = PassportElementTypeTemporaryRegistration

fun TdApi.PassportElementTypeUtilityBill.toDomain(): PassportElementTypeUtilityBill = PassportElementTypeUtilityBill

fun TdApi.Photo.toDomain(): Photo = Photo(
    hasStickers = this.hasStickers,
    minithumbnail = this.minithumbnail?.toDomain(),
    sizes = this.sizes.map { it.toDomain() }
)

fun TdApi.PhotoSize.toDomain(): PhotoSize = PhotoSize(
    type = this.type,
    photo = this.photo.toDomain(),
    width = this.width,
    height = this.height,
    progressiveSizes = this.progressiveSizes
)

fun TdApi.Poll.toDomain(): Poll = Poll(
    id = this.id,
    question = this.question.toDomain(),
    options = this.options.map { it.toDomain() },
    totalVoterCount = this.totalVoterCount,
    recentVoterIds = this.recentVoterIds.map { it.toDomain() },
    isAnonymous = this.isAnonymous,
    type = this.type.toDomain(),
    openPeriod = this.openPeriod,
    closeDate = this.closeDate,
    isClosed = this.isClosed
)

fun TdApi.PollOption.toDomain(): PollOption = PollOption(
    text = this.text.toDomain(),
    voterCount = this.voterCount,
    votePercentage = this.votePercentage,
    isChosen = this.isChosen,
    isBeingChosen = this.isBeingChosen
)

fun TdApi.PollType.toDomain(): PollType = when(this) {
    is TdApi.PollTypeRegular -> this.toDomain()
    is TdApi.PollTypeQuiz -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PollTypeQuiz.toDomain(): PollTypeQuiz = PollTypeQuiz(
    correctOptionId = this.correctOptionId,
    explanation = this.explanation.toDomain()
)

fun TdApi.PollTypeRegular.toDomain(): PollTypeRegular = PollTypeRegular(
    allowMultipleAnswers = this.allowMultipleAnswers
)

fun TdApi.ProductInfo.toDomain(): ProductInfo = ProductInfo(
    title = this.title,
    description = this.description.toDomain(),
    photo = this.photo?.toDomain()
)

fun TdApi.RemoteFile.toDomain(): RemoteFile = RemoteFile(
    id = this.id,
    uniqueId = this.uniqueId,
    isUploadingActive = this.isUploadingActive,
    isUploadingCompleted = this.isUploadingCompleted,
    uploadedSize = this.uploadedSize
)

fun TdApi.SharedChat.toDomain(): SharedChat = SharedChat(
    chatId = this.chatId,
    title = this.title,
    username = this.username,
    photo = this.photo?.toDomain()
)

fun TdApi.SharedUser.toDomain(): SharedUser = SharedUser(
    userId = this.userId,
    firstName = this.firstName,
    lastName = this.lastName,
    username = this.username,
    photo = this.photo?.toDomain()
)

fun TdApi.SpeechRecognitionResult.toDomain(): SpeechRecognitionResult = when(this) {
    is TdApi.SpeechRecognitionResultPending -> this.toDomain()
    is TdApi.SpeechRecognitionResultText -> this.toDomain()
    is TdApi.SpeechRecognitionResultError -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SpeechRecognitionResultError.toDomain(): SpeechRecognitionResultError = SpeechRecognitionResultError(
    error = this.error.toDomain()
)

fun TdApi.SpeechRecognitionResultPending.toDomain(): SpeechRecognitionResultPending = SpeechRecognitionResultPending(
    partialText = this.partialText
)

fun TdApi.SpeechRecognitionResultText.toDomain(): SpeechRecognitionResultText = SpeechRecognitionResultText(
    text = this.text
)

fun TdApi.StarAmount.toDomain(): StarAmount = StarAmount(
    starCount = this.starCount,
    nanostarCount = this.nanostarCount
)

fun TdApi.Sticker.toDomain(): Sticker = Sticker(
    id = this.id,
    setId = this.setId,
    width = this.width,
    height = this.height,
    emoji = this.emoji,
    format = this.format.toDomain(),
    fullType = this.fullType.toDomain(),
    thumbnail = this.thumbnail?.toDomain(),
    sticker = this.sticker.toDomain()
)

fun TdApi.StickerFormat.toDomain(): StickerFormat = when(this) {
    is TdApi.StickerFormatWebp -> this.toDomain()
    is TdApi.StickerFormatTgs -> this.toDomain()
    is TdApi.StickerFormatWebm -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StickerFormatTgs.toDomain(): StickerFormatTgs = StickerFormatTgs

fun TdApi.StickerFormatWebm.toDomain(): StickerFormatWebm = StickerFormatWebm

fun TdApi.StickerFormatWebp.toDomain(): StickerFormatWebp = StickerFormatWebp

fun TdApi.StickerFullType.toDomain(): StickerFullType = when(this) {
    is TdApi.StickerFullTypeRegular -> this.toDomain()
    is TdApi.StickerFullTypeMask -> this.toDomain()
    is TdApi.StickerFullTypeCustomEmoji -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StickerFullTypeCustomEmoji.toDomain(): StickerFullTypeCustomEmoji = StickerFullTypeCustomEmoji(
    customEmojiId = this.customEmojiId,
    needsRepainting = this.needsRepainting
)

fun TdApi.StickerFullTypeMask.toDomain(): StickerFullTypeMask = StickerFullTypeMask(
    maskPosition = this.maskPosition?.toDomain()
)

fun TdApi.StickerFullTypeRegular.toDomain(): StickerFullTypeRegular = StickerFullTypeRegular(
    premiumAnimation = this.premiumAnimation?.toDomain()
)

fun TdApi.SuggestedPostPrice.toDomain(): SuggestedPostPrice = when(this) {
    is TdApi.SuggestedPostPriceStar -> this.toDomain()
    is TdApi.SuggestedPostPriceTon -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SuggestedPostPriceStar.toDomain(): SuggestedPostPriceStar = SuggestedPostPriceStar(
    starCount = this.starCount
)

fun TdApi.SuggestedPostPriceTon.toDomain(): SuggestedPostPriceTon = SuggestedPostPriceTon(
    toncoinCentCount = this.toncoinCentCount
)

fun TdApi.SuggestedPostRefundReason.toDomain(): SuggestedPostRefundReason = when(this) {
    is TdApi.SuggestedPostRefundReasonPostDeleted -> this.toDomain()
    is TdApi.SuggestedPostRefundReasonPaymentRefunded -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SuggestedPostRefundReasonPaymentRefunded.toDomain(): SuggestedPostRefundReasonPaymentRefunded = SuggestedPostRefundReasonPaymentRefunded

fun TdApi.SuggestedPostRefundReasonPostDeleted.toDomain(): SuggestedPostRefundReasonPostDeleted = SuggestedPostRefundReasonPostDeleted

fun TdApi.TextEntity.toDomain(): TextEntity = TextEntity(
    offset = this.offset,
    length = this.length,
    type = this.type.toDomain()
)

fun TdApi.TextEntityType.toDomain(): TextEntityType = when(this) {
    is TdApi.TextEntityTypeMention -> this.toDomain()
    is TdApi.TextEntityTypeHashtag -> this.toDomain()
    is TdApi.TextEntityTypeCashtag -> this.toDomain()
    is TdApi.TextEntityTypeBotCommand -> this.toDomain()
    is TdApi.TextEntityTypeUrl -> this.toDomain()
    is TdApi.TextEntityTypeEmailAddress -> this.toDomain()
    is TdApi.TextEntityTypePhoneNumber -> this.toDomain()
    is TdApi.TextEntityTypeBankCardNumber -> this.toDomain()
    is TdApi.TextEntityTypeBold -> this.toDomain()
    is TdApi.TextEntityTypeItalic -> this.toDomain()
    is TdApi.TextEntityTypeUnderline -> this.toDomain()
    is TdApi.TextEntityTypeStrikethrough -> this.toDomain()
    is TdApi.TextEntityTypeSpoiler -> this.toDomain()
    is TdApi.TextEntityTypeCode -> this.toDomain()
    is TdApi.TextEntityTypePre -> this.toDomain()
    is TdApi.TextEntityTypePreCode -> this.toDomain()
    is TdApi.TextEntityTypeBlockQuote -> this.toDomain()
    is TdApi.TextEntityTypeExpandableBlockQuote -> this.toDomain()
    is TdApi.TextEntityTypeTextUrl -> this.toDomain()
    is TdApi.TextEntityTypeMentionName -> this.toDomain()
    is TdApi.TextEntityTypeCustomEmoji -> this.toDomain()
    is TdApi.TextEntityTypeMediaTimestamp -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.TextEntityTypeBankCardNumber.toDomain(): TextEntityTypeBankCardNumber = TextEntityTypeBankCardNumber

fun TdApi.TextEntityTypeBlockQuote.toDomain(): TextEntityTypeBlockQuote = TextEntityTypeBlockQuote

fun TdApi.TextEntityTypeBold.toDomain(): TextEntityTypeBold = TextEntityTypeBold

fun TdApi.TextEntityTypeBotCommand.toDomain(): TextEntityTypeBotCommand = TextEntityTypeBotCommand

fun TdApi.TextEntityTypeCashtag.toDomain(): TextEntityTypeCashtag = TextEntityTypeCashtag

fun TdApi.TextEntityTypeCode.toDomain(): TextEntityTypeCode = TextEntityTypeCode

fun TdApi.TextEntityTypeCustomEmoji.toDomain(): TextEntityTypeCustomEmoji = TextEntityTypeCustomEmoji(
    customEmojiId = this.customEmojiId
)

fun TdApi.TextEntityTypeEmailAddress.toDomain(): TextEntityTypeEmailAddress = TextEntityTypeEmailAddress

fun TdApi.TextEntityTypeExpandableBlockQuote.toDomain(): TextEntityTypeExpandableBlockQuote = TextEntityTypeExpandableBlockQuote

fun TdApi.TextEntityTypeHashtag.toDomain(): TextEntityTypeHashtag = TextEntityTypeHashtag

fun TdApi.TextEntityTypeItalic.toDomain(): TextEntityTypeItalic = TextEntityTypeItalic

fun TdApi.TextEntityTypeMediaTimestamp.toDomain(): TextEntityTypeMediaTimestamp = TextEntityTypeMediaTimestamp(
    mediaTimestamp = this.mediaTimestamp
)

fun TdApi.TextEntityTypeMention.toDomain(): TextEntityTypeMention = TextEntityTypeMention

fun TdApi.TextEntityTypeMentionName.toDomain(): TextEntityTypeMentionName = TextEntityTypeMentionName(
    userId = this.userId
)

fun TdApi.TextEntityTypePhoneNumber.toDomain(): TextEntityTypePhoneNumber = TextEntityTypePhoneNumber

fun TdApi.TextEntityTypePre.toDomain(): TextEntityTypePre = TextEntityTypePre

fun TdApi.TextEntityTypePreCode.toDomain(): TextEntityTypePreCode = TextEntityTypePreCode(
    language = this.language
)

fun TdApi.TextEntityTypeSpoiler.toDomain(): TextEntityTypeSpoiler = TextEntityTypeSpoiler

fun TdApi.TextEntityTypeStrikethrough.toDomain(): TextEntityTypeStrikethrough = TextEntityTypeStrikethrough

fun TdApi.TextEntityTypeTextUrl.toDomain(): TextEntityTypeTextUrl = TextEntityTypeTextUrl(
    url = this.url
)

fun TdApi.TextEntityTypeUnderline.toDomain(): TextEntityTypeUnderline = TextEntityTypeUnderline

fun TdApi.TextEntityTypeUrl.toDomain(): TextEntityTypeUrl = TextEntityTypeUrl

fun TdApi.ThemeSettings.toDomain(): ThemeSettings = ThemeSettings(
    baseTheme = this.baseTheme.toDomain(),
    accentColor = this.accentColor,
    background = this.background?.toDomain(),
    outgoingMessageFill = this.outgoingMessageFill?.toDomain(),
    animateOutgoingMessageFill = this.animateOutgoingMessageFill,
    outgoingMessageAccentColor = this.outgoingMessageAccentColor
)

fun TdApi.Thumbnail.toDomain(): Thumbnail = Thumbnail(
    format = this.format.toDomain(),
    width = this.width,
    height = this.height,
    file = this.file.toDomain()
)

fun TdApi.ThumbnailFormat.toDomain(): ThumbnailFormat = when(this) {
    is TdApi.ThumbnailFormatJpeg -> this.toDomain()
    is TdApi.ThumbnailFormatGif -> this.toDomain()
    is TdApi.ThumbnailFormatMpeg4 -> this.toDomain()
    is TdApi.ThumbnailFormatPng -> this.toDomain()
    is TdApi.ThumbnailFormatTgs -> this.toDomain()
    is TdApi.ThumbnailFormatWebm -> this.toDomain()
    is TdApi.ThumbnailFormatWebp -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ThumbnailFormatGif.toDomain(): ThumbnailFormatGif = ThumbnailFormatGif

fun TdApi.ThumbnailFormatJpeg.toDomain(): ThumbnailFormatJpeg = ThumbnailFormatJpeg

fun TdApi.ThumbnailFormatMpeg4.toDomain(): ThumbnailFormatMpeg4 = ThumbnailFormatMpeg4

fun TdApi.ThumbnailFormatPng.toDomain(): ThumbnailFormatPng = ThumbnailFormatPng

fun TdApi.ThumbnailFormatTgs.toDomain(): ThumbnailFormatTgs = ThumbnailFormatTgs

fun TdApi.ThumbnailFormatWebm.toDomain(): ThumbnailFormatWebm = ThumbnailFormatWebm

fun TdApi.ThumbnailFormatWebp.toDomain(): ThumbnailFormatWebp = ThumbnailFormatWebp

fun TdApi.UpgradedGift.toDomain(): UpgradedGift = UpgradedGift(
    id = this.id,
    regularGiftId = this.regularGiftId,
    publisherChatId = this.publisherChatId,
    title = this.title,
    name = this.name,
    number = this.number,
    totalUpgradedCount = this.totalUpgradedCount,
    maxUpgradedCount = this.maxUpgradedCount,
    isPremium = this.isPremium,
    isThemeAvailable = this.isThemeAvailable,
    usedThemeChatId = this.usedThemeChatId,
    ownerId = this.ownerId?.toDomain(),
    ownerAddress = this.ownerAddress,
    ownerName = this.ownerName,
    giftAddress = this.giftAddress,
    model = this.model.toDomain(),
    symbol = this.symbol.toDomain(),
    backdrop = this.backdrop.toDomain(),
    originalDetails = this.originalDetails?.toDomain(),
    resaleParameters = this.resaleParameters?.toDomain(),
    valueCurrency = this.valueCurrency,
    valueAmount = this.valueAmount
)

fun TdApi.UpgradedGiftBackdrop.toDomain(): UpgradedGiftBackdrop = UpgradedGiftBackdrop(
    id = this.id,
    name = this.name,
    colors = this.colors.toDomain(),
    rarityPerMille = this.rarityPerMille
)

fun TdApi.UpgradedGiftBackdropColors.toDomain(): UpgradedGiftBackdropColors = UpgradedGiftBackdropColors(
    centerColor = this.centerColor,
    edgeColor = this.edgeColor,
    symbolColor = this.symbolColor,
    textColor = this.textColor
)

fun TdApi.UpgradedGiftModel.toDomain(): UpgradedGiftModel = UpgradedGiftModel(
    name = this.name,
    sticker = this.sticker.toDomain(),
    rarityPerMille = this.rarityPerMille
)

fun TdApi.UpgradedGiftOrigin.toDomain(): UpgradedGiftOrigin = when(this) {
    is TdApi.UpgradedGiftOriginUpgrade -> this.toDomain()
    is TdApi.UpgradedGiftOriginTransfer -> this.toDomain()
    is TdApi.UpgradedGiftOriginResale -> this.toDomain()
    is TdApi.UpgradedGiftOriginPrepaidUpgrade -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.UpgradedGiftOriginPrepaidUpgrade.toDomain(): UpgradedGiftOriginPrepaidUpgrade = UpgradedGiftOriginPrepaidUpgrade

fun TdApi.UpgradedGiftOriginResale.toDomain(): UpgradedGiftOriginResale = UpgradedGiftOriginResale(
    price = this.price.toDomain()
)

fun TdApi.UpgradedGiftOriginTransfer.toDomain(): UpgradedGiftOriginTransfer = UpgradedGiftOriginTransfer

fun TdApi.UpgradedGiftOriginUpgrade.toDomain(): UpgradedGiftOriginUpgrade = UpgradedGiftOriginUpgrade(
    giftMessageId = this.giftMessageId
)

fun TdApi.UpgradedGiftOriginalDetails.toDomain(): UpgradedGiftOriginalDetails = UpgradedGiftOriginalDetails(
    senderId = this.senderId?.toDomain(),
    receiverId = this.receiverId.toDomain(),
    text = this.text.toDomain(),
    date = this.date
)

fun TdApi.UpgradedGiftSymbol.toDomain(): UpgradedGiftSymbol = UpgradedGiftSymbol(
    name = this.name,
    sticker = this.sticker.toDomain(),
    rarityPerMille = this.rarityPerMille
)

fun TdApi.Venue.toDomain(): Venue = Venue(
    location = this.location.toDomain(),
    title = this.title,
    address = this.address,
    provider = this.provider,
    id = this.id,
    type = this.type
)

fun TdApi.Video.toDomain(): Video = Video(
    duration = this.duration,
    width = this.width,
    height = this.height,
    fileName = this.fileName,
    mimeType = this.mimeType,
    hasStickers = this.hasStickers,
    supportsStreaming = this.supportsStreaming,
    minithumbnail = this.minithumbnail?.toDomain(),
    thumbnail = this.thumbnail?.toDomain(),
    video = this.video.toDomain()
)

fun TdApi.VideoNote.toDomain(): VideoNote = VideoNote(
    duration = this.duration,
    waveform = this.waveform,
    length = this.length,
    minithumbnail = this.minithumbnail?.toDomain(),
    thumbnail = this.thumbnail?.toDomain(),
    speechRecognitionResult = this.speechRecognitionResult?.toDomain(),
    video = this.video.toDomain()
)

fun TdApi.VideoStoryboard.toDomain(): VideoStoryboard = VideoStoryboard(
    storyboardFile = this.storyboardFile.toDomain(),
    width = this.width,
    height = this.height,
    mapFile = this.mapFile.toDomain()
)

fun TdApi.VoiceNote.toDomain(): VoiceNote = VoiceNote(
    duration = this.duration,
    waveform = this.waveform,
    mimeType = this.mimeType,
    speechRecognitionResult = this.speechRecognitionResult?.toDomain(),
    voice = this.voice.toDomain()
)

fun TdApi.WebApp.toDomain(): WebApp = WebApp(
    shortName = this.shortName,
    title = this.title,
    description = this.description,
    photo = this.photo.toDomain(),
    animation = this.animation?.toDomain()
)
