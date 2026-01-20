package com.xxcactussell.domain

data class ChatEventMessageAutoDeleteTimeChanged(
    val oldMessageAutoDeleteTime: Int,
    val newMessageAutoDeleteTime: Int
) : ChatEventAction
