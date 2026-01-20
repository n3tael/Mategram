package com.xxcactussell.domain

data class EmojiCategory(
    val name: String,
    val icon: Sticker,
    val source: EmojiCategorySource,
    val isGreeting: Boolean
) : Object
