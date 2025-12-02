package com.xxcactussell.presentation.messages.screen

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import com.xxcactussell.domain.messages.model.Animation
import com.xxcactussell.domain.messages.model.MessageAnimation
import com.xxcactussell.domain.messages.model.MessageContent
import com.xxcactussell.presentation.LocalRootViewModel
import com.xxcactussell.presentation.messages.model.MessageUiItem
import com.xxcactussell.presentation.tools.ColumnWidthOf
import java.io.File

@kotlin.OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AnimationMessageContent(
    message: MessageUiItem.MessageItem,
    textColor: Color,
    isSending: Boolean,
    isFailed: Boolean,
    onMediaClicked: (Long) -> Unit,
) {
    val content = message.message.content as MessageAnimation

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

        val imageHeight = content.animation.height
        val imageWidth = content.animation.width
        val aspectRatio = if (imageHeight != 0 && imageWidth != 0) imageWidth.toFloat() / imageHeight.toFloat() else 1F

        AnimationMessage(
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
            animation = content.animation
        )
        if (!content.showCaptionAboveMedia) {
            Caption(content.caption, textColor)
        }
    }
}

@ExperimentalMaterial3ExpressiveApi
@OptIn(UnstableApi::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AnimationMessage(
    modifier: Modifier = Modifier,
    animation: Animation,
) {
    val animationFile = animation.animation

    val rootViewModel = LocalRootViewModel.current
    val fileUpdates = rootViewModel.files.collectAsState()
    val file = fileUpdates.value[animationFile.id] ?: animationFile

    if (file.local.isDownloadingCompleted && file.local.path.isNotEmpty()) {
        if (animation.mimeType == "video/mp4") {
            val context = LocalContext.current
            val exoPlayer = remember(file.local.path) {
                ExoPlayer.Builder(context).build().apply {
                    setMediaItem(MediaItem.fromUri(Uri.fromFile(File(file.local.path))))
                    repeatMode = Player.REPEAT_MODE_ALL
                    playWhenReady = true
                    prepare()
                }
            }

            DisposableEffect(Unit) {
                onDispose {
                    exoPlayer.release()
                }
            }

            AndroidView(
                modifier = modifier,
                factory = {
                    PlayerView(it).apply {
                        player = exoPlayer
                        useController = false
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    }
                }
            )
        } else {
            val context = LocalContext.current
            val imageLoader = remember {
                ImageLoader.Builder(context)
                    .components {
                        add(ImageDecoderDecoder.Factory())
                    }
                    .build()
            }
            AsyncImage(
                model = File(file.local.path),
                contentDescription = "Animation",
                imageLoader = imageLoader,
                modifier = modifier,
                contentScale = ContentScale.Crop
            )
        }
    } else {
        Box(
            modifier = modifier.background(MaterialTheme.colorScheme.surfaceContainer),
            contentAlignment = Alignment.Center
        ) {
            if (file.local.isDownloadingActive) {
                LoadingIndicator()
            }
        }
        if (!file.local.isDownloadingActive && !file.local.isDownloadingCompleted) {
            LaunchedEffect(file.id) {
                rootViewModel.downloadFile(file.id, )
            }
        }
    }
}