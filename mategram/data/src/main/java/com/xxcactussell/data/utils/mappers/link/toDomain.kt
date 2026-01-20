package com.xxcactussell.data.utils.mappers.link

import com.xxcactussell.data.utils.mappers.animation.toDomain
import com.xxcactussell.data.utils.mappers.background.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.invite.toDomain
import com.xxcactussell.data.utils.mappers.monetization.toDomain
import com.xxcactussell.data.utils.mappers.photo.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import com.xxcactussell.data.utils.mappers.theme.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

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

