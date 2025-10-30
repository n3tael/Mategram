package com.xxcactussell.data.utils.todomain

import com.xxcactussell.data.utils.toDomain
import com.xxcactussell.domain.chats.model.Chat
import com.xxcactussell.domain.chats.model.ChatPhoto
import com.xxcactussell.domain.chats.model.ChatType
import com.xxcactussell.domain.messages.model.MessageSender
import org.drinkless.tdlib.TdApi


fun TdApi.Chat.toDomain(): Chat {
    val lastMessage = lastMessage?.toDomain()
    val permissions = permissions.toDomain()
    val positions = positions.map{ it.toDomain() }
    val chatList = chatLists.map { it.toDomain() }
    val chat = Chat(
        id,
        when (type) {
            is TdApi.ChatTypePrivate -> ChatType.Private((type as TdApi.ChatTypePrivate).userId)
            is TdApi.ChatTypeBasicGroup -> ChatType.BasicGroup((type as TdApi.ChatTypeBasicGroup).basicGroupId)
            is TdApi.ChatTypeSupergroup -> ChatType.Supergroup(
                (type as TdApi.ChatTypeSupergroup).supergroupId,
                (type as TdApi.ChatTypeSupergroup).isChannel
            )

            is TdApi.ChatTypeSecret -> ChatType.Secret(
                (type as TdApi.ChatTypeSecret).secretChatId,
                (type as TdApi.ChatTypeSecret).userId
            )

            else -> ChatType.Private(0L)
        },
        title,
        ChatPhoto(
            photo?.small?.toDomain(),
            photo?.big?.toDomain(),
            photo?.hasAnimation ?: false,
            photo?.isPersonal ?: false
        ),
        accentColorId,
        backgroundCustomEmojiId,
        profileAccentColorId,
        profileBackgroundCustomEmojiId,
        permissions,
        lastMessage,
        positions,
        chatList,
        when(messageSenderId) {
            is TdApi.MessageSenderChat -> MessageSender.Chat((messageSenderId as TdApi.MessageSenderChat).chatId)
            is TdApi.MessageSenderUser -> MessageSender.User((messageSenderId as TdApi.MessageSenderUser).userId)
            else -> MessageSender.User(0L)
        },
        hasProtectedContent,
        unreadCount,
        unreadMentionCount,
        unreadReactionCount,
        isTranslatable,
        isMarkedAsUnread,
        viewAsTopics,
        hasScheduledMessages,
        defaultDisableNotification,
        canBeDeletedOnlyForSelf,
        canBeDeletedForAllUsers,
        lastReadInboxMessageId,
        lastReadOutboxMessageId,
        messageAutoDeleteTime,
        replyMarkupMessageId,
        clientData
    )
    return chat
}