package com.xxcactussell.domain

data class EmojiReaction(
    val emoji: String,
    val title: String,
    val isActive: Boolean,
    val staticIcon: Sticker,
    val appearAnimation: Sticker,
    val selectAnimation: Sticker,
    val activateAnimation: Sticker,
    val effectAnimation: Sticker,
    val aroundAnimation: Sticker? = null,
    val centerAnimation: Sticker? = null
) : Object
