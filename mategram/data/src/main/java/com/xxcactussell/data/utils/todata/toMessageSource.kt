package com.xxcactussell.data.utils.todata

import com.xxcactussell.domain.messages.model.MessageSource
import org.drinkless.tdlib.TdApi

fun MessageSource.toData(): TdApi.MessageSource {
    return when (this) {
        MessageSource.ChatHistory -> TdApi.MessageSourceChatHistory()
        MessageSource.ThreadHistory -> TdApi.MessageSourceMessageThreadHistory()
        MessageSource.ForumTopicHistory -> TdApi.MessageSourceForumTopicHistory()
        MessageSource.DirectMessagesChatTopicHistory -> TdApi.MessageSourceDirectMessagesChatTopicHistory()
        MessageSource.HistoryPreview -> TdApi.MessageSourceHistoryPreview()
        MessageSource.ChatList -> TdApi.MessageSourceChatList()
        MessageSource.Search -> TdApi.MessageSourceSearch()
        MessageSource.ChatEventLog -> TdApi.MessageSourceChatEventLog()
        MessageSource.Notification -> TdApi.MessageSourceNotification()
        MessageSource.Screenshot -> TdApi.MessageSourceScreenshot()
        else -> TdApi.MessageSourceOther()
    }
}