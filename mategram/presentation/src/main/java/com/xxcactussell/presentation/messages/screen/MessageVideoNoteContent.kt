package com.xxcactussell.presentation.messages.screen

import android.graphics.BitmapFactory
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onVisibilityChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player // Не забудь импорт
import androidx.media3.common.util.UnstableApi
import com.xxcactussell.domain.MessageVideoNote
import com.xxcactussell.mategram.presentation.R
import com.xxcactussell.player.PlayerState
import com.xxcactussell.presentation.LocalPlayerViewModel
import com.xxcactussell.presentation.LocalRootViewModel
import com.xxcactussell.presentation.messages.model.MessageUiItem
import com.xxcactussell.presentation.messages.model.MessagesEvent
import com.xxcactussell.presentation.messages.model.getChatId
import com.xxcactussell.utils.toTimeFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@kotlin.OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MessageVideoNoteContent(
    message: MessageUiItem.MessageItem,
    playerState: PlayerState,
    onEvent: (Any) -> Unit,
) {
    val content = message.message.content as MessageVideoNote
    val video = content.videoNote
    val rootViewModel = LocalRootViewModel.current
    val playerViewModel = LocalPlayerViewModel.current
    val fileUpdates = rootViewModel.files.collectAsState()
    val file = fileUpdates.value[video.video.id] ?: video.video
    val isDownloaded = file.local.isDownloadingCompleted
    val downloadProgress: Float = file.local.downloadedSize.toFloat() / if (file.size != 0L) file.size else 1

    val playerInstance = LocalPlayerViewModel.current.getExoPlayerInstance()
    val isPlaying = playerState.currentMediaId == message.message.id.toString() && playerState.isPlaying
    val isPaused = playerState.currentMediaId == message.message.id.toString() && playerState.isPaused

    val playbackProgress = if (playerState.currentMediaId == message.message.id.toString() && playerState.duration > 0) {
        playerState.progress.toFloat() / playerState.duration.toFloat()
    } else {
        0f
    }
    val playbackProgressMs = if (playerState.currentMediaId == message.message.id.toString() && playerState.duration > 0) {
        playerState.progress
    } else {
        0
    }

    var playerInstanceForSurface by remember { mutableStateOf(playerInstance) }

    LaunchedEffect(Unit) {
        playerViewModel.switchPlayer(message.message.id, true)
    }

    Box(
        modifier = Modifier
            .onVisibilityChanged(minFractionVisible = 0f) {
                playerViewModel.switchPlayer(message.message.id, it)
                playerInstanceForSurface = if (!it) {
                    null
                } else playerInstance
            }
    ) {
        AnimatedContent(
            targetState = isPlaying || isPaused,
        ) { opened ->
            Box(
                modifier = Modifier
                    .size(if (opened) 240.dp else 180.dp)
            ) {
                if (opened && playerInstance != null) {
                    CircleVideoSurface(
                        player = playerInstanceForSurface,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                if (isPlaying) {
                                    onEvent(MessagesEvent.PauseMedia(message.message.id))
                                } else {
                                    onEvent(
                                        MessagesEvent.PlayVideo(
                                            message.message.id,
                                            message.getChatId()
                                        )
                                    )
                                }
                            }
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.3f)),
                        contentAlignment = Alignment.Center
                    ) {
                        val photoSize = video.thumbnail?.file ?: return@Box
                        val thumbnailFile = fileUpdates.value[photoSize.id] ?: photoSize

                        if (thumbnailFile.local.isDownloadingCompleted && thumbnailFile.local.path.isNotEmpty()) {
                            var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }

                            LaunchedEffect(thumbnailFile.local.path) {
                                withContext(Dispatchers.IO) {
                                    try {
                                        val decodedBitmap =
                                            BitmapFactory.decodeFile(thumbnailFile.local.path)
                                        bitmap = decodedBitmap?.asImageBitmap()
                                    } catch (e: Exception) {
                                        // Handle exceptions
                                    }
                                }
                            }

                            if (bitmap != null) {
                                Box(
                                    modifier = Modifier,
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Image(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clickable {
                                                if (!isDownloaded) {
                                                    if (!file.local.isDownloadingActive) {
                                                        rootViewModel.downloadFile(video.video.id)
                                                    }
                                                } else {
                                                    if (isPlaying) {
                                                        onEvent(MessagesEvent.PauseMedia(message.message.id))
                                                    } else {
                                                        onEvent(
                                                            MessagesEvent.PlayVideo(
                                                                message.message.id,
                                                                message.getChatId()
                                                            )
                                                        )
                                                    }
                                                }
                                            },
                                        bitmap = bitmap!!,
                                        contentDescription = "Photo",
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                if (file.local.isDownloadingActive) {
                                    LoadingIndicator()
                                }
                            }
                            if (!thumbnailFile.local.isDownloadingActive && !thumbnailFile.local.isDownloadingCompleted) {
                                LaunchedEffect(thumbnailFile.id) {
                                    rootViewModel.downloadFile(thumbnailFile.id)
                                }
                            }
                        }
                    }
                }

                if (opened) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(240.dp),
                        progress = { playbackProgress }
                    )
                }

                val buttonSize = IconButtonDefaults.mediumContainerSize()

                FilledTonalIconToggleButton(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .size(buttonSize),
                    checked = isPlaying,
                    shapes = IconButtonDefaults.toggleableShapes(),
                    onCheckedChange = {
                        if (!isDownloaded) {
                            if (!file.local.isDownloadingActive) {
                                rootViewModel.downloadFile(video.video.id)
                            }
                        } else {
                            if (isPlaying) {
                                onEvent(MessagesEvent.PauseMedia(message.message.id))
                            } else {
                                onEvent(
                                    MessagesEvent.PlayVideo(
                                        message.message.id,
                                        message.getChatId()
                                    )
                                )
                            }
                        }
                    }
                ) {
                    if (!isDownloaded) {
                        if (!file.local.isDownloadingActive) {
                            Icon(
                                painterResource(R.drawable.download_24px),
                                "download",
                                modifier = Modifier.size(IconButtonDefaults.mediumIconSize)
                            )
                        } else {
                            CircularWavyProgressIndicator(progress = { downloadProgress })
                        }
                    } else {
                        if (isPlaying) {
                            Icon(
                                painterResource(R.drawable.pause_24px),
                                "pause",
                                modifier = Modifier.size(IconButtonDefaults.mediumIconSize)
                            )
                        } else {
                            Icon(
                                painterResource(R.drawable.play_arrow_24px),
                                "play",
                                modifier = Modifier.size(IconButtonDefaults.mediumIconSize)
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        text = if (opened) (video.duration * 1000 - playbackProgressMs.toInt()).toTimeFormat() else (video.duration * 1000).toTimeFormat(),
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colorScheme.tertiaryContainer)
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                }
            }
    }
    }
}