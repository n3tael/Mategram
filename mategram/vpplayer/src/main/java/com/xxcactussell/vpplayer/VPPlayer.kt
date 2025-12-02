package com.xxcactussell.vpplayer

import android.graphics.Bitmap

object VPPlayer {
    init { System.loadLibrary("vpplayer") }
    external fun nativeCreate(path: String): Long
    external fun nativeRenderNextFrame(handle: Long, bitmap: Bitmap): Long
    external fun nativeSeekToStart(handle: Long)
    external fun nativeDestroy(handle: Long)
    external fun nativeGetVideoSize(handle: Long, outSize: IntArray)
}