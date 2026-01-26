package com.xxcactussell.jni

import android.view.Choreographer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

enum class VisibilityState {
    HIDDEN,
    PREPARING,
    VISIBLE
}

object StickerAnimScheduler {
    private val activeStickers = mutableMapOf<StickerController, VisibilityState>()
    private val choreographer = Choreographer.getInstance()
    private var isRunning = false

    private val decoderScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val frameCallback = object : Choreographer.FrameCallback {
        override fun doFrame(frameTimeNanos: Long) {
            val now = System.currentTimeMillis()

            val iterator = activeStickers.entries.iterator()
            while (iterator.hasNext()) {
                val entry = iterator.next()
                val sticker = entry.key
                val state = entry.value

                if (sticker.isReleased) {
                    iterator.remove()
                    continue
                }

                val snapshot = synchronized(activeStickers) { activeStickers.entries.toList() }

                val sortedStickers = snapshot.sortedByDescending { it.value == VisibilityState.VISIBLE }

                sortedStickers.forEach { (sticker, state) ->
                    if (state == VisibilityState.VISIBLE) {
                        sticker.tryDecodeNextFrame(now, decoderScope)
                    } else if (state == VisibilityState.PREPARING && now % 32 < 8) {
                        sticker.tryDecodeNextFrame(now, decoderScope)
                    }
                }

                if (state == VisibilityState.VISIBLE && sticker.checkNewFrameAvailable()) {
                    sticker.onFrameReady?.invoke()
                }
            }

            if (activeStickers.isNotEmpty()) choreographer.postFrameCallback(this)
            else isRunning = false
        }
    }

    fun updateState(sticker: StickerController, state: VisibilityState) {
        synchronized(activeStickers) {
            activeStickers[sticker] = state
            if (!isRunning && activeStickers.isNotEmpty()) {
                isRunning = true
                choreographer.postFrameCallback(frameCallback)
            }
        }
    }

    fun add(sticker: StickerController, initialState: VisibilityState = VisibilityState.PREPARING) {
        synchronized(activeStickers) {
            if (!activeStickers.containsKey(sticker)) {
                activeStickers[sticker] = initialState
                if (!isRunning) {
                    isRunning = true
                    choreographer.postFrameCallback(frameCallback)
                }
            }
        }
    }

    fun remove(sticker: StickerController) {
        synchronized(activeStickers) { activeStickers.remove(sticker) }
    }
}