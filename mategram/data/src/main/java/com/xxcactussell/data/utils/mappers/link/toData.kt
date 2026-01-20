package com.xxcactussell.data.utils.mappers.link

import com.xxcactussell.data.utils.mappers.animation.toData
import com.xxcactussell.data.utils.mappers.background.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.invite.toData
import com.xxcactussell.data.utils.mappers.monetization.toData
import com.xxcactussell.data.utils.mappers.photo.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import com.xxcactussell.data.utils.mappers.theme.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

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

