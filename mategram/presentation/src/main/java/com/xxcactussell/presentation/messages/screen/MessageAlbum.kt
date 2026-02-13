package com.xxcactussell.presentation.messages.screen

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
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
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import com.xxcactussell.domain.MessageAnimation
import com.xxcactussell.domain.MessageDocument
import com.xxcactussell.domain.MessagePhoto
import com.xxcactussell.domain.MessageSendingStateFailed
import com.xxcactussell.domain.MessageSendingStatePending
import com.xxcactussell.domain.MessageVideo
import com.xxcactussell.presentation.LocalSharedTransitionScope
import com.xxcactussell.presentation.localization.localizedString
import com.xxcactussell.presentation.messages.model.MessageUiItem
import com.xxcactussell.presentation.messages.model.getChatId
import com.xxcactussell.presentation.tools.ColumnWidthOf

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MessageAlbum(messages: List<MessageUiItem.MessageItem>, onEvent: (Any) -> Unit, onMediaClicked: (Long) -> Unit) {
    val hasForward = messages.any { it.message.forwardFullInfo != null }
    val isOutgoing = messages.any { it.message.isOutgoing }

    val messageTextColor = if (hasForward) {
        MaterialTheme.colorScheme.onSurfaceVariant
    } else if (isOutgoing) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSecondaryContainer
    }

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



    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope = LocalNavAnimatedContentScope.current

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
        if (isTextAboveMessage != null) Caption(caption, messageTextColor)
        Column(
            Modifier.layoutId("carousel")
        ) {
            if (mediaMessages.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .width(320.dp),
                    contentAlignment = Alignment.Center
                ) {
                    with(sharedTransitionScope) {
                        HorizontalMultiBrowseCarousel(
                            modifier = Modifier
                                .width(320.dp)
                                .clip(MaterialTheme.shapes.large),
                            state = carouselState,
                            maxSmallItemWidth = 220.dp,
                            minSmallItemWidth = 92.dp,
                            preferredItemWidth = 220.dp,
                            itemSpacing = 2.dp
                        ) { id ->
                            val isSending =
                                messages[id].message.sendingState is MessageSendingStatePending
                            val isFailed =
                                messages[id].message.sendingState is MessageSendingStateFailed

                            when (val content = messages[id].message.content) {
                                is MessagePhoto -> {
                                    PhotoMessage(
                                        modifier = Modifier
                                            .height(320.dp)
                                            .width(256.dp)
                                            .maskClip(MaterialTheme.shapes.medium)
                                            .sharedBounds(
                                                rememberSharedContentState(key = "bounds_${messages[id].message.id}"),
                                                animatedVisibilityScope = animatedVisibilityScope,
                                                enter = fadeIn(),
                                                exit = fadeOut(),
                                                resizeMode = SharedTransitionScope.ResizeMode.scaleToBounds()
                                            ),
                                        messageId = messages[id].message.id,
                                        photo = content.photo,
                                        isSending = isSending,
                                        onMediaClicked = onMediaClicked,
                                        onEvent = onEvent,
                                        chatId = messages[id].getChatId(),
                                        isFailed = isFailed
                                    )
                                }

                                is MessageVideo -> {
                                    MessageVideoContent(
                                        modifier = Modifier
                                            .height(320.dp)
                                            .width(256.dp)
                                            .maskClip(MaterialTheme.shapes.medium)
                                            .sharedBounds(
                                                rememberSharedContentState(key = "bounds_${messages[id].message.id}"),
                                                animatedVisibilityScope = animatedVisibilityScope,
                                                enter = fadeIn(),
                                                exit = fadeOut(),
                                                resizeMode = SharedTransitionScope.ResizeMode.scaleToBounds()
                                            ),
                                        messageId = messages[id].message.id,
                                        video = content.video,
                                        videoCover = content.cover,
                                        isSending = isSending,
                                        onEvent = onEvent,
                                        onMediaClicked = onMediaClicked,
                                        chatId = messages[id].getChatId(),
                                        isFailed = isFailed
                                    )
                                }

                                is MessageAnimation -> {
                                    AnimationMessage(
                                        modifier = Modifier
                                            .height(320.dp)
                                            .width(256.dp)
                                            .maskClip(MaterialTheme.shapes.medium)
                                            .sharedBounds(
                                                rememberSharedContentState(key = "bounds_${messages[id].message.id}"),
                                                animatedVisibilityScope = animatedVisibilityScope,
                                                enter = fadeIn(),
                                                exit = fadeOut(),
                                                resizeMode = SharedTransitionScope.ResizeMode.scaleToBounds()
                                            ),
                                        animation = content.animation
                                    )
                                }

                                else -> {}
                            }
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
        if (isTextAboveMessage == null) Caption(caption, messageTextColor)
    }
}