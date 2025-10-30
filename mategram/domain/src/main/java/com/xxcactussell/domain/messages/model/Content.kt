package com.xxcactussell.domain.messages.model

import com.xxcactussell.domain.files.model.File

sealed interface TextEntityType {
    object Mention : TextEntityType
    object Hashtag : TextEntityType
    object Cashtag : TextEntityType
    object BotCommand : TextEntityType
    object Url : TextEntityType
    object Email : TextEntityType
    object Phone : TextEntityType
    object Card : TextEntityType
    object Bold : TextEntityType
    object Italic : TextEntityType
    object Underline : TextEntityType
    object Strikethrough : TextEntityType
    object Spoiler : TextEntityType
    object Code : TextEntityType
    object Pre : TextEntityType
    data class PreCode(val language: String) : TextEntityType
    object BlockQuote : TextEntityType
    object ExpandableBlockQuote : TextEntityType
    data class TextUrl(val url: String) : TextEntityType
    data class MentionName(val id: Long) : TextEntityType
    data class CustomEmoji(val id: Long) : TextEntityType
    data class Timestamp(val timestamp: Int) : TextEntityType
}

data class TextEntity(
    val offset: Int,
    val length: Int,
    val type: TextEntityType
)

data class FormattedText(
    val text: String,
    val entities: List<TextEntity> = emptyList<TextEntity>()
)

data class WebPagePreview(
    val url: String,
    val displayUrl: String?,
    val siteName: String?,
    val title: String?,
    val description: FormattedText?,
    val author: String?,

    val type: String?,
    val showLargeMedia: Boolean,
    val showMediaAboveDescription: Boolean,
    val instantViewVersion: Int?,

    val showAboveText: Boolean,
    val skipConfirmation: Boolean,
    val hasLargeMedia: Boolean
)

data class LinkOption(
    val isDisabled: Boolean?,
    val url: String?,
    val forceSmallMedia: Boolean?,
    val forceLargeMedia: Boolean?,
    val showAboveText: Boolean?
)

data class AnimatedEmoji(
    val sticker: Sticker? = null,
    val width: Int,
    val height: Int,
    val fitzpatrick: Int,
    val sound: File? = null,
)

sealed interface DiceStickers {
    object Unknown : DiceStickers

    data class Regular(
        val sticker: Sticker
    ) : DiceStickers

    data class SlotMachine(
        val background: Sticker,
        val lever: Sticker,
        val leftReel: Sticker,
        val centerReel: Sticker,
        val rightReel: Sticker
    ) : DiceStickers
}

data class Audio(
    val duration: Int,
    val title: String,
    val performer: String,
    val fileName: String,
    val mimeType: String,
    val albumCoverMiniThumbnail: MiniThumbnail? = null,
    val albumCoverThumbnail: Thumbnail? = null,
    val externalAlbumCovers: List<Thumbnail>,
    val audio: File
)

data class Document(
    val fileName: String,
    val mimeType: String,
    val miniThumbnail: MiniThumbnail? = null,
    val thumbnail: Thumbnail? = null,
    val document: File
)

sealed interface MessageContent {
    sealed interface Media : MessageContent

    data class Text(
        val text: FormattedText,
        val linkPreview: WebPagePreview? = null,
        val linkOption: LinkOption? = null
    ) : MessageContent

    data class MessagePhoto(
        val photo: Photo,
        val caption: FormattedText,
        val showCaptionAboveMedia: Boolean,
        val hasSpoiler: Boolean,
        val isSecret: Boolean
    ) : Media

    data class MessageVideo(
        val video: Video.Main,
        val alternativeVideo: List<Video.Alternative>,
        val cover: Photo?,
        val startTime: Int,
        val caption: FormattedText,
        val showCaptionAboveMedia: Boolean,
        val hasSpoiler: Boolean,
        val isSecret: Boolean
    ) : Media

    data class MessageAnimatedEmoji(val animatedEmoji: AnimatedEmoji, val emoji: String) : Media

    data class MessageDice(
        val initialState: DiceStickers? = null,
        val finalState: DiceStickers? = null,
        val emoji: String,
        val value: Int,
        val successAnimationFrameNumber: Int
    ) : Media

    data class MessageAudio(
        val audio: Audio,
        val caption: FormattedText
    ) : Media

    data class MessageDocument(
        val document: Document,
        val caption: FormattedText
    ) : Media
    data class MessageSticker(val sticker: Sticker, val isPremium: Boolean) : Media
    data class MessageAnimation(val fileId: String) : Media

    data class MessageLocation(val latitude: Double, val longitude: Double) : MessageContent
    data class MessageContact(val name: String, val phoneNumber: String) : MessageContent
    data class MessagePoll(val question: String) : MessageContent
    data class MessageGame(val title: String) : MessageContent

    sealed interface SystemService : MessageContent

    data class MemberJoined(val memberId: Long) : SystemService // MessageChatAddMembers, MessageChatJoinByLink
    data class MemberLeft(val memberId: Long) : SystemService   // MessageChatDeleteMember
    data class ChatTitleChanged(val newTitle: String) : SystemService // MessageChatChangeTitle
    data class MessagePinned(val messageId: Long) : SystemService     // MessagePinMessage
    data class ScreenshotTaken(val userNames: List<String>) : SystemService

    sealed interface CallService : SystemService

    data class CallEnded(val duration: Int) : CallService       // MessageVideoChatEnded, MessageCall
    object CallStarted : CallService                           // MessageVideoChatStarted
    data class CallScheduled(val time: Int) : CallService      // MessageVideoChatScheduled

    sealed interface Payment : SystemService

    data class PaymentSuccessful(val invoice: String) : Payment      // MessagePaymentSuccessful, MessagePaymentSuccessfulBot
    data class GiftedPremium(val currency: String) : Payment        // MessageGiftedPremium
    data class Giveaway(val details: String) : Payment             // MessageGiveawayCreated, MessageGiveawayWinners

    object Expired : MessageContent // MessageExpiredPhoto, MessageExpiredVideo, etc.
    object Unsupported : MessageContent // MessageUnsupported
}