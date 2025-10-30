package com.xxcactussell.domain.chats.model

import com.xxcactussell.domain.files.model.File
import com.xxcactussell.domain.messages.model.FormattedText
import com.xxcactussell.domain.messages.model.Message
import com.xxcactussell.domain.messages.model.MessageSender

sealed interface ChatType {
    data class Private(
        val id: Long
    ) : ChatType
    data class BasicGroup(
        val id: Long
    ) : ChatType
    data class Supergroup(
        val id: Long,
        val isChannel: Boolean
    ) : ChatType
    data class Secret(
        val chatId: Int,
        val id: Long
    ) : ChatType
}

sealed interface ChatList {
    data class Main(val id: Int = -1) : ChatList
    data class Folder(val id: Int) : ChatList
    data class Archive(val id: Int = -2) : ChatList
}

data class ChatFolderName(
    val text: FormattedText,
    val animateCustomEmoji: Boolean
)

data class ChatFolder(
    val name: ChatFolderName? = null,
    val unreadCount: Int = 0,
    val id: Int
)

data class ChatPosition(
    val list: ChatList,
    val order: Long,
    val isPinned: Boolean,
)

data class ChatPhoto(
    val small: File?,
    val big: File?,
    val hasAnimation: Boolean,
    val isPersonal: Boolean
)

data class ChatPermissions(
    val canSendBasicMessages: Boolean,
    val canSendOtherMessages: Boolean,
    val canAddLinkPreviews: Boolean,

    val canSendAudios: Boolean,
    val canSendDocuments: Boolean,
    val canSendPhotos: Boolean,
    val canSendVideos: Boolean,
    val canSendVideoNotes: Boolean,
    val canSendVoiceNotes: Boolean,

    val canSendPolls: Boolean,
    val canCreateTopics: Boolean,

    val canChangeInfo: Boolean,
    val canInviteUsers: Boolean,
    val canPinMessages: Boolean
)

sealed interface ChatStatus {
    object Empty : ChatStatus
    object Online: ChatStatus
    data class Offline(val wasOnline : Int) : ChatStatus
    object Recently : ChatStatus
    object LastWeek : ChatStatus
    object LastMonth : ChatStatus
}

fun ChatStatus.toStringKey() : String {
    return when(this) {
        is ChatStatus.Empty -> "ALongTimeAgo"
        is ChatStatus.LastMonth -> "WithinAMonth"
        is ChatStatus.LastWeek -> "WithinAWeek"
        is ChatStatus.Offline -> "LastSeenFormatted"
        is ChatStatus.Online -> "Online"
        is ChatStatus.Recently -> "Lately"
    }
}

data class Chat(
    val id: Long,
    val type: ChatType,
    val title: String,
    val photo: ChatPhoto?,

    val accentColorId: Int,
    val backgroundCustomEmojiId: Long,
    val profileAccentColorId: Int,
    val profileBackgroundCustomEmojiId: Long,

    val permissions: ChatPermissions,

    val lastMessage: Message?,

    val positions: List<ChatPosition>,
    val chatLists: List<ChatList>,

    val messageSenderId: MessageSender?,

    val hasProtectedContent: Boolean,

    val unreadCount: Int,
    val unreadMentionCount: Int,
    val unreadReactionCount: Int,

    val isTranslatable: Boolean,
    val isMarkedAsUnread: Boolean,
    val viewAsTopics: Boolean,
    val hasScheduledMessages: Boolean,
    val defaultDisableNotification: Boolean,

    val canBeDeletedOnlyForSelf: Boolean,
    val canBeDeletedForAllUsers: Boolean,

    val lastReadInboxMessageId: Long,
    val lastReadOutboxMessageId: Long,
    val messageAutoDeleteTime: Int,
    val replyMarkupMessageId: Long,

    val clientData: String,

    val status: ChatStatus = ChatStatus.Empty,
)