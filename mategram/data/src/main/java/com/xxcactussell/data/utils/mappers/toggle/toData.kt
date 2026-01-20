package com.xxcactussell.data.utils.mappers.toggle

import com.xxcactussell.data.utils.mappers.chat.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ToggleAllDownloadsArePaused.toData(): TdApi.ToggleAllDownloadsArePaused = TdApi.ToggleAllDownloadsArePaused(
    this.arePaused
)

fun ToggleBotCanManageEmojiStatus.toData(): TdApi.ToggleBotCanManageEmojiStatus = TdApi.ToggleBotCanManageEmojiStatus(
    this.botUserId,
    this.canManageEmojiStatus
)

fun ToggleBotIsAddedToAttachmentMenu.toData(): TdApi.ToggleBotIsAddedToAttachmentMenu = TdApi.ToggleBotIsAddedToAttachmentMenu(
    this.botUserId,
    this.isAdded,
    this.allowWriteAccess
)

fun ToggleBotUsernameIsActive.toData(): TdApi.ToggleBotUsernameIsActive = TdApi.ToggleBotUsernameIsActive(
    this.botUserId,
    this.username,
    this.isActive
)

fun ToggleBusinessConnectedBotChatIsPaused.toData(): TdApi.ToggleBusinessConnectedBotChatIsPaused = TdApi.ToggleBusinessConnectedBotChatIsPaused(
    this.chatId,
    this.isPaused
)

fun ToggleChatDefaultDisableNotification.toData(): TdApi.ToggleChatDefaultDisableNotification = TdApi.ToggleChatDefaultDisableNotification(
    this.chatId,
    this.defaultDisableNotification
)

fun ToggleChatFolderTags.toData(): TdApi.ToggleChatFolderTags = TdApi.ToggleChatFolderTags(
    this.areTagsEnabled
)

fun ToggleChatGiftNotifications.toData(): TdApi.ToggleChatGiftNotifications = TdApi.ToggleChatGiftNotifications(
    this.chatId,
    this.areEnabled
)

fun ToggleChatHasProtectedContent.toData(): TdApi.ToggleChatHasProtectedContent = TdApi.ToggleChatHasProtectedContent(
    this.chatId,
    this.hasProtectedContent
)

fun ToggleChatIsPinned.toData(): TdApi.ToggleChatIsPinned = TdApi.ToggleChatIsPinned(
    this.chatList.toData(),
    this.chatId,
    this.isPinned
)

fun ToggleChatIsTranslatable.toData(): TdApi.ToggleChatIsTranslatable = TdApi.ToggleChatIsTranslatable(
    this.chatId,
    this.isTranslatable
)

fun ToggleChatViewAsTopics.toData(): TdApi.ToggleChatViewAsTopics = TdApi.ToggleChatViewAsTopics(
    this.chatId,
    this.viewAsTopics
)

fun ToggleDirectMessagesChatTopicCanSendUnpaidMessages.toData(): TdApi.ToggleDirectMessagesChatTopicCanSendUnpaidMessages = TdApi.ToggleDirectMessagesChatTopicCanSendUnpaidMessages(
    this.chatId,
    this.topicId,
    this.canSendUnpaidMessages,
    this.refundPayments
)

fun ToggleDownloadIsPaused.toData(): TdApi.ToggleDownloadIsPaused = TdApi.ToggleDownloadIsPaused(
    this.fileId,
    this.isPaused
)

fun ToggleForumTopicIsClosed.toData(): TdApi.ToggleForumTopicIsClosed = TdApi.ToggleForumTopicIsClosed(
    this.chatId,
    this.messageThreadId,
    this.isClosed
)

fun ToggleForumTopicIsPinned.toData(): TdApi.ToggleForumTopicIsPinned = TdApi.ToggleForumTopicIsPinned(
    this.chatId,
    this.messageThreadId,
    this.isPinned
)

fun ToggleGiftIsSaved.toData(): TdApi.ToggleGiftIsSaved = TdApi.ToggleGiftIsSaved(
    this.receivedGiftId,
    this.isSaved
)

fun ToggleGroupCallIsMyVideoEnabled.toData(): TdApi.ToggleGroupCallIsMyVideoEnabled = TdApi.ToggleGroupCallIsMyVideoEnabled(
    this.groupCallId,
    this.isMyVideoEnabled
)

fun ToggleGroupCallIsMyVideoPaused.toData(): TdApi.ToggleGroupCallIsMyVideoPaused = TdApi.ToggleGroupCallIsMyVideoPaused(
    this.groupCallId,
    this.isMyVideoPaused
)

fun ToggleGroupCallScreenSharingIsPaused.toData(): TdApi.ToggleGroupCallScreenSharingIsPaused = TdApi.ToggleGroupCallScreenSharingIsPaused(
    this.groupCallId,
    this.isPaused
)

fun ToggleHasSponsoredMessagesEnabled.toData(): TdApi.ToggleHasSponsoredMessagesEnabled = TdApi.ToggleHasSponsoredMessagesEnabled(
    this.hasSponsoredMessagesEnabled
)

fun ToggleSavedMessagesTopicIsPinned.toData(): TdApi.ToggleSavedMessagesTopicIsPinned = TdApi.ToggleSavedMessagesTopicIsPinned(
    this.savedMessagesTopicId,
    this.isPinned
)

fun ToggleSessionCanAcceptCalls.toData(): TdApi.ToggleSessionCanAcceptCalls = TdApi.ToggleSessionCanAcceptCalls(
    this.sessionId,
    this.canAcceptCalls
)

fun ToggleSessionCanAcceptSecretChats.toData(): TdApi.ToggleSessionCanAcceptSecretChats = TdApi.ToggleSessionCanAcceptSecretChats(
    this.sessionId,
    this.canAcceptSecretChats
)

fun ToggleStoryIsPostedToChatPage.toData(): TdApi.ToggleStoryIsPostedToChatPage = TdApi.ToggleStoryIsPostedToChatPage(
    this.storyPosterChatId,
    this.storyId,
    this.isPostedToChatPage
)

fun ToggleSupergroupCanHaveSponsoredMessages.toData(): TdApi.ToggleSupergroupCanHaveSponsoredMessages = TdApi.ToggleSupergroupCanHaveSponsoredMessages(
    this.supergroupId,
    this.canHaveSponsoredMessages
)

fun ToggleSupergroupHasAggressiveAntiSpamEnabled.toData(): TdApi.ToggleSupergroupHasAggressiveAntiSpamEnabled = TdApi.ToggleSupergroupHasAggressiveAntiSpamEnabled(
    this.supergroupId,
    this.hasAggressiveAntiSpamEnabled
)

fun ToggleSupergroupHasAutomaticTranslation.toData(): TdApi.ToggleSupergroupHasAutomaticTranslation = TdApi.ToggleSupergroupHasAutomaticTranslation(
    this.supergroupId,
    this.hasAutomaticTranslation
)

fun ToggleSupergroupHasHiddenMembers.toData(): TdApi.ToggleSupergroupHasHiddenMembers = TdApi.ToggleSupergroupHasHiddenMembers(
    this.supergroupId,
    this.hasHiddenMembers
)

fun ToggleSupergroupIsAllHistoryAvailable.toData(): TdApi.ToggleSupergroupIsAllHistoryAvailable = TdApi.ToggleSupergroupIsAllHistoryAvailable(
    this.supergroupId,
    this.isAllHistoryAvailable
)

fun ToggleSupergroupIsBroadcastGroup.toData(): TdApi.ToggleSupergroupIsBroadcastGroup = TdApi.ToggleSupergroupIsBroadcastGroup(
    this.supergroupId
)

fun ToggleSupergroupIsForum.toData(): TdApi.ToggleSupergroupIsForum = TdApi.ToggleSupergroupIsForum(
    this.supergroupId,
    this.isForum,
    this.hasForumTabs
)

fun ToggleSupergroupJoinByRequest.toData(): TdApi.ToggleSupergroupJoinByRequest = TdApi.ToggleSupergroupJoinByRequest(
    this.supergroupId,
    this.joinByRequest
)

fun ToggleSupergroupJoinToSendMessages.toData(): TdApi.ToggleSupergroupJoinToSendMessages = TdApi.ToggleSupergroupJoinToSendMessages(
    this.supergroupId,
    this.joinToSendMessages
)

fun ToggleSupergroupSignMessages.toData(): TdApi.ToggleSupergroupSignMessages = TdApi.ToggleSupergroupSignMessages(
    this.supergroupId,
    this.signMessages,
    this.showMessageSender
)

fun ToggleSupergroupUsernameIsActive.toData(): TdApi.ToggleSupergroupUsernameIsActive = TdApi.ToggleSupergroupUsernameIsActive(
    this.supergroupId,
    this.username,
    this.isActive
)

fun ToggleUsernameIsActive.toData(): TdApi.ToggleUsernameIsActive = TdApi.ToggleUsernameIsActive(
    this.username,
    this.isActive
)

fun ToggleVideoChatEnabledStartNotification.toData(): TdApi.ToggleVideoChatEnabledStartNotification = TdApi.ToggleVideoChatEnabledStartNotification(
    this.groupCallId,
    this.enabledStartNotification
)

fun ToggleVideoChatMuteNewParticipants.toData(): TdApi.ToggleVideoChatMuteNewParticipants = TdApi.ToggleVideoChatMuteNewParticipants(
    this.groupCallId,
    this.muteNewParticipants
)

