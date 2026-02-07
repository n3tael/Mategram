package com.xxcactussell.presentation.messages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xxcactussell.domain.MessageVoiceNote
import com.xxcactussell.mategram.presentation.R
import com.xxcactussell.player.PlayerState
import com.xxcactussell.presentation.LocalRootViewModel
import com.xxcactussell.presentation.messages.model.MessageUiItem
import com.xxcactussell.presentation.messages.model.MessagesEvent
import com.xxcactussell.presentation.messages.model.getChatId
import com.xxcactussell.presentation.tools.AudioWaveform
import com.xxcactussell.presentation.tools.ColumnWidthOf
import com.xxcactussell.utils.formatFileSize
import com.xxcactussell.utils.toTimeFormat

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun VoiceMessage(
    message: MessageUiItem.MessageItem,
    playerState: PlayerState,
    onEvent: (Any) -> Unit
) {
    val content = message.message.content as MessageVoiceNote
    val voice = content.voiceNote

    val rootViewModel = LocalRootViewModel.current
    val fileUpdates = rootViewModel.files.collectAsState()
    val file = fileUpdates.value[voice.voice.id] ?: voice.voice

    val isDownloaded = file.local.isDownloadingCompleted
    val isDownloading = file.local.isDownloadingActive
    val downloadProgress: Float = file.local.downloadedSize.toFloat() / if (file.size != 0L) file.size else 1

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

    ColumnWidthOf(
        rulerId = "voice",
        horizontalSpacers = 0.dp
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .width(260.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilledIconToggleButton(
                checked = isPlaying,
                shapes = IconButtonDefaults.toggleableShapes(),
                onCheckedChange = {
                    if (!isDownloaded && !isDownloading) {
                        rootViewModel.downloadFile(voice.voice.id)
                    } else if (isDownloaded) {
                        if (isPlaying) {
                            onEvent(MessagesEvent.PauseMedia(message.message.id))
                        } else {
                            onEvent(
                                MessagesEvent.PlayVoice(
                                    message.message.id,
                                    message.getChatId()
                                )
                            )
                        }
                    }
                }
            ) {
                when {
                    isDownloading -> CircularWavyProgressIndicator(progress = { downloadProgress }, modifier = Modifier.size(24.dp))
                    isPlaying -> Icon(painterResource(R.drawable.pause_24px), "Pause")
                    !isDownloaded -> Icon(painterResource(R.drawable.download_24px), "Download")
                    else -> Icon(painterResource(R.drawable.play_arrow_24px), "Play")
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            AudioWaveform(
                waveform = voice.waveform,
                progress = if (isPlaying || isPaused) playbackProgress else 0f,
                modifier = Modifier.height(24.dp).weight(1f)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = if (isPlaying || isPaused) (voice.duration*1000 - playbackProgressMs.toInt()).toTimeFormat() else (voice.duration*1000).toTimeFormat(),
                style = MaterialTheme.typography.labelMediumEmphasized
            )
        }
    }
}