package com.xxcactussell.jni

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.graphics.createBitmap
import java.util.ArrayDeque
import java.util.LinkedHashMap

/**
 * Реализует пул для объектов Bitmap ("Жесткое кэширование"), чтобы минимизировать
 * аллокации памяти и нагрузку на сборщик мусора при рендеринге стикеров.
 */
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
            val queue = pool[key] // Accessing makes it recently used
            val pooled = queue?.pollFirst()
            if (pooled != null && !pooled.isRecycled) {
                pooled.eraseColor(Color.TRANSPARENT) // Очищаем Bitmap перед повторным использованием
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