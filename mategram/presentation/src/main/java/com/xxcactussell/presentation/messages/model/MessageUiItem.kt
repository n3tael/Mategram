package com.xxcactussell.presentation.messages.model

import com.xxcactussell.domain.Message
import com.xxcactussell.domain.MessageAnimatedEmoji
import com.xxcactussell.domain.MessageAnimation
import com.xxcactussell.domain.MessageAudio
import com.xxcactussell.domain.MessageBasicGroupChatCreate
import com.xxcactussell.domain.MessageBotWriteAccessAllowed
import com.xxcactussell.domain.MessageCall
import com.xxcactussell.domain.MessageChatAddMembers
import com.xxcactussell.domain.MessageChatBoost
import com.xxcactussell.domain.MessageChatChangePhoto
import com.xxcactussell.domain.MessageChatChangeTitle
import com.xxcactussell.domain.MessageChatDeleteMember
import com.xxcactussell.domain.MessageChatDeletePhoto
import com.xxcactussell.domain.MessageChatJoinByLink
import com.xxcactussell.domain.MessageChatJoinByRequest
import com.xxcactussell.domain.MessageChatSetBackground
import com.xxcactussell.domain.MessageChatSetMessageAutoDeleteTime
import com.xxcactussell.domain.MessageChatSetTheme
import com.xxcactussell.domain.MessageChatShared
import com.xxcactussell.domain.MessageChatUpgradeFrom
import com.xxcactussell.domain.MessageChatUpgradeTo
import com.xxcactussell.domain.MessageChecklist
import com.xxcactussell.domain.MessageChecklistTasksAdded
import com.xxcactussell.domain.MessageChecklistTasksDone
import com.xxcactussell.domain.MessageContact
import com.xxcactussell.domain.MessageContactRegistered
import com.xxcactussell.domain.MessageContent
import com.xxcactussell.domain.MessageCustomServiceAction
import com.xxcactussell.domain.MessageDice
import com.xxcactussell.domain.MessageDirectMessagePriceChanged
import com.xxcactussell.domain.MessageDocument
import com.xxcactussell.domain.MessageExpiredPhoto
import com.xxcactussell.domain.MessageExpiredVideo
import com.xxcactussell.domain.MessageExpiredVideoNote
import com.xxcactussell.domain.MessageExpiredVoiceNote
import com.xxcactussell.domain.MessageForumTopicCreated
import com.xxcactussell.domain.MessageForumTopicEdited
import com.xxcactussell.domain.MessageForumTopicIsClosedToggled
import com.xxcactussell.domain.MessageForumTopicIsHiddenToggled
import com.xxcactussell.domain.MessageGame
import com.xxcactussell.domain.MessageGameScore
import com.xxcactussell.domain.MessageGift
import com.xxcactussell.domain.MessageGiftedPremium
import com.xxcactussell.domain.MessageGiftedStars
import com.xxcactussell.domain.MessageGiftedTon
import com.xxcactussell.domain.MessageGiveaway
import com.xxcactussell.domain.MessageGiveawayCompleted
import com.xxcactussell.domain.MessageGiveawayCreated
import com.xxcactussell.domain.MessageGiveawayPrizeStars
import com.xxcactussell.domain.MessageGiveawayWinners
import com.xxcactussell.domain.MessageGroupCall
import com.xxcactussell.domain.MessageInviteVideoChatParticipants
import com.xxcactussell.domain.MessageInvoice
import com.xxcactussell.domain.MessageLocation
import com.xxcactussell.domain.MessagePaidMedia
import com.xxcactussell.domain.MessagePaidMessagePriceChanged
import com.xxcactussell.domain.MessagePaidMessagesRefunded
import com.xxcactussell.domain.MessagePassportDataReceived
import com.xxcactussell.domain.MessagePassportDataSent
import com.xxcactussell.domain.MessagePaymentRefunded
import com.xxcactussell.domain.MessagePaymentSuccessful
import com.xxcactussell.domain.MessagePaymentSuccessfulBot
import com.xxcactussell.domain.MessagePhoto
import com.xxcactussell.domain.MessagePinMessage
import com.xxcactussell.domain.MessagePoll
import com.xxcactussell.domain.MessagePremiumGiftCode
import com.xxcactussell.domain.MessageProximityAlertTriggered
import com.xxcactussell.domain.MessageReaction
import com.xxcactussell.domain.MessageRefundedUpgradedGift
import com.xxcactussell.domain.MessageReplyTo
import com.xxcactussell.domain.MessageScreenshotTaken
import com.xxcactussell.domain.MessageSticker
import com.xxcactussell.domain.MessageStory
import com.xxcactussell.domain.MessageSuggestProfilePhoto
import com.xxcactussell.domain.MessageSuggestedPostApprovalFailed
import com.xxcactussell.domain.MessageSuggestedPostApproved
import com.xxcactussell.domain.MessageSuggestedPostDeclined
import com.xxcactussell.domain.MessageSuggestedPostPaid
import com.xxcactussell.domain.MessageSuggestedPostRefunded
import com.xxcactussell.domain.MessageSupergroupChatCreate
import com.xxcactussell.domain.MessageText
import com.xxcactussell.domain.MessageUnsupported
import com.xxcactussell.domain.MessageUpgradedGift
import com.xxcactussell.domain.MessageUsersShared
import com.xxcactussell.domain.MessageVenue
import com.xxcactussell.domain.MessageVideo
import com.xxcactussell.domain.MessageVideoChatEnded
import com.xxcactussell.domain.MessageVideoChatScheduled
import com.xxcactussell.domain.MessageVideoChatStarted
import com.xxcactussell.domain.MessageVideoNote
import com.xxcactussell.domain.MessageVoiceNote
import com.xxcactussell.domain.MessageWebAppDataReceived
import com.xxcactussell.domain.MessageWebAppDataSent
import com.xxcactussell.presentation.chats.model.AvatarUiState
import com.xxcactussell.repositories.messages.utils.getId

sealed interface MessageUiItem {
    val key: Any

    data class DateSeparator(val timestamp: Int) : MessageUiItem {
        override val key: Any = this.getKey()
    }
    data class MessageItem(val message: Message, val profilePhoto: AvatarUiState?) : MessageUiItem {
        override val key: Any = this.getKey()
    }
    data class AlbumItem(val messages: List<MessageItem>) : MessageUiItem {
        override val key: Any = this.getKey()
    }
}

fun MessageUiItem.getKey() : String = when(this) {
    is MessageUiItem.MessageItem -> "message_${message.id}_" + message.editDate
    is MessageUiItem.AlbumItem -> "album_" + messages.sumOf { it.message.id } + "_" + messages.sumOf { it.message.editDate }
    is MessageUiItem.DateSeparator -> "date_separator_$timestamp"
}

fun MessageUiItem.getAlbumId(): Long? = when(this) {
    is MessageUiItem.MessageItem -> this.message.mediaAlbumId.takeIf { it != 0L }
    is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.message?.mediaAlbumId?.takeIf { it != 0L }
    else -> null
}

fun MessageUiItem.isAlbum(): Boolean = this is MessageUiItem.AlbumItem || this is MessageUiItem.MessageItem && this.message.mediaAlbumId != 0L


fun MessageUiItem.getMessage(): Message? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.message
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.message
        is MessageUiItem.DateSeparator -> null
    }
}

fun MessageUiItem.getEditTime(): Int? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.message.editDate
        is MessageUiItem.AlbumItem -> this.messages.maxByOrNull { it.message.editDate }?.message?.editDate
        is MessageUiItem.DateSeparator -> null
    }
}

fun MessageUiItem.getItem(): MessageUiItem? {
    return when (this) {
        is MessageUiItem.MessageItem -> this
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()
        is MessageUiItem.DateSeparator -> null
    }
}


fun MessageUiItem.getMessageId(): Long? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.message.id
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.message?.id
        is MessageUiItem.DateSeparator -> null
    }
}

fun MessageUiItem.getMessageDate(): Int? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.message.date
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.message?.date
        is MessageUiItem.DateSeparator -> this.timestamp
    }
}

fun MessageUiItem.isOutgoing(): Boolean? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.message.isOutgoing
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.message?.isOutgoing
        is MessageUiItem.DateSeparator -> null
    }
}

fun MessageUiItem.getMessageSenderId() : Long? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.message.senderId.getId()
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.message?.senderId?.getId()
        is MessageUiItem.DateSeparator -> null
    }
}

fun MessageUiItem.getReplyTo() : MessageReplyTo? {
    return when (this) {
        is MessageUiItem.MessageItem -> if (this.message.replyTo != null) this.message.replyTo else null
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull { it.message.replyTo != null }?.message?.replyTo
        else -> null
    }
}

fun MessageUiItem.getReactions() : List<MessageReaction> {
    return when (this) {
        is MessageUiItem.AlbumItem -> {
            val reactions = emptyList<MessageReaction>().toMutableList()
            this.messages.forEach {
                reactions.addAll(it.message.interactionInfo?.reactions?.reactions ?: emptyList())
            }
            reactions.toList()
        }
        is MessageUiItem.DateSeparator -> emptyList()
        is MessageUiItem.MessageItem -> this.message.interactionInfo?.reactions?.reactions ?: emptyList()
    }
}

fun MessageUiItem.getAvatar() : AvatarUiState? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.profilePhoto
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.profilePhoto
        is MessageUiItem.DateSeparator -> null
    }
}

fun MessageUiItem.getChatId() : Long {
    return when(this) {
        is MessageUiItem.MessageItem -> this.message.chatId
        is MessageUiItem.AlbumItem -> this.messages.first().message.chatId
        is MessageUiItem.DateSeparator -> 0L
    }
}

fun MessageUiItem.isServiceMessage() : Boolean {
    return when (this) {
        is MessageUiItem.AlbumItem -> false
        is MessageUiItem.DateSeparator -> true
        is MessageUiItem.MessageItem -> {
            when(this.message.content) {
                is MessageAnimation,
                is MessageAnimatedEmoji,
                is MessageAudio,
                is MessageChecklist,
                is MessageContact,
                is MessageDice,
                is MessageDocument,
                MessageExpiredPhoto,
                MessageExpiredVideo,
                MessageExpiredVideoNote,
                MessageExpiredVoiceNote,
                is MessageGame,
                is MessageGameScore,
                is MessageGift,
                is MessageInvoice,
                is MessageLocation,
                is MessagePaidMedia,
                is MessagePhoto,
                is MessagePoll,
                is MessageSticker,
                is MessageStory,
                is MessageText,
                is MessageVenue,
                is MessageVideo,
                is MessageVideoNote,
                is MessageVoiceNote
                    -> false
                else -> true
            }
        }
    }
}