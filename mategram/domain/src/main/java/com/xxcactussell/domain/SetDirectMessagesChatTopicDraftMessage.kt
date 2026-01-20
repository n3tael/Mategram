package com.xxcactussell.domain

data class SetDirectMessagesChatTopicDraftMessage(
    val chatId: Long,
    val topicId: Long,
    val draftMessage: DraftMessage
) : Function
