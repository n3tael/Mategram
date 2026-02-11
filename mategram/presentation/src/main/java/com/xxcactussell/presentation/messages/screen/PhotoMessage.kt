package com.xxcactussell.presentation.messages.screen

import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import com.xxcactussell.domain.Photo
import com.xxcactussell.domain.Video
import com.xxcactussell.mategram.presentation.R
import com.xxcactussell.presentation.LocalRootViewModel
import com.xxcactussell.presentation.LocalSharedTransitionScope
import com.xxcactussell.presentation.messages.model.MessagesEvent
import com.xxcactussell.presentation.tools.messageContentAspectRatio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun PhotoMessage(
    modifier: Modifier = Modifier,
    messageId: Long,
    chatId: Long,
    photo: Photo,
    isSending: Boolean,
    isFailed: Boolean,
    onEvent: (Any) -> Unit,
    onMediaClicked: (Long) -> Unit
) {
    val photoSize = photo.sizes.maxByOrNull { it.width }?.photo ?: return

    val rootViewModel = LocalRootViewModel.current
    val fileUpdates = rootViewModel.files.collectAsState()

    val file = fileUpdates.value[photoSize.id] ?: photoSize
    val fileState = rootViewModel.observeFileStatus(file.id).collectAsState(initial = null)

    if (file.local.isDownloadingCompleted && file.local.path.isNotEmpty()) {
        var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }

        LaunchedEffect(file.local.path) {
            withContext(Dispatchers.IO) {
                try {
                    val decodedBitmap = BitmapFactory.decodeFile(file.local.path)
                    bitmap = decodedBitmap?.asImageBitmap()
                } catch (e: Exception) {
                    // Handle exceptions
                }
            }
        }

        if (bitmap != null) {
            Box(
                modifier = Modifier
                    .then(modifier),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onMediaClicked(messageId)
                        },
                    bitmap = bitmap!!,
                    contentDescription = "Photo",
                    contentScale = ContentScale.Crop
                )
                if (isSending) {
                    Box(
                        Modifier
                            .clip(CircleShape)
                            .size(48.dp)
                            .background(MaterialTheme.colorScheme.surfaceContainer),
                        Alignment.Center
                    ) {
                        CircularWavyProgressIndicator(
                            progress = {
                                if ((fileState.value?.expectedSize ?: 0) > 0) {
                                    (fileState.value?.remote?.uploadedSize?.toFloat()
                                        ?: 0f) / (fileState.value?.expectedSize?.toFloat()
                                        ?: 1f)
                                } else {
                                    0f
                                }
                            }
                        )
                        IconButton(
                            onClick = {
                                onEvent(
                                    MessagesEvent.CancelUploadFile(
                                        messageId,
                                        chatId
                                    )
                                )
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Icon(
                                painterResource(R.drawable.close_24px),
                                "Stop loading",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                } else if (isFailed) {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            painterResource(R.drawable.refresh_24px),
                            "Retry loading"
                        )
                    }
                }
            }
        } else {
            Box(
                modifier = modifier.background(MaterialTheme.colorScheme.surfaceContainer)
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
                rootViewModel.downloadFile(file.id)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MessageVideoContent(
    modifier: Modifier = Modifier,
    chatId: Long,
    messageId: Long,
    video: Video,
    videoCover: Photo?,
    isSending: Boolean,
    isFailed: Boolean,
    onEvent: (Any) -> Unit,
    onMediaClicked: (Long) -> Unit
) {
    val cover = if (videoCover != null) {
        videoCover.sizes.maxByOrNull { it.width }?.photo ?: return
    } else {
        video.thumbnail?.file ?: return
    }

    val rootViewModel = LocalRootViewModel.current
    val fileUpdates = rootViewModel.files.collectAsState()

    val file = fileUpdates.value[cover.id] ?: cover
    val fileState = rootViewModel.observeFileStatus(video.video.id).collectAsState(initial = null)

    if (file.local.isDownloadingCompleted && file.local.path.isNotEmpty()) {
        var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }

        LaunchedEffect(file.local.path) {
            withContext(Dispatchers.IO) {
                val path = file.local.path
                val retriever = MediaMetadataRetriever()

                try {
                    retriever.setDataSource(path)
                    val hasVideo = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_HAS_VIDEO)
                    val decodedBitmap = if (hasVideo == "yes") {
                        retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
                    } else {
                        BitmapFactory.decodeFile(path)
                    }

                    bitmap = decodedBitmap?.asImageBitmap()
                } catch (e: Exception) {
                    bitmap = BitmapFactory.decodeFile(path)?.asImageBitmap()
                } finally {
                    try { retriever.release() } catch (e: Exception) {}
                }
            }
        }

        if (bitmap != null) {
            Box(
                modifier = Modifier
                    .then(modifier),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    bitmap = bitmap!!,
                    contentDescription = "Photo",
                    contentScale = ContentScale.Crop
                )
                if (isSending) {
                    Box(
                        Modifier
                            .clip(CircleShape)
                            .size(48.dp)
                            .background(MaterialTheme.colorScheme.surfaceContainer),
                        Alignment.Center
                    ) {
                        CircularWavyProgressIndicator(
                            progress = {
                                if ((fileState.value?.expectedSize ?: 0) > 0) {
                                    (fileState.value?.remote?.uploadedSize?.toFloat()
                                        ?: 0f) / (fileState.value?.expectedSize?.toFloat()
                                        ?: 1f)
                                } else {
                                    0f
                                }
                            }
                        )
                        IconButton(
                            onClick = {
                                onEvent(MessagesEvent.CancelUploadFile(messageId, chatId))
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Icon(
                                painterResource(R.drawable.close_24px),
                                "Stop loading",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                } else if (isFailed) {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            painterResource(R.drawable.refresh_24px),
                            "Retry loading"
                        )
                    }
                } else {
                    FilledTonalIconButton(
                        onClick = {
                            onMediaClicked(messageId)
                        }
                    ) {
                        Icon(
                            painterResource(R.drawable.play_arrow_24px),
                            "Play"
                        )
                    }
                }
            }
        } else {
            Box(
                modifier = modifier.background(MaterialTheme.colorScheme.surfaceContainer)
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
                rootViewModel.downloadFile(file.id)
            }
        }
    }
}