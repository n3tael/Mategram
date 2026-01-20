package com.xxcactussell.data.impl

import com.xxcactussell.data.TdClientManager
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import com.xxcactussell.domain.Sticker
import com.xxcactussell.repositories.root.repository.CustomEmojiStickerRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import org.drinkless.tdlib.TdApi
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class CustomEmojiStickerRepositoryImpl @Inject constructor(
    private val clientManager: TdClientManager
) : CustomEmojiStickerRepository {

    override suspend fun getCustomEmojiSticker(customEmojiId: Long): Sticker?= suspendCancellableCoroutine { continuation ->
        clientManager.send(TdApi.GetCustomEmojiStickers(longArrayOf(customEmojiId))) { result ->
            if (continuation.isActive) {
                when (result) {
                    is TdApi.Stickers -> {
                        val sticker = result.stickers.firstOrNull()
                        if (sticker != null) {
                            continuation.resume(sticker.toDomain())
                        } else {
                            continuation.resume(null)
                        }
                    }
                    is TdApi.Error -> continuation.resumeWithException(Exception("TDLib error: ${result.code} ${result.message}"))
                    else -> continuation.resume(null)
                }
            }
        }
    }
}