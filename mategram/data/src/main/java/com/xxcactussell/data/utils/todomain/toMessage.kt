package com.xxcactussell.data.utils

import com.xxcactussell.data.utils.todomain.toDomain
import com.xxcactussell.domain.messages.model.ForwardSource
import com.xxcactussell.domain.messages.model.Message
import com.xxcactussell.domain.messages.model.MessageForwardInfo
import com.xxcactussell.domain.messages.model.MessageImportInfo
import com.xxcactussell.domain.messages.model.MessageInteractionInfo
import com.xxcactussell.domain.messages.model.MessageOrigin
import com.xxcactussell.domain.messages.model.MessageReaction
import com.xxcactussell.domain.messages.model.MessageReactions
import com.xxcactussell.domain.messages.model.MessageReplyInfo
import com.xxcactussell.domain.messages.model.MessageReplyTo
import com.xxcactussell.domain.messages.model.PaidReactor
import com.xxcactussell.domain.messages.model.ReactionType
import com.xxcactussell.domain.messages.model.TextQuote
import org.drinkless.tdlib.TdApi

fun TdApi.Message.toDomain() : Message {
    return Message(
        id,
        chatId,
        senderId.toDomain(),
        sendingState?.toDomain(),
        isOutgoing,
        isPinned,
        content.toDomain(),
        date,
        editDate,
        authorSignature,
        mediaAlbumId,
        containsUnreadMention,
        isChannelPost,
        canBeSaved,
        messageThreadId,
        selfDestructIn,
        autoDeleteIn,
        forwardInfo?.toDomain(),
        importInfo?.toDomain(),
        interactionInfo?.toDomain(),
        replyTo?.toDomain()
    )
}

fun TdApi.MessageForwardInfo.toDomain(): MessageForwardInfo {
    return MessageForwardInfo(
        origin = this.origin.toDomain(),
        date = this.date,
        source = source?.toDomain()
    )
}

fun TdApi.ForwardSource.toDomain() : ForwardSource {
    return ForwardSource(
        chatId,
        messageId,
        senderId?.toDomain(),
        senderName,
        date,
        isOutgoing
    )
}

fun TdApi.MessageOrigin.toDomain(): MessageOrigin {
    return when (this) {
        is TdApi.MessageOriginUser -> MessageOrigin.User(this.senderUserId)
        is TdApi.MessageOriginChat -> MessageOrigin.Chat(this.senderChatId)
        is TdApi.MessageOriginHiddenUser -> MessageOrigin.HiddenUser(this.senderName)
        is TdApi.MessageOriginChannel -> MessageOrigin.Channel(this.chatId, this.messageId)
        else -> MessageOrigin.HiddenUser("Unknown")
    }
}

fun TdApi.MessageImportInfo.toDomain(): MessageImportInfo {
    return MessageImportInfo(
        senderName = this.senderName,
        date = this.date
    )
}

fun TdApi.MessageInteractionInfo.toDomain(): MessageInteractionInfo {
    return MessageInteractionInfo(
        viewCount = this.viewCount,
        forwardCount = this.forwardCount,
        replyInfo = this.replyInfo?.toDomain(),
        reactions = this.reactions?.toDomain()
    )
}

fun TdApi.MessageReplyInfo.toDomain(): MessageReplyInfo {
    return MessageReplyInfo(
        replyCount = this.replyCount,
        recentRepliersIds = this.recentReplierIds.map { it.toDomain() },
        lastReadInboxMessageId = this.lastReadInboxMessageId,
        lastReadOutboxMessageId = this.lastReadOutboxMessageId,
        lastMessageId = this.lastMessageId
    )
}

fun TdApi.MessageReactions.toDomain(): MessageReactions {
    return MessageReactions(
        reactions = this.reactions.map { it.toDomain() },
        areTags = this.areTags,
        paidReactors = this.paidReactors.map { it.toDomain() },
        canGetAddedReactions = this.canGetAddedReactions
    )
}

fun TdApi.MessageReaction.toDomain(): MessageReaction {
    return MessageReaction(
        type = this.type.toDomain(),
        totalCount = this.totalCount,
        isChosen = this.isChosen,
        userSenderId = this.usedSenderId?.toDomain(),
        recentSenderIds = this.recentSenderIds.map { it.toDomain() }
    )
}

fun TdApi.ReactionType.toDomain(): ReactionType {
    return when (this) {
        is TdApi.ReactionTypeEmoji -> ReactionType.Emoji(this.emoji)
        is TdApi.ReactionTypeCustomEmoji -> ReactionType.Custom(this.customEmojiId)
        is TdApi.ReactionTypePaid -> ReactionType.Paid
        else -> ReactionType.Emoji("❓")
    }
}

fun TdApi.PaidReactor.toDomain(): PaidReactor {
    return PaidReactor(
        senderId = this.senderId?.toDomain(),
        starCount = this.starCount,
        isTop = this.isTop,
        isMe = this.isMe,
        isAnonymous = this.isAnonymous
    )
}

fun TdApi.MessageReplyTo.toDomain(): MessageReplyTo? {
    return when (this) {
        is TdApi.MessageReplyToMessage -> MessageReplyTo.Message(
            chatId = this.chatId,
            messageId = this.messageId,
            quote = this.quote?.toDomain(),
            checkListTaskId = checklistTaskId,
            origin = this.origin?.toDomain(),
            originSentDate = this.originSendDate,
            content = this.content?.toDomain()
        )
        is TdApi.MessageReplyToStory -> MessageReplyTo.Story(
            storyId = this.storyId,
            storyPosterChatId = this.storyPosterChatId
        )
        else -> null
    }
}

fun TdApi.TextQuote.toDomain(): TextQuote {
    return TextQuote(
        text = this.text.toDomain(),
        position = this.position,
        isManual = this.isManual
    )
}