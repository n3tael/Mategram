package com.xxcactussell.presentation.messages.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.InsertDriveFile
import androidx.compose.material.icons.automirrored.rounded.Reply
import androidx.compose.material.icons.rounded.CardGiftcard
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.ContactPhone
import androidx.compose.material.icons.rounded.Gif
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.Photo
import androidx.compose.material.icons.rounded.PhotoAlbum
import androidx.compose.material.icons.rounded.PlayCircle
import androidx.compose.material.icons.rounded.Poll
import androidx.compose.material.icons.rounded.RecordVoiceOver
import androidx.compose.material.icons.rounded.ShareLocation
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.VideoFile
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.xxcactussell.domain.messages.model.FormattedText
import com.xxcactussell.domain.messages.model.Message
import com.xxcactussell.domain.messages.model.MessageAnimatedEmoji
import com.xxcactussell.domain.messages.model.MessageAnimation
import com.xxcactussell.domain.messages.model.MessageAudio
import com.xxcactussell.domain.messages.model.MessageChecklist
import com.xxcactussell.domain.messages.model.MessageContact
import com.xxcactussell.domain.messages.model.MessageDice
import com.xxcactussell.domain.messages.model.MessageDocument
import com.xxcactussell.domain.messages.model.MessageGame
import com.xxcactussell.domain.messages.model.MessageGift
import com.xxcactussell.domain.messages.model.MessageLocation
import com.xxcactussell.domain.messages.model.MessagePaidMedia
import com.xxcactussell.domain.messages.model.MessagePhoto
import com.xxcactussell.domain.messages.model.MessagePoll
import com.xxcactussell.domain.messages.model.MessageReplyTo
import com.xxcactussell.domain.messages.model.MessageSticker
import com.xxcactussell.domain.messages.model.MessageStory
import com.xxcactussell.domain.messages.model.MessageText
import com.xxcactussell.domain.messages.model.MessageUpgradedGift
import com.xxcactussell.domain.messages.model.MessageVenue
import com.xxcactussell.domain.messages.model.MessageVideo
import com.xxcactussell.domain.messages.model.MessageVideoNote
import com.xxcactussell.domain.messages.model.MessageVoiceNote
import com.xxcactussell.domain.messages.model.TextEntity
import com.xxcactussell.domain.messages.model.TextEntityTypeCustomEmoji
import com.xxcactussell.presentation.localization.localizedString
import com.xxcactussell.presentation.tools.FormattedTextView
import com.xxcactussell.presentation.tools.getServiceMessageText

@Composable
fun ReplyMarkup(message: Message, isOutgoing: Boolean, onMessageClicked: (Long) -> Unit) {
    val messageReplyTo = message.replyTo

    if (messageReplyTo == null) return

    if (messageReplyTo is MessageReplyTo.Message) {
        if (messageReplyTo.message == null) return
        ReplyBubble(messageReplyTo.messageId, isOutgoing,  onMessageClicked) {
            ReplyMarkupMessage(
                messageReplyTo = messageReplyTo
            )
        }

    } else return
}

@Composable
fun ReplyBubble(
    id: Long,
    isOutgoing: Boolean,
    onMessageClicked: (Long) -> Unit,
    content: @Composable () -> Unit
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (isOutgoing) {
            Icon(
                Icons.AutoMirrored.Rounded.Reply,
                "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )
        }
        Surface(
            modifier = Modifier
                .height(36.dp)
                .widthIn(max = 200.dp)
                .clip(RoundedCornerShape(18.dp))
                .clickable {
                    onMessageClicked(id)
                },
            color = MaterialTheme.colorScheme.outlineVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            shape = RoundedCornerShape(18.dp)
        ) {
            content()
        }
        if (!isOutgoing) {
            Icon(
                Icons.AutoMirrored.Rounded.Reply,
                "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun ReplyMarkupMessage(
    messageReplyTo: MessageReplyTo.Message
) {
    val iconModifier = Modifier.size(16.dp)
    val textStyle = MaterialTheme.typography.bodyMedium

    val text : FormattedText = when (val content = messageReplyTo.message?.content) {
        is MessageAnimatedEmoji -> FormattedText(localizedString("LiteOptionsEmoji"))
        is MessageAnimation -> {
            if (content.caption.text.isNotEmpty()) {
                content.caption
            } else {
                FormattedText(localizedString("AttachGif"))
            }
        }
        is MessageAudio -> {
            if (content.caption.text.isNotEmpty()) {
                content.caption
            } else {
                FormattedText(localizedString("AttachMusic"))
            }
        }
        is MessageContact -> FormattedText("${content.contact.firstName} ${content.contact.lastName}")
        is MessageDocument -> {
            if (content.caption.text.isNotEmpty()) {
                content.caption
            } else {
                FormattedText(localizedString("AttachDocument"))
            }
        }
        is MessageGame -> FormattedText(content.game.title)
        is MessageGift -> if (content.text.text.isNotEmpty()) content.text else FormattedText(localizedString("ActionStarGift"))
        is MessageLocation -> FormattedText(localizedString("ShareLocation"))
        is MessagePaidMedia -> if (content.caption.text.isNotEmpty()) content.caption else FormattedText(localizedString("PaidContentTitle"))
        is MessagePhoto -> if (content.caption.text.isNotEmpty()) content.caption else FormattedText(localizedString("AttachPhoto"))
        is MessagePoll -> FormattedText(localizedString("Poll"))
        is MessageSticker -> FormattedText(localizedString("AttachSticker"))
        is MessageText -> content.text
        is MessageVenue -> FormattedText(content.venue.title)
        is MessageVideo -> if (content.caption.text.isNotEmpty()) content.caption else FormattedText(localizedString("AttachVideo"))
        is MessageVideoNote -> FormattedText(localizedString("AttachRound"))
        is MessageVoiceNote -> FormattedText(localizedString("AttachAudio"))
        else -> content?.getServiceMessageText() ?: FormattedText("${content?.javaClass?.simpleName}")
    }

    val icon : @Composable (() -> Unit)? = when (val content = messageReplyTo.message?.content) {
        is MessagePhoto -> {
            {
                if (content.photo.minithumbnail?.data?.isNotEmpty() == true) {
                    ByteArrayImage(
                        imageData = content.photo.minithumbnail!!.data,
                        contentDescription = "",
                        modifier = iconModifier.clip(RoundedCornerShape(4.dp))
                    )
                } else {
                    Icon(
                        Icons.Rounded.Photo,
                        "",
                        modifier = iconModifier
                    )
                }
            }
        }
        is MessageAnimatedEmoji -> {
            {
                val entities = if (content.animatedEmoji.sticker != null) {
                    listOf(TextEntity(0,1, TextEntityTypeCustomEmoji(
                        content.animatedEmoji.sticker!!.id
                    )))
                } else emptyList()

                val textDp = 16.dp

                val textSp = with(LocalDensity.current) {
                    textDp.toSp()
                }

                FormattedTextView(
                    text = FormattedText(content.emoji, entities),
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = textSp),
                    maxLines = 1,
                    modifier = iconModifier
                )
            }
        }
        is MessageAnimation -> {
            {
                if (content.animation.minithumbnail?.data?.isNotEmpty() == true) {
                    ByteArrayImage(
                        imageData = content.animation.minithumbnail!!.data,
                        contentDescription = "",
                        modifier = iconModifier.clip(RoundedCornerShape(4.dp))
                    )
                } else {
                    Icon(
                        Icons.Rounded.Gif,
                        "",
                        modifier = iconModifier
                    )
                }
            }
        }
        is MessageAudio -> {
            {
                if (content.audio.albumCoverMinithumbnail?.data?.isNotEmpty() == true) {
                    ByteArrayImage(
                        imageData = content.audio.albumCoverMinithumbnail!!.data,
                        contentDescription = "",
                        modifier = iconModifier.clip(RoundedCornerShape(4.dp))
                    )
                } else {
                    Icon(
                        Icons.Rounded.MusicNote,
                        "",
                        modifier = iconModifier
                    )
                }
            }
        }
        is MessageChecklist -> {
            {
                Icon(
                    Icons.Rounded.Checklist,
                    "",
                    modifier = iconModifier
                )
            }
        }
        is MessageContact -> {
            {
                Icon(
                    Icons.Rounded.ContactPhone,
                    "",
                    modifier = iconModifier
                )
            }
        }
        is MessageDice -> {
            {
                val textDp = 16.dp
                val textSp = with(LocalDensity.current) {
                    textDp.toSp()
                }

                FormattedTextView(
                    text = FormattedText(content.emoji),
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = textSp),
                    maxLines = 1,
                    modifier = iconModifier
                )
            }
        }
        is MessageDocument -> {
            {
                if (content.document.minithumbnail?.data?.isNotEmpty() == true) {
                    ByteArrayImage(
                        imageData = content.document.minithumbnail!!.data,
                        contentDescription = "",
                        modifier = iconModifier.clip(RoundedCornerShape(4.dp))
                    )
                } else {
                    Icon(
                        Icons.AutoMirrored.Rounded.InsertDriveFile,
                        "",
                        modifier = iconModifier
                    )
                }
            }
        }
        is MessageLocation -> {
            {
                Icon(
                    Icons.Rounded.ShareLocation,
                    "",
                    modifier = iconModifier
                )
            }
        }
        is MessagePaidMedia -> {
            {
                Icon(
                    Icons.Rounded.Star,
                    "",
                    modifier = iconModifier
                )
            }
        }
        is MessagePoll -> {
            {
                Icon(
                    Icons.Rounded.Poll,
                    "",
                    modifier = iconModifier
                )
            }
        }
        is MessageSticker -> {
            {
                val textDp = 16.dp
                val textSp = with(LocalDensity.current) {
                    textDp.toSp()
                }

                FormattedTextView(
                    text = FormattedText(content.sticker.emoji),
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = textSp),
                    maxLines = 1,
                    modifier = iconModifier
                )
            }
        }
        is MessageStory -> {
            {
                Icon(
                    Icons.Rounded.PhotoAlbum,
                    "",
                    modifier = iconModifier
                )
            }
        }
        is MessageUpgradedGift -> {
            {
                Icon(
                    Icons.Rounded.CardGiftcard,
                    "",
                    modifier = iconModifier
                )
            }
        }
        is MessageVenue -> {
            {
                Icon(
                    Icons.Rounded.LocationOn,
                    "",
                    modifier = iconModifier
                )
            }
        }
        is MessageVideo -> {
            {
                if (content.video.minithumbnail?.data?.isNotEmpty() == true) {
                    ByteArrayImage(
                        imageData = content.video.minithumbnail!!.data,
                        contentDescription = "",
                        modifier = iconModifier.clip(RoundedCornerShape(4.dp))
                    )
                } else {
                    Icon(
                        Icons.Rounded.VideoFile,
                        "",
                        modifier = iconModifier
                    )
                }
            }
        }
        is MessageVideoNote -> {
            {
                if (content.videoNote.minithumbnail?.data?.isNotEmpty() == true) {
                    ByteArrayImage(
                        imageData = content.videoNote.minithumbnail!!.data,
                        contentDescription = "",
                        modifier = iconModifier.clip(CircleShape)
                    )
                } else {
                    Icon(
                        Icons.Rounded.PlayCircle,
                        "",
                        modifier = iconModifier
                    )
                }
            }
        }
        is MessageVoiceNote -> {
            {
                Icon(
                    Icons.Rounded.RecordVoiceOver,
                    "",
                    modifier = iconModifier
                )
            }
        }
        else -> null
    }
    Row(
        modifier = Modifier.padding(start = if (icon != null) 10.dp else 16.dp, end = 16.dp).padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.invoke()
        FormattedTextView(modifier = Modifier, text, textStyle, 1, TextOverflow.Ellipsis, clickable = false)
    }
}