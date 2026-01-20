package com.xxcactussell.domain

data class ToggleGeneralForumTopicIsHidden(
    val chatId: Long,
    val isHidden: Boolean
) : Function
