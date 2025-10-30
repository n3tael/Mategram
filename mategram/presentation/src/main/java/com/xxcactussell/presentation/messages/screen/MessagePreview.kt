package com.xxcactussell.presentation.messages.screen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.InsertDriveFile
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.Photo
import androidx.compose.material.icons.rounded.VideoCameraBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.xxcactussell.domain.messages.model.FormattedText
import com.xxcactussell.domain.messages.model.Message
import com.xxcactussell.domain.messages.model.MessageContent
import com.xxcactussell.domain.messages.model.MiniThumbnail
import com.xxcactussell.domain.messages.model.TextEntity
import com.xxcactussell.domain.messages.model.TextEntityType
import com.xxcactussell.presentation.localization.localizedString
import com.xxcactussell.presentation.tools.FormattedTextView

data class MessagePreviewState(
    val text: FormattedText,
    val icon: ImageVector? = null,
    val thumbnail: MiniThumbnail? = null
)

@Composable
fun MessagePreview(message: Message) {
    val content = message.content
    val state = when(content) {
        is MessageContent.Text -> {
            MessagePreviewState(
                text = content.text
            )
        }
        is MessageContent.MessagePhoto -> {
            val text = if (content.caption.text.isEmpty()) {
                if (message.mediaAlbumId != 0L) {
                    FormattedText(localizedString("Album"))
                } else {
                    FormattedText(localizedString("AttachPhoto"))
                }
            } else {
                content.caption
            }
            MessagePreviewState(
                text = text,
                icon = Icons.Rounded.Photo,
                thumbnail = content.photo.miniThumbnail
            )
        }
        is MessageContent.MessageVideo -> {
            val text = if (content.caption.text.isEmpty()) {
                if (message.mediaAlbumId != 0L) {
                    FormattedText(localizedString("Album"))
                } else {
                    FormattedText(localizedString("AttachVideo"))
                }
            } else {
                content.caption
            }
            MessagePreviewState(
                text = text,
                icon = Icons.Rounded.VideoCameraBack,
                thumbnail = content.cover?.miniThumbnail
            )
        }
        is MessageContent.MessageAudio -> {
            MessagePreviewState(
                text = FormattedText("${content.audio.performer} - ${content.audio.title}"),
                icon = Icons.Rounded.MusicNote
            )
        }
        is MessageContent.MessageAnimatedEmoji -> {
            val entities = if (content.animatedEmoji.sticker != null) {
                listOf(TextEntity(0,1, TextEntityType.CustomEmoji(
                    content.animatedEmoji.sticker!!.id
                )))
            } else emptyList()
            MessagePreviewState(
                text = FormattedText(content.emoji, entities)
            )
        }
        is MessageContent.MessageSticker -> {
            MessagePreviewState(
                text = FormattedText("${content.sticker.emoji} ${localizedString("AttachSticker")}")
            )
        }
        is MessageContent.MessageDice -> {
            MessagePreviewState(
                text = FormattedText(content.emoji)
            )
        }
        is MessageContent.MessageDocument -> {
            MessagePreviewState(
                text = if (content.caption.text.isNotEmpty()) {
                    content.caption
                } else {
                    FormattedText(content.document.fileName)
                },
                icon = Icons.AutoMirrored.Rounded.InsertDriveFile
            )
        }
        //TODO
        is MessageContent.MessageAnimation -> {
            MessagePreviewState(
                text = FormattedText(content.fileId)
            )
        }
        else -> {
            MessagePreviewState(
                text = FormattedText(content.toString())
            )
        }
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(message.isOutgoing) {
            Text(
                text = "${localizedString("FromYou")}:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
        if(state.thumbnail?.data != null) {
            ByteArrayImage(
                imageData = state.thumbnail.data!!,
                contentDescription = "Медиа в ответе",
                modifier = Modifier
                    .size(16.dp)
                    .clip(RoundedCornerShape(4.dp)),
            )
        } else if (state.icon != null) {
            Icon(
                imageVector = state.icon,
                contentDescription = "Медиа в ответе",
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        FormattedTextView(
            text = state.text,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            clickable = false
        )
    }
}

@Composable
fun ByteArrayImage(
    imageData: ByteArray,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    val bitmap = remember(imageData) {
        BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
    }

    Image(
        bitmap = bitmap?.asImageBitmap() ?: ImageBitmap(1, 1),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}