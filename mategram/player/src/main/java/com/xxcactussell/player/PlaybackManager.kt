package com.xxcactussell.player

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.xxcactussell.player.playablemedia.PlayableMedia
import com.xxcactussell.player.playablemedia.toMediaItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

data class PlayerState(
    val currentMediaId: String? = null,
    val currentPlayableMedia: PlayableMedia? = null,
    val nextPlayableMedia: PlayableMedia? = null,
    val isPlaying: Boolean = false,
    val isPaused: Boolean = false,
    val onScreenVideoNotesId: Map<Long, Boolean> = emptyMap(),
    val buffering: Boolean = false,
    val waveform: ByteArray? = null,
    val duration: Long = 0L,
    val progress: Long = 0L
)

@Singleton
class PlaybackManager @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val mediaRepository: MediaRepository
) : Player.Listener {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private var controllerFuture: ListenableFuture<MediaController>? = null

    private val controller: MediaController?
        get() = if (controllerFuture?.isDone == true) controllerFuture?.get() else null

    private val _playerState = MutableStateFlow(PlayerState())
    val playerState: StateFlow<PlayerState> = _playerState.asStateFlow()

    init {
        initializeController()
    }

    private fun initializeController() {
        val sessionToken = SessionToken(
            context,
            ComponentName(context, MategramMediaService::class.java)
        )

        controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()

        controllerFuture?.addListener({
            try {
                val mediaController = controllerFuture?.get()
                mediaController?.addListener(this)
                syncPlayerState()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, MoreExecutors.directExecutor())
    }

    private var progressJob: Job? = null

    private fun startProgressUpdate() {
        stopProgressUpdate()
        progressJob = scope.launch {
            while (isActive) {
                val player = controller ?: break
                if (player.isPlaying) {
                    updateState {
                        it.copy(progress = player.currentPosition)
                    }
                }
                delay(10)
            }
        }
    }

    private fun stopProgressUpdate() {
        progressJob?.cancel()
        progressJob = null
    }

    fun pause() {
        controller?.pause()
        updateState {
            it.copy(isPaused = true)
        }
    }

    fun resume() {
        controller?.play()
        updateState {
            it.copy(isPaused = false)
        }
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        updateState { it.copy(isPlaying = isPlaying) }
        if (isPlaying) {
            startProgressUpdate()
        } else {
            stopProgressUpdate()
        }
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        if (playbackState == Player.STATE_ENDED) {
            stop()
            return
        }

        val isBuffering = playbackState == Player.STATE_BUFFERING

        val duration = if (playbackState == Player.STATE_READY) {
            controller?.duration?.coerceAtLeast(0L) ?: 0L
        } else {
            _playerState.value.duration
        }

        updateState {
            it.copy(
                buffering = isBuffering,
                duration = duration
            )
        }
    }

    private fun syncPlayerState() {
        val mediaController = controller ?: return
        updateState {
            it.copy(
                currentMediaId = mediaController.currentMediaItem?.mediaId,
                isPlaying = mediaController.isPlaying,
                duration = mediaController.duration.coerceAtLeast(0)
            )
        }
    }

    private inline fun updateState(crossinline function: (PlayerState) -> PlayerState) {
        _playerState.update { currentState ->
            function(currentState)
        }
    }

    fun getPlayerInstance(): Player? = controller

    fun release() {
        controllerFuture?.let { MediaController.releaseFuture(it) }
    }

    fun playMedia(messageId: Long, chatId: Long) {
        scope.launch {
            val currentMedia = mediaRepository.getMediaById(chatId, messageId) ?: return@launch

            val controller = controller ?: return@launch

            val currentItem = currentMedia.toMediaItem()
            val waveform = currentMedia.toMediaItem().mediaMetadata.extras?.getByteArray("WAVEFORM")

            updateState {
                it.copy(
                    currentPlayableMedia = currentMedia,
                    currentMediaId = currentMedia.toMediaItem().mediaId,
                    progress = 0,
                    waveform = waveform
                )
            }

            controller.setMediaItem(currentItem)
            controller.prepare()
            controller.play()
        }
    }

    private suspend fun enqueueNext(chatId: Long, currentMsgId: Long) {
        mediaRepository.markVoiceVideoAsViewed(chatId, currentMsgId)

        var nextMedia = mediaRepository.getNextMedia(chatId, currentMsgId) ?: return
        val playlist = getCurrentQueue()
        if (playlist.any { it.mediaId == nextMedia.toMediaItem().mediaId }) return

        val downloadedPath = mediaRepository.downloadMedia(nextMedia.fileId)

        nextMedia = when(nextMedia) {
            is PlayableMedia.Video -> nextMedia.copy(url = downloadedPath)
            is PlayableMedia.Voice -> nextMedia.copy(url = downloadedPath)
        }

        if (nextMedia.url.isNotEmpty()) {
            _playerState.update { it.copy(nextPlayableMedia = nextMedia) }
            val nextItem = nextMedia.toMediaItem()
            controller?.addMediaItem(nextItem)
        }
    }

    fun getCurrentQueue(): List<MediaItem> {
        val player = controller ?: return emptyList()

        val queue = ArrayList<MediaItem>()
        for (i in 0 until player.mediaItemCount) {
            queue.add(player.getMediaItemAt(i))
        }
        return queue
    }

    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
        super.onMediaItemTransition(mediaItem, reason)

        if (_playerState.value.nextPlayableMedia != null) {
            updateState {
                it.copy(
                    currentPlayableMedia = it.nextPlayableMedia
                )
            }
        }

        mediaItem?.mediaId?.toLongOrNull()?.let { newMsgId ->
            val chatId = mediaItem.mediaMetadata.extras?.getLong("CHAT_ID") ?: return@let

            scope.launch {
                enqueueNext(chatId, newMsgId)
            }
        }

        val waveform = mediaItem?.mediaMetadata?.extras?.getByteArray("WAVEFORM")
        updateState {
            it.copy(
                currentMediaId = mediaItem?.mediaId,
                duration = controller?.duration ?: 0L,
                progress = 0,
                waveform = waveform
            )
        }
    }

    fun stop() {
        controller?.stop()
        controller?.clearMediaItems()
        stopProgressUpdate()
        updateState {
            it.copy(
                currentMediaId = null,
                isPlaying = false,
                progress = 0,
                duration = 0
            )
        }
    }

    fun fromChatToFloatingSwitcher(id: Long, switcher: Boolean) {
        _playerState.update { currentState ->
            val newMap = currentState.onScreenVideoNotesId.toMutableMap().apply {
                if (switcher) put(id, true) else remove(id)
            }
            currentState.copy(onScreenVideoNotesId = newMap)
        }
    }
}