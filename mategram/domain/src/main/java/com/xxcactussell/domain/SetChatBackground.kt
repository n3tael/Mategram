package com.xxcactussell.domain

data class SetChatBackground(
    val chatId: Long,
    val background: InputBackground,
    val type: BackgroundType,
    val darkThemeDimming: Int,
    val onlyForSelf: Boolean
) : Function
