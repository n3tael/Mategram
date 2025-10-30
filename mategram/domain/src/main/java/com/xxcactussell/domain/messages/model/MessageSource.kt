package com.xxcactussell.domain.messages.model

interface MessageSource {
    object ChatHistory : MessageSource
    object ThreadHistory : MessageSource
    object ForumTopicHistory : MessageSource
    object DirectMessagesChatTopicHistory : MessageSource
    object HistoryPreview : MessageSource
    object ChatList : MessageSource
    object Search : MessageSource
    object ChatEventLog : MessageSource
    object Notification : MessageSource
    object Screenshot : MessageSource
    object Other : MessageSource
}