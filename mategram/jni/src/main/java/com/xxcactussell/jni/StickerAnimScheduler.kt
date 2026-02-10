package com.xxcactussell.jni

import android.os.Handler
import android.os.Looper
import android.view.Choreographer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.ConcurrentHashMap

object StickerAnimScheduler {
    private val activeStickers = ConcurrentHashMap.newKeySet<StickerController>()
    private val mainHandler = Handler(Looper.getMainLooper())

    private var choreographer: Choreographer? = null

    @Volatile
    private var isRunning = false

    private val decoderScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val frameCallback = object : Choreographer.FrameCallback {
        override fun doFrame(frameTimeNanos: Long) {
            if (activeStickers.isEmpty()) {
                isRunning = false
                return
            }

            val now = System.currentTimeMillis()
            val iterator = activeStickers.iterator()

            while (iterator.hasNext()) {
                val sticker = iterator.next()

                if (sticker.isReleased) {
                    iterator.remove()
                    continue
                }

                sticker.tryDecodeNextFrame(now, decoderScope)

                if (sticker.checkNewFrameAvailable()) {
                    sticker.dispatchFrameReady()
                }
            }

            choreographer?.postFrameCallback(this)
        }
    }

    fun add(sticker: StickerController) {
        if (sticker.isReleased) return
        if (activeStickers.add(sticker)) {
            scheduleStart()
        }
    }

    fun remove(sticker: StickerController) {
        activeStickers.remove(sticker)
    }

    private fun scheduleStart() {
        if (isRunning) return

        if (Looper.myLooper() == Looper.getMainLooper()) {
            startInternal()
        } else {
            mainHandler.post { startInternal() }
        }
    }

    private fun startInternal() {
        if (isRunning) return
        if (activeStickers.isEmpty()) return

        isRunning = true

        if (choreographer == null) {
            choreographer = Choreographer.getInstance()
        }

        choreographer?.removeFrameCallback(frameCallback)
        choreographer?.postFrameCallback(frameCallback)
    }
}