package com.xxcactussell.repositories.chats.model

sealed interface ChatStatus {
    object Empty : ChatStatus
    object Online: ChatStatus
    data class Offline(val wasOnline : Int) : ChatStatus
    object Recently : ChatStatus
    object LastWeek : ChatStatus
    object LastMonth : ChatStatus
}