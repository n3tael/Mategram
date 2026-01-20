package com.xxcactussell.presentation.mediaviewer.screen

import android.annotation.SuppressLint
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.compose.PlayerSurface
import androidx.media3.ui.compose.SURFACE_TYPE_SURFACE_VIEW
import androidx.media3.ui.compose.modifiers.resizeWithContentScale
import androidx.media3.ui.compose.state.rememberPlayPauseButtonState
import androidx.media3.ui.compose.state.rememberPlaybackSpeedState
import androidx.media3.ui.compose.state.rememberPresentationState
import androidx.media3.ui.compose.state.rememberProgressStateWithTickInterval
import androidx.media3.ui.compose.state.rememberSeekBackButtonState
import androidx.media3.ui.compose.state.rememberSeekForwardButtonState
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import com.xxcactussell.domain.File
import com.xxcactussell.domain.FormattedText
import com.xxcactussell.domain.MessageContent
import com.xxcactussell.domain.MessageVideo
import com.xxcactussell.mategram.presentation.R
import com.xxcactussell.presentation.LocalRootViewModel
import com.xxcactussell.presentation.LocalSharedTransitionScope
import com.xxcactussell.presentation.mediaviewer.model.MediaPageUiState
import com.xxcactussell.presentation.mediaviewer.viewmodel.MediaViewerViewModel
import com.xxcactussell.presentation.mediaviewer.viewmodel.MediaViewerViewModelFactory
import com.xxcactussell.presentation.tools.WavyTimeline
import com.xxcactussell.presentation.tools.screenStyle
import me.saket.telephoto.zoomable.coil3.ZoomableAsyncImage
import me.saket.telephoto.zoomable.rememberZoomableImageState
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@kotlin.OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class, ExperimentalSharedTransitionApi::class
)
@Composable
fun MediaViewerScreen(
    messageId: Long,
    chatId: Long,
    onBack: () -> Unit,
) {
    val mediaViewModel: MediaViewerViewModel = hiltViewModel(
        creationCallback = { factory: MediaViewerViewModelFactory ->
            factory.create(messageId, chatId)
        },
        key = "media_viewer_screen_$messageId"
    )

    val uiState by mediaViewModel.state.collectAsStateWithLifecycle()
    val isUiVisible by mediaViewModel.isUiVisible.collectAsStateWithLifecycle()

    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope = LocalNavAnimatedContentScope.current

    with(sharedTransitionScope) {
        Scaffold(
            modifier = Modifier.sharedBounds(
                rememberSharedContentState(key = "bounds_$messageId"),
                animatedVisibilityScope = animatedVisibilityScope,
                enter = fadeIn(),
                exit = fadeOut(),
                resizeMode = SharedTransitionScope.ResizeMode.scaleToBounds()
            ).screenStyle(),
            containerColor = Color.Black,
            topBar = {
                AnimatedVisibility(
                    visible = isUiVisible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    TopAppBar(
                        title = { },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(
                                    painterResource(R.drawable.arrow_back_24px),
                                    contentDescription = "Back",
                                    tint = Color.White
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Black.copy(
                                alpha = 0.4f
                            )
                        )
                    )
                }
            }
        ) { _ ->

            if (uiState.isLoading && uiState.items.isEmpty()) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingIndicator(color = Color.White)
                }
            } else {
                val pagerState = rememberPagerState(
                    initialPage = uiState.initialIndex,
                    pageCount = { uiState.items.size }
                )

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                    key = { index -> uiState.items[index].messageId },
                    reverseLayout = true,
                    beyondViewportPageCount = 1
                ) { pageIndex ->

                    val item = uiState.items[pageIndex]
                    val isSelected = pagerState.currentPage == pageIndex

                    MediaPageItem(
                        file = item.file,
                        isVideo = item.isVideo,
                        caption = item.caption,
                        content = item.content,
                        isUiVisible = isUiVisible,
                        isSelected = isSelected,
                        onTap = { mediaViewModel.toggleUiVisibility() },
                        mediaViewModel = mediaViewModel
                    )
                }
            }
        }
    }
}

@kotlin.OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MediaPageItem(
    file: File,
    caption: FormattedText?,
    content: MessageContent?,
    isVideo: Boolean,
    isSelected: Boolean,
    mediaViewModel: MediaViewerViewModel,
    onTap: () -> Unit,
    isUiVisible: Boolean,
) {
    val rootViewModel = LocalRootViewModel.current
    val fileUpdates = rootViewModel.files.collectAsState()

    val remoteFile = fileUpdates.value[file.id] ?: file

    val supportStreaming = when(content) {
        is MessageVideo -> content.video.supportsStreaming
        else -> false
    }

    val uiState = mediaViewModel.getUiStateForFile(remoteFile, isVideo, supportStreaming)

    LaunchedEffect(remoteFile.id, remoteFile.local.path) {
        if (remoteFile.local.path.isEmpty() &&
            !remoteFile.local.isDownloadingActive &&
            !remoteFile.local.isDownloadingCompleted) {
            rootViewModel.downloadFile(remoteFile.id)
        }
    }

    val fileState = rootViewModel.observeFileStatus(remoteFile.id).collectAsState(initial = null)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is MediaPageUiState.Loading -> {
                LoadingIndicator(color = Color.White, progress = { (fileState.value?.local?.downloadedSize?.toFloat() ?: 1f) / (fileState.value?.expectedSize?.toFloat() ?: 1f) })
            }
            is MediaPageUiState.Content -> {
                if (uiState.isVideo) {
                    VideoPlayer(
                        path = uiState.path,
                        content = content,
                        play = isSelected,
                        onTap = onTap,
                        isUiVisible = isUiVisible,
                        downloadedProgress = (fileState.value?.local?.downloadedSize?.toFloat() ?: 1f) / (fileState.value?.expectedSize?.toFloat() ?: 1f)
                    )
                } else {
                    ZoomableImageContent(
                        imagePath = uiState.path,
                        onTap = onTap
                    )
                }
            }
        }
    }
}

@Composable
fun ZoomableImageContent(
    imagePath: String,
    onTap: () -> Unit,
) {
    ZoomableAsyncImage(
        model = imagePath,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        state = rememberZoomableImageState(),
        contentScale = ContentScale.Fit,
        onClick = { onTap() }
    )
}

val CONTENT_SCALES =
    listOf(
        "Fit" to ContentScale.Fit,
        "Crop" to ContentScale.Crop,
        "None" to ContentScale.None,
        "Inside" to ContentScale.Inside,
        "FillBounds" to ContentScale.FillBounds,
        "FillHeight" to ContentScale.FillHeight,
        "FillWidth" to ContentScale.FillWidth,
    )

@Composable
internal fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier =
    clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
    ) {
        onClick()
    }

@ExperimentalMaterial3ExpressiveApi
@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(
    path: String,
    content: MessageContent?,
    play: Boolean,
    onTap: () -> Unit,
    downloadedProgress: Float,
    isUiVisible: Boolean,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var isPlayerReady by remember { mutableStateOf(false) }
    var isBuffering by remember { mutableStateOf(false) }

    var currentContentScaleIndex by remember { mutableIntStateOf(0) }
    val contentScale = CONTENT_SCALES[currentContentScaleIndex].second

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (path.isNotEmpty()) {
            val exoPlayer = remember {
                ExoPlayer.Builder(context).apply {
                    setSeekBackIncrementMs(5000)
                    setSeekForwardIncrementMs(10000)
                }.build().apply {
                    videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
                    repeatMode = Player.REPEAT_MODE_ONE
                }
            }

            val presentationState = rememberPresentationState(exoPlayer)
            val scaledModifier = Modifier.resizeWithContentScale(contentScale, presentationState.videoSizeDp)

            DisposableEffect(exoPlayer) {
                val listener = object : Player.Listener {
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        isPlayerReady = playbackState == Player.STATE_READY
                        isBuffering = playbackState == Player.STATE_BUFFERING
                    }
                }
                exoPlayer.addListener(listener)
                onDispose { exoPlayer.removeListener(listener) }
            }

            LaunchedEffect(path) {
                val mediaItem = MediaItem.fromUri(path)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
            }

            LaunchedEffect(play) {
                if (play) exoPlayer.play() else exoPlayer.pause()
            }

            DisposableEffect(lifecycleOwner) {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_PAUSE) exoPlayer.pause()
                    else if (event == Lifecycle.Event.ON_RESUME && play) exoPlayer.play()
                }
                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                    exoPlayer.release()
                }
            }

            PlayerSurface(
                player = exoPlayer,
                surfaceType = SURFACE_TYPE_SURFACE_VIEW,
                modifier = scaledModifier.noRippleClickable { onTap() },
            )

            if (presentationState.coverSurface) {
                Box(Modifier
                    .matchParentSize()
                    .background(Color.Black))
            }

            if (isUiVisible) {
                MinimalControls(exoPlayer, Modifier
                    .align(Alignment.Center))
                ExtraControls(
                    exoPlayer,
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(Color.Black.copy(alpha = 0.4f))
                        .navigationBarsPadding()
                        .padding(horizontal = 16.dp),
                )
            }
        }

        val showLoader = !isPlayerReady || isBuffering

        AnimatedVisibility(
            visible = showLoader,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularWavyProgressIndicator(
                    progress = { downloadedProgress },
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.2f),
                )
            }
        }
    }
}

@kotlin.OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun ExtraControls(player: Player, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        ProgressTimeline(
            modifier = Modifier.fillMaxWidth(),
            player = player,
            seekTo = {
                player.seekTo(it)
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TimerText(player)
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //FOR MORE BUTTONS
                PlaybackSpeedPopUpButton(player)
            }
        }
    }
}

@kotlin.OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun MinimalControls(player: Player, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(24.dp)) {
        SeekBackButton(player)
        PlayPauseButton(player)
        SeekForwardButton(player)
    }
}
@ExperimentalMaterial3ExpressiveApi
@OptIn(UnstableApi::class)
@Composable
internal fun PlayPauseButton(player: Player, modifier: Modifier = Modifier) {
    val state = rememberPlayPauseButtonState(player)
    AnimatedContent(
        state.showPlay
    ) {
        FilledIconToggleButton(
            checked = it,
            shape = if (it) ToggleButtonDefaults.mediumSquareShape else ToggleButtonDefaults.roundShape,
            onCheckedChange = { state.onClick() },
            modifier = modifier.size(IconButtonDefaults.mediumContainerSize(IconButtonDefaults.IconButtonWidthOption.Wide)),
        ) {
            if (it) {
                Icon(painterResource(R.drawable.play_arrow_24px), contentDescription = "", modifier = Modifier.size(IconButtonDefaults.mediumIconSize))
            } else {
                Icon(painterResource(R.drawable.pause_24px), contentDescription = "", modifier = Modifier.size(IconButtonDefaults.mediumIconSize))
            }
        }
    }
}

@ExperimentalMaterial3ExpressiveApi
@OptIn(UnstableApi::class)
@Composable
internal fun PlaybackSpeedPopUpButton(
    player: Player,
    modifier: Modifier = Modifier,
    speedSelection: List<Float> = listOf(0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 1.75f, 2.0f),
) {
    val state = rememberPlaybackSpeedState(player)
    var checked by remember { mutableStateOf(false) }

    Box {
        SplitButtonLayout(
            modifier = modifier,
            leadingButton = {
                SplitButtonDefaults.OutlinedLeadingButton(
                    onClick = { state.updatePlaybackSpeed(1.0f) },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    ),
                    border = BorderStroke(width = 2.dp, color = Color.White)
                ) {
                    Icon(
                        painterResource(R.drawable.speed_24px),
                        modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                        contentDescription = "Localized description",
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("${state.playbackSpeed}x")
                }
            },
            trailingButton = {
                val description = "Toggle Button"
                SplitButtonDefaults.OutlinedTrailingButton(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    ),
                    border = BorderStroke(width = 2.dp, color = Color.White),
                    modifier =
                        Modifier.semantics {
                            stateDescription = if (checked) "Expanded" else "Collapsed"
                            contentDescription = description
                        },
                ) {
                    val rotation: Float by
                    animateFloatAsState(
                        targetValue = if (checked) 180f else 0f,
                        label = "Trailing Icon Rotation",
                    )
                    Icon(
                        painterResource(R.drawable.keyboard_arrow_down_24px),
                        modifier =
                            Modifier.size(SplitButtonDefaults.TrailingIconSize).graphicsLayer {
                                this.rotationZ = rotation
                            },
                        contentDescription = "Localized description",
                    )
                }
            }
        )

        DropdownMenu(expanded = checked, onDismissRequest = { checked = false }) {
            speedSelection.forEach {
                val style = if (it == state.playbackSpeed) {
                    MaterialTheme.typography.labelLargeEmphasized
                } else {
                    MaterialTheme.typography.labelLarge
                }
                DropdownMenuItem(
                    text = { Text("${it}x", style = style) },
                    onClick = {
                        state.updatePlaybackSpeed(it)
                        checked = false
                    }
                )
            }
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
internal fun SeekBackButton(player: Player, modifier: Modifier = Modifier) {
    val state = rememberSeekBackButtonState(player)

    FilledTonalIconButton(
        state::onClick
    ) {
        Icon(
            painterResource(R.drawable.replay_5_24px),
            "",
            modifier = modifier.clickable(enabled = state.isEnabled, onClick = state::onClick),
        )
    }
}

@OptIn(UnstableApi::class)
@Composable
internal fun SeekForwardButton(player: Player, modifier: Modifier = Modifier) {
    val state = rememberSeekForwardButtonState(player)

    FilledTonalIconButton(
        state::onClick
    ) {
        Icon(
            painterResource(R.drawable.forward_10_24px),
            "",
            modifier = modifier.clickable(enabled = state.isEnabled, onClick = state::onClick),
        )
    }
}
@OptIn(UnstableApi::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun ProgressTimeline(player: Player, modifier: Modifier = Modifier, seekTo: (Long) -> Unit) {
    val state = rememberProgressStateWithTickInterval(player)
    WavyTimeline(
        modifier = modifier,
        state = state,
        seekTo = seekTo,
        playedColor = MaterialTheme.colorScheme.primaryFixedDim,
        pause = {
            player.pause()
        },
        play = {
            player.play()
        }
    )
}

@OptIn(UnstableApi::class)
@Composable
fun TimerText(player: Player, modifier: Modifier = Modifier) {
    val state = rememberProgressStateWithTickInterval(player)

    Text(
        text = formatTrackDuration(state.currentPositionMs, state.durationMs),
        style = MaterialTheme.typography.labelLarge,
        color = Color.White
    )

}

fun formatTrackDuration(currentMillis: Long, durationMillis: Long): String {
    val isLongFormat = durationMillis >= 3_600_000L
    fun formatTime(millis: Long): String {
        val seconds = (millis / 1000) % 60
        val minutes = (millis / (1000 * 60)) % 60
        val hours = millis / (1000 * 60 * 60)
        return if (isLongFormat) {
            String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        }
    }
    return "${formatTime(currentMillis)} / ${formatTime(durationMillis)}"
}