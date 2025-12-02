package com.xxcactussell.presentation.messages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import com.xxcactussell.domain.messages.model.MessageAnimation
import com.xxcactussell.domain.messages.model.MessageContent
import com.xxcactussell.domain.messages.model.MessageDocument
import com.xxcactussell.domain.messages.model.MessagePhoto
import com.xxcactussell.domain.messages.model.MessageStatus
import com.xxcactussell.domain.messages.model.MessageVideo
import com.xxcactussell.presentation.localization.localizedString
import com.xxcactussell.presentation.messages.model.MessageUiItem
import com.xxcactussell.presentation.tools.ColumnWidthOf

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MessageAlbum(messages: List<MessageUiItem.MessageItem>, onMediaClicked: (Long) -> Unit) {
    val caption = messages.firstNotNullOfOrNull {
        when (val content = it.message.content) {
            is MessagePhoto -> if (content.caption.text.isNotEmpty()) content.caption else null
            is MessageVideo -> if (content.caption.text.isNotEmpty()) content.caption else null
            is MessageAnimation -> if (content.caption.text.isNotEmpty()) content.caption else null
            is MessageDocument -> if (content.caption.text.isNotEmpty()) content.caption else null
            else -> null
        }
    }

    val isTextAboveMessage = messages.firstOrNull {
        when (it.message.content) {
            is MessagePhoto -> {
                (it.message.content as MessagePhoto).showCaptionAboveMedia
            }
            is MessageVideo -> {
                (it.message.content as MessageVideo).showCaptionAboveMedia
            }
            is MessageAnimation -> {
                (it.message.content as MessageAnimation).showCaptionAboveMedia
            }
            else -> {
                false
            }
        }
    }

    val mediaMessages = messages.filter { it.message.content is MessagePhoto || it.message.content is MessageVideo }
    val docsMessages = messages.filter { it.message.content is MessageDocument }

    val carouselState = rememberCarouselState{ mediaMessages.count() }

    ColumnWidthOf(
        modifier =
            if(caption?.text.isNullOrEmpty())
                Modifier
            else if (isTextAboveMessage != null)
                Modifier.padding(top = 8.dp)
            else
                Modifier.padding(bottom = 8.dp),
        rulerId = "carousel",
        horizontalSpacers = 8.dp
    ) {
        if (isTextAboveMessage != null) Caption(caption)
        Column(
            Modifier.layoutId("carousel")
        ) {
            if (mediaMessages.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .width(320.dp),
                    contentAlignment = Alignment.Center
                ) {
                    HorizontalMultiBrowseCarousel(
                        modifier = Modifier
                            .width(320.dp)
                            .clip(MaterialTheme.shapes.large),
                        state = carouselState,
                        maxSmallItemWidth = 320.dp,
                        minSmallItemWidth = 256.dp,
                        preferredItemWidth = 256.dp,
                        itemSpacing = 2.dp
                    ) { id ->
                        val isSending = messages[id].message.sendingState is MessageStatus.Pending
                        val isFailed = messages[id].message.sendingState is MessageStatus.Failed
                        when (val content = messages[id].message.content) {
                            is MessagePhoto -> {
                                PhotoMessage(
                                    modifier = Modifier
                                        .height(320.dp)
                                        .width(256.dp)
                                        .maskClip(MaterialTheme.shapes.medium),
                                    messageId = messages[id].message.id,
                                    photo = content.photo,
                                    isSending = isSending,
                                    uploadProgress = { 0.0F },
                                    onMediaClicked = onMediaClicked,
                                    isFailed = isFailed,
                                    // TODO
                                )
                            }

                            is MessageVideo -> {
                                VideoMessage(
                                    modifier = Modifier
                                        .height(320.dp)
                                        .width(256.dp)
                                        .maskClip(MaterialTheme.shapes.medium),
                                    messageId = messages[id].message.id,
                                    video = content.video,
                                    videoCover = content.cover,
                                    isSending = isSending,
                                    uploadProgress = { 0.0F },
                                    onMediaClicked = onMediaClicked,
                                    isFailed = isFailed,
                                    // TODO
                                )
                            }

                            is MessageAnimation -> {
                                AnimationMessage(
                                    modifier = Modifier
                                        .height(320.dp)
                                        .width(256.dp)
                                        .maskClip(MaterialTheme.shapes.medium),
                                    animation = content.animation
                                )
                            }

                            else -> {}
                        }
                    }
                    Box(
                        Modifier
                            .padding(16.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 8.dp),
                            text = localizedString("Media", 1, messages.count()),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
            docsMessages.forEach { doc ->
                DocumentMessageContent(doc.message.content as MessageDocument) { }
            }
        }
        if (isTextAboveMessage == null) Caption(caption)
    }
}