package com.xxcactussell.presentation.player.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xxcactussell.presentation.chats.model.AvatarUiState
import com.xxcactussell.presentation.chats.screen.ChatAvatar
import com.xxcactussell.presentation.localization.localizedString
import com.xxcactussell.presentation.messages.screen.CircleVideoSurface
import com.xxcactussell.presentation.player.viewmodel.GlobalPlayerViewModel

@Composable
fun FloatingPlayerWidget(
    viewModel: GlobalPlayerViewModel,
    onWidgetClick: (Long, Long?) -> Unit,
    modifier: Modifier = Modifier,
    onSizeChanged: (Dp) -> Unit,
    isOnLeftSide: Boolean,
) {
    val state by viewModel.playerState.collectAsState()
    val playerInstance = viewModel.getExoPlayerInstance()
    var isExpandedPlayer by remember { mutableStateOf(false) }

    localizedString("AttachRound")
    localizedString("AttachAudio")

    val isVideo = playerInstance?.currentMediaItem?.mediaMetadata?.extras?.getBoolean("IS_VIDEO") == true

    val isPlaying = state.isPlaying
    val isPaused = state.isPaused

    val playbackProgress = if (state.duration > 0) {
        state.progress.toFloat() / state.duration.toFloat()
    } else {
        0f
    }
    val playbackProgressMs = if (state.duration > 0) {
        state.progress
    } else {
        0
    }
    val currentMediaId = try {
        state.currentMediaId?.toLong()?: 0L
    } catch (_ : Exception) {
        0L
    }

    val avatarSize = if (isExpandedPlayer) 40.dp else if (isVideo && state.onScreenVideoNotesId[currentMediaId] != true) 160.dp else 64.dp
    val xTimerOffset = if (isExpandedPlayer) avatarSize + 6.dp else avatarSize - 20.dp
    val yTimerOffset = if (isExpandedPlayer) 0.dp else avatarSize - 18.dp

    val animatedXOffset = animateDpAsState(xTimerOffset)
    val animatedYOffset = animateDpAsState(yTimerOffset)
    val animatedAvatarSize = animateDpAsState(avatarSize)

    val density = LocalDensity.current
    val playerInstanceForSurface = if (isVideo && state.onScreenVideoNotesId[currentMediaId] != true) playerInstance else null

    Box(
        modifier = modifier
            .wrapContentSize()
            .onSizeChanged { onSizeChanged(with(density) { it.width.toDp() }) }
    ) {
        Box(
            modifier = Modifier
        ) {
            if (playerInstanceForSurface != null) {
                CircleVideoSurface(
                    player = playerInstanceForSurface,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(animatedAvatarSize.value)
                        .clickable {
                            isExpandedPlayer = !isExpandedPlayer
                        }
                )
            } else {
                ChatAvatar(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(animatedAvatarSize.value)
                        .clickable {
                            isExpandedPlayer = !isExpandedPlayer
                        },
                    state = AvatarUiState(
                        0L,
                        state.currentPlayableMedia?.avatar,
                        state.currentPlayableMedia?.artist ?: "Unknown artist"
                    ),
                    isPinned = false,
                    isOnline = false
                )
            }
            FloatingPlayerTimer(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(animatedXOffset.value, animatedYOffset.value),
                currentTime = state.duration.toInt() - playbackProgressMs.toInt(),
                waveform = state.waveform ?: byteArrayOf(1, 1, 1, 1),
                isPlaying = isPlaying,
                isExpanded = isExpandedPlayer,
                playbackProgress = playbackProgress
            ) {
                viewModel.togglePlayPause()
            }
            FloatingPlayerButtons(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(46.dp, 44.dp),
                isExpanded = isExpandedPlayer,
                onCloseClick = {
                    viewModel.closePlayer()
                },
                onGoToMessageClick = {
                    val messageId = try {
                        playerInstance?.currentMediaItem?.mediaId?.toLong()
                    } catch (_: Exception) {
                        null
                    }
                    onWidgetClick(
                        playerInstance?.mediaMetadata?.extras?.getLong(
                            "CHAT_ID"
                        ) ?: 0L, messageId
                    )
                }
            )
        }
    }
}