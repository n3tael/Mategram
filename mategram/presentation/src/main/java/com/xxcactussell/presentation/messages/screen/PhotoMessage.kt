package com.xxcactussell.presentation.messages.screen

import android.graphics.BitmapFactory
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import com.xxcactussell.domain.messages.model.Photo
import com.xxcactussell.domain.messages.model.Video
import com.xxcactussell.presentation.LocalNavAnimatedVisibilityScope
import com.xxcactussell.presentation.LocalRootViewModel
import com.xxcactussell.presentation.LocalSharedTransitionScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun PhotoMessage(
    modifier: Modifier = Modifier,
    messageId: Long,
    photo: Photo,
    isSending: Boolean,
    uploadProgress: () -> Float,
    isFailed: Boolean,
    onMediaClicked: (Long) -> Unit
) {
    val photoSize = photo.sizes.maxByOrNull { it.width }?.photo ?: return

    val rootViewModel = LocalRootViewModel.current
    val fileUpdates = rootViewModel.files.collectAsState()

    val file = fileUpdates.value[photoSize.id] ?: photoSize


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

        val sharedTransitionScope = LocalSharedTransitionScope.current
            ?: throw IllegalStateException("No SharedElementScope found")
        val animatedVisibilityScope = LocalNavAnimatedContentScope.current

        if (bitmap != null) {
            with(sharedTransitionScope) {
                Box(
                    modifier = Modifier
                        .sharedBounds(
                            rememberSharedContentState(key = "bounds_$messageId"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            enter = fadeIn(),
                            exit = fadeOut(),
                            resizeMode = SharedTransitionScope.ResizeMode.scaleToBounds()
                        ).then(modifier),
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
                        CircularWavyProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary,
                            progress = { uploadProgress() }
                        )
                        IconButton(
                            onClick = { /*TODO*/ },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Icon(
                                Icons.Rounded.Close,
                                "Stop loading"
                            )
                        }
                    } else if (isFailed) {
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                Icons.Rounded.Refresh,
                                "Retry loading"
                            )
                        }
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
fun VideoMessage(
    modifier: Modifier = Modifier,
    messageId: Long,
    video: Video,
    videoCover: Photo?,
    isSending: Boolean,
    uploadProgress: () -> Float,
    isFailed: Boolean,
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

        val sharedTransitionScope = LocalSharedTransitionScope.current
            ?: throw IllegalStateException("No SharedElementScope found")
        val animatedVisibilityScope = LocalNavAnimatedContentScope.current

        if (bitmap != null) {
            with(sharedTransitionScope) {
                Box(
                    modifier = Modifier
                        .sharedBounds(
                            rememberSharedContentState(key = "bounds_$messageId"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            enter = fadeIn(),
                            exit = fadeOut(),
                            resizeMode = SharedTransitionScope.ResizeMode.scaleToBounds()
                        ).then(modifier),
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
                        CircularWavyProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary,
                            progress = { uploadProgress() }
                        )
                        IconButton(
                            onClick = { /*TODO*/ },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Icon(
                                Icons.Rounded.Close,
                                "Stop loading"
                            )
                        }
                    } else if (isFailed) {
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                Icons.Rounded.Refresh,
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
                                Icons.Rounded.PlayArrow,
                                "Play"
                            )
                        }
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