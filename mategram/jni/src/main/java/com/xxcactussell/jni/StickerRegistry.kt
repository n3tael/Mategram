package com.xxcactussell.jni

import java.io.File
import java.util.zip.GZIPInputStream

private fun decompressGzipIfNeeded(data: ByteArray): ByteArray {
    return if (data.size >= 2 && data[0] == 0x1f.toByte() && data[1] == 0x8b.toByte()) {
        try {
            data.inputStream().use { input ->
                GZIPInputStream(input).use { gzip ->
                    gzip.readBytes()
                }
            }
        } catch (e: Exception) { data }
    } else data
}

object StickerRegistry {
    private data class CacheKey(
        val path: String,
        val width: Int,
        val height: Int,
        val color: Int
    )

    private val cache = mutableMapOf<CacheKey, SharedController>()

    fun acquire(path: String, hash: String, width: Int, height: Int, tintColorInt: Int): StickerController {
        val key = CacheKey(path, width, height, tintColorInt)

        synchronized(cache) {
            cache[key]?.let {
                it.refCount++
                return it.controller
            }

            val type = when {
                path.endsWith(".tgs") -> StickerController.StickerType.LOTTIE
                path.endsWith(".webp") -> StickerController.StickerType.WEBP
                else -> StickerController.StickerType.VP9
            }

            val source = if (type == StickerController.StickerType.LOTTIE) {
                val bytes = File(path).readBytes()
                decompressGzipIfNeeded(bytes).toString(Charsets.UTF_8)
            } else {
                File(path).readBytes()
            }

            val controller = StickerController(type, source, width, height, sourceHash = hash, color = tintColorInt)

            cache[key] = SharedController(controller)
            return controller
        }
    }

    fun release(path: String, width: Int, height: Int, color: Int) {
        val key = CacheKey(path, width, height, color)

        synchronized(cache) {
            val shared = cache[key] ?: return
            shared.refCount--
            if (shared.refCount <= 0) {
                shared.controller.close()
                cache.remove(key)
            }
        }
    }

    private class SharedController(val controller: StickerController, var refCount: Int = 1)
}