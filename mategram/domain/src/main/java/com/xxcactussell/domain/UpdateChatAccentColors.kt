package com.xxcactussell.domain

data class UpdateChatAccentColors(
    val chatId: Long,
    val accentColorId: Int,
    val backgroundCustomEmojiId: Long,
    val profileAccentColorId: Int,
    val profileBackgroundCustomEmojiId: Long
) : Update
