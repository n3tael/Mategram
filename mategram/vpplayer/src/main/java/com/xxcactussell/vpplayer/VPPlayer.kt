package com.xxcactussell.vpplayer

import android.view.Surface

class VPPlayer(videoPath: String, surface: Surface) {
    companion object {
        init {
            System.loadLibrary("vpplayer")
        }
    }

    private var nativeHandle: Long

    init {
        nativeHandle = nativeCreate(videoPath, surface)
        if (nativeHandle == 0L) {
            throw IllegalStateException("Failed to create native Vp9Player for path: $videoPath")
        }
    }

    fun start() {
        if (nativeHandle != 0L) {
            nativeStart(nativeHandle)
        }
    }

    fun stop() {
        if (nativeHandle != 0L) {
            nativeStop(nativeHandle)
        }
    }

    fun destroy() {
        if (nativeHandle != 0L) {
            nativeDestroy(nativeHandle)
            nativeHandle = 0L
        }
    }

    private external fun nativeCreate(videoPath: String, surface: Surface): Long
    private external fun nativeStart(nativeHandle: Long)
    private external fun nativeStop(nativeHandle: Long)
    private external fun nativeDestroy(nativeHandle: Long)
}