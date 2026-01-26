package com.xxcactussell.jni

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.graphics.createBitmap
import java.util.ArrayDeque
import java.util.LinkedHashMap

object BitmapProvider {
    private const val MAX_BITMAPS_PER_SIZE = 5
    private const val MAX_CACHED_SIZES = 10
    // Lru-like cache for different bitmap sizes
    private val pool = object : LinkedHashMap<String, ArrayDeque<Bitmap>>(MAX_CACHED_SIZES, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, ArrayDeque<Bitmap>>?): Boolean {
            // Remove the least recently used size category if we exceed the number of categories to cache
            return size > MAX_CACHED_SIZES
        }
    }
    private val lock = Any()

    private fun getKey(width: Int, height: Int, config: Bitmap.Config?) = "$width:$height:${config?.name}"

    fun acquire(width: Int, height: Int, config: Bitmap.Config): Bitmap {
        synchronized(lock) {
            val key = getKey(width, height, config)
            val pooled = pool[key]?.pollFirst()
            if (pooled != null && !pooled.isRecycled) {
                pooled.eraseColor(0)
                return pooled
            }
        }
        return createBitmap(width, height, config)
    }

    fun release(bitmap: Bitmap) {
        if (bitmap.isRecycled) return

        synchronized(lock) {
            val key = getKey(bitmap.width, bitmap.height, bitmap.config)
            val queue = pool.getOrPut(key) { ArrayDeque(MAX_BITMAPS_PER_SIZE) }

            if (queue.size < MAX_BITMAPS_PER_SIZE) {
                queue.addLast(bitmap)
            } else {
                bitmap.recycle()
            }
        }
    }
}