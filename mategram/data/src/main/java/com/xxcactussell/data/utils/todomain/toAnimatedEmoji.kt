package com.xxcactussell.data.utils.todomain

import com.xxcactussell.data.utils.toDomain
import com.xxcactussell.domain.messages.model.AnimatedEmoji
import org.drinkless.tdlib.TdApi

fun TdApi.AnimatedEmoji.toDomain() : AnimatedEmoji {
    return AnimatedEmoji(
        sticker = sticker?.toDomain(),
        width = stickerWidth,
        height = stickerHeight,
        fitzpatrick = fitzpatrickType,
        sound = sound?.toDomain()
    )
}