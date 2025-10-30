package com.xxcactussell.presentation.messages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xxcactussell.domain.messages.model.MessageContent
import com.xxcactussell.domain.messages.model.MessageStatus
import com.xxcactussell.domain.messages.model.Sticker
import com.xxcactussell.domain.messages.model.StickerFormat
import com.xxcactussell.presentation.LocalRootViewModel
import com.xxcactussell.presentation.messages.model.MessageUiItem
import com.xxcactussell.presentation.tools.ColumnWidthOf
import com.xxcactussell.presentation.tools.FormattedTextView
import com.xxcactussell.presentation.tools.LottieSticker
import com.xxcactussell.presentation.tools.StickerWEBMPlayer
import com.xxcactussell.presentation.tools.WebPImage

@Composable
fun MessageContent(message: MessageUiItem.MessageItem) {
    val messageTextColor = MaterialTheme.colorScheme.onSurface
    val isSending = message.message.sendingState is MessageStatus.Pending
    val isFailed = message.message.sendingState is MessageStatus.Failed

    when (val content = message.message.content) {
        is MessageContent.Text -> MessageTextContent(content, messageTextColor)
        is MessageContent.MessagePhoto -> MessagePhotoContent(message, messageTextColor, isSending, isFailed)
        is MessageContent.MessageVideo -> MessageVideoContent(message, messageTextColor, isSending, isFailed)
        is MessageContent.MessageSticker -> MessageStickerContent(content.sticker, 172.dp)
        is MessageContent.MessageAnimatedEmoji -> MessageStickerContent(content.animatedEmoji.sticker, 92.dp, content.emoji)
        else -> {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Unsupported message type: ${content.javaClass.simpleName}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun MessageStickerContent(sticker: Sticker?, size: Dp, emoji: String = "") {
    if (sticker!= null) {
        val localSticker = sticker.sticker

        val rootViewModel = LocalRootViewModel.current
        val fileUpdates = rootViewModel.files.collectAsState()

        val file = fileUpdates.value[sticker.sticker.id] ?: localSticker
        val path = file.local.path

        if (file.local.isDownloadingComplete && path.isNotEmpty()) {
            when (sticker.format) {
                is StickerFormat.Tgs -> {
                    LottieSticker(path = path, size = size)
                }
                is StickerFormat.Webm -> {
                    val aspectRatio = if (sticker.width != 0 && sticker.height != 0) sticker.width.toFloat() / sticker.height.toFloat() else 1f
                    StickerWEBMPlayer(
                        videoPath = path,
                        modifier = Modifier.widthIn(max = size).heightIn(max = size).aspectRatio(aspectRatio)
                    )
                }
                is StickerFormat.Webp -> {
                    WebPImage(path = path, size = size)
                }
                else -> {
                    Box(
                        modifier = Modifier.size(size).clip(RoundedCornerShape(24.dp)).background(MaterialTheme.colorScheme.secondaryContainer)
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier.size(size).clip(RoundedCornerShape(24.dp)).background(MaterialTheme.colorScheme.secondaryContainer)
            )
            if (!file.local.isDownloadingActive) {
                LaunchedEffect(key1 = file.id) {
                    rootViewModel.downloadFile(file.id)
                }
            }
        }
    }
}

@Composable
fun MessageVideoContent(
    message: MessageUiItem.MessageItem,
    textColor: Color,
    isSending: Boolean,
    isFailed: Boolean
) {
    val content = message.message.content as MessageContent.MessageVideo

    ColumnWidthOf(
        modifier =
            if(content.caption.text.isEmpty())
                Modifier
            else if (content.showCaptionAboveMedia)
                Modifier.padding(top = 8.dp)
            else
                Modifier.padding(bottom = 8.dp),
        rulerId = "photo",
        horizontalSpacers = 8.dp
    ) {
        if (content.showCaptionAboveMedia) {
            Caption(content.caption, textColor)
        }

        val imageHeight = content.video.thumbnail?.height ?: 0
        val imageWidth = content.video.thumbnail?.width ?: 0
        val aspectRatio = if (imageHeight != 0 && imageWidth != 0) imageWidth.toFloat() / imageHeight.toFloat() else 1F

        VideoMessage(
            modifier = Modifier
                .layoutId("photo")
                .then(
                    if (imageHeight < imageWidth) {
                        Modifier
                            .width(320.dp)
                            .aspectRatio(aspectRatio)
                            .heightIn(min = 80.dp, max = 320.dp)
                    } else {
                        Modifier
                            .height(320.dp)
                            .aspectRatio(aspectRatio)
                            .widthIn(min = 80.dp, max = 320.dp)
                    }
                )
                .clip(RoundedCornerShape(14.dp)),
            video = content.video,
            isSending = isSending,
            isFailed = isFailed,
            videoCover = content.cover,
            uploadProgress = { 0.0F } //TODO
        )
        if (!content.showCaptionAboveMedia) {
            Caption(content.caption, textColor)
        }
    }
}

@Composable
fun MessagePhotoContent(
    message: MessageUiItem.MessageItem,
    textColor: Color,
    isSending: Boolean,
    isFailed: Boolean
) {
    val content = message.message.content as MessageContent.MessagePhoto

    ColumnWidthOf(
        modifier =
            if(content.caption.text.isEmpty())
                Modifier
            else if (content.showCaptionAboveMedia)
                Modifier.padding(top = 8.dp)
            else
                Modifier.padding(bottom = 8.dp),
        rulerId = "photo",
        horizontalSpacers = 8.dp
    ) {
        if (content.showCaptionAboveMedia) {
            Caption(content.caption, textColor)
        }

        val imageHeight = content.photo.sizes.maxByOrNull { it.width }?.height ?: 0
        val imageWidth = content.photo.sizes.maxByOrNull { it.width }?.width ?: 0
        val aspectRatio = if (imageHeight != 0 && imageWidth != 0) imageWidth.toFloat() / imageHeight.toFloat() else 1F

        PhotoMessage(
            modifier = Modifier
                .layoutId("photo")
                .then(
                    if (imageHeight < imageWidth) {
                        Modifier
                            .width(320.dp)
                            .aspectRatio(aspectRatio)
                            .heightIn(min = 80.dp, max = 320.dp)
                    } else {
                        Modifier
                            .height(320.dp)
                            .aspectRatio(aspectRatio)
                            .widthIn(min = 80.dp, max = 320.dp)
                    }
                )
                .clip(RoundedCornerShape(14.dp)),
            photo = content.photo,
            isSending = isSending,
            isFailed = isFailed,
            uploadProgress = { 0.0F } //TODO
        )
        if (!content.showCaptionAboveMedia) {
            Caption(content.caption, textColor)
        }
    }
}

@Composable
fun MessageTextContent(content: MessageContent.Text, textColor: Color = MaterialTheme.colorScheme.onSurface) {
    FormattedTextView(text = content.text, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(16.dp, 8.dp), color = textColor)
}