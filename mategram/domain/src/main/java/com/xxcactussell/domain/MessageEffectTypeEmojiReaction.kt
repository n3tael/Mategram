package com.xxcactussell.domain

data class MessageEffectTypeEmojiReaction(
    val selectAnimation: Sticker,
    val effectAnimation: Sticker
) : MessageEffectType
