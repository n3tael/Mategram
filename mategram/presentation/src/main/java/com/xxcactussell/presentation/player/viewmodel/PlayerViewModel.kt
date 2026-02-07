package com.xxcactussell.presentation.player.viewmodel

import androidx.lifecycle.ViewModel
import androidx.media3.common.Player
import com.xxcactussell.player.PlaybackManager
import com.xxcactussell.player.PlayerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GlobalPlayerViewModel @Inject constructor(
    private val playbackManager: PlaybackManager
) : ViewModel() {
    val playerState: StateFlow<PlayerState> = playbackManager.playerState

    fun togglePlayPause() {
        if (playerState.value.isPlaying) {
            playbackManager.pause()
        } else {
            playbackManager.resume()
        }
    }

    fun switchPlayer(id: Long, switch: Boolean) {
        playbackManager.fromChatToFloatingSwitcher(id, switch)
    }

    fun closePlayer() {
        playbackManager.stop()
    }

    fun getExoPlayerInstance(): Player? {
        return playbackManager.getPlayerInstance()
    }
}