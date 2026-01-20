package com.xxcactussell.repositories.root.repository

import com.xxcactussell.domain.Sticker
import javax.inject.Inject

interface CustomEmojiStickerRepository {
    suspend fun getCustomEmojiSticker(customEmojiId: Long): Sticker?
}

class GetCustomEmojiStickerUseCase @Inject constructor(private val repo: CustomEmojiStickerRepository) {
    suspend operator fun invoke(customEmojiId: Long): Sticker? = repo.getCustomEmojiSticker(customEmojiId)
}