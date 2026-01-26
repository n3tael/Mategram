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
    private val cache = mutableMapOf<String, SharedController>()

    fun acquire(path: String, hash: String, width: Int, height: Int): StickerController? {
        synchronized(cache) {
            cache[hash]?.let {
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

            val controller = StickerController(type, source, width, height)
            cache[hash] = SharedController(controller)
            return controller
        }
    }

    fun release(hash: String) {
        synchronized(cache) {
            val shared = cache[hash] ?: return
            shared.refCount--
            if (shared.refCount <= 0) {
                shared.controller.close()
                cache.remove(hash)
            }
        }
    }

    private class SharedController(val controller: StickerController, var refCount: Int = 1)
}