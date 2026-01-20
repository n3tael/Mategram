package com.xxcactussell.data.utils.mappers.toggle

import com.xxcactussell.data.utils.mappers.chat.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ToggleAllDownloadsArePaused.toDomain(): ToggleAllDownloadsArePaused = ToggleAllDownloadsArePaused(
    arePaused = this.arePaused
)

fun TdApi.ToggleBotCanManageEmojiStatus.toDomain(): ToggleBotCanManageEmojiStatus = ToggleBotCanManageEmojiStatus(
    botUserId = this.botUserId,
    canManageEmojiStatus = this.canManageEmojiStatus
)

fun TdApi.ToggleBotIsAddedToAttachmentMenu.toDomain(): ToggleBotIsAddedToAttachmentMenu = ToggleBotIsAddedToAttachmentMenu(
    botUserId = this.botUserId,
    isAdded = this.isAdded,
    allowWriteAccess = this.allowWriteAccess
)

fun TdApi.ToggleBotUsernameIsActive.toDomain(): ToggleBotUsernameIsActive = ToggleBotUsernameIsActive(
    botUserId = this.botUserId,
    username = this.username,
    isActive = this.isActive
)

fun TdApi.ToggleBusinessConnectedBotChatIsPaused.toDomain(): ToggleBusinessConnectedBotChatIsPaused = ToggleBusinessConnectedBotChatIsPaused(
    chatId = this.chatId,
    isPaused = this.isPaused
)

fun TdApi.ToggleChatDefaultDisableNotification.toDomain(): ToggleChatDefaultDisableNotification = ToggleChatDefaultDisableNotification(
    chatId = this.chatId,
    defaultDisableNotification = this.defaultDisableNotification
)

fun TdApi.ToggleChatFolderTags.toDomain(): ToggleChatFolderTags = ToggleChatFolderTags(
    areTagsEnabled = this.areTagsEnabled
)

fun TdApi.ToggleChatGiftNotifications.toDomain(): ToggleChatGiftNotifications = ToggleChatGiftNotifications(
    chatId = this.chatId,
    areEnabled = this.areEnabled
)

fun TdApi.ToggleChatHasProtectedContent.toDomain(): ToggleChatHasProtectedContent = ToggleChatHasProtectedContent(
    chatId = this.chatId,
    hasProtectedContent = this.hasProtectedContent
)

fun TdApi.ToggleChatIsPinned.toDomain(): ToggleChatIsPinned = ToggleChatIsPinned(
    chatList = this.chatList.toDomain(),
    chatId = this.chatId,
    isPinned = this.isPinned
)

fun TdApi.ToggleChatIsTranslatable.toDomain(): ToggleChatIsTranslatable = ToggleChatIsTranslatable(
    chatId = this.chatId,
    isTranslatable = this.isTranslatable
)

fun TdApi.ToggleChatViewAsTopics.toDomain(): ToggleChatViewAsTopics = ToggleChatViewAsTopics(
    chatId = this.chatId,
    viewAsTopics = this.viewAsTopics
)

fun TdApi.ToggleDirectMessagesChatTopicCanSendUnpaidMessages.toDomain(): ToggleDirectMessagesChatTopicCanSendUnpaidMessages = ToggleDirectMessagesChatTopicCanSendUnpaidMessages(
    chatId = this.chatId,
    topicId = this.topicId,
    canSendUnpaidMessages = this.canSendUnpaidMessages,
    refundPayments = this.refundPayments
)

fun TdApi.ToggleDownloadIsPaused.toDomain(): ToggleDownloadIsPaused = ToggleDownloadIsPaused(
    fileId = this.fileId,
    isPaused = this.isPaused
)

fun TdApi.ToggleForumTopicIsClosed.toDomain(): ToggleForumTopicIsClosed = ToggleForumTopicIsClosed(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    isClosed = this.isClosed
)

fun TdApi.ToggleForumTopicIsPinned.toDomain(): ToggleForumTopicIsPinned = ToggleForumTopicIsPinned(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    isPinned = this.isPinned
)

fun TdApi.ToggleGiftIsSaved.toDomain(): ToggleGiftIsSaved = ToggleGiftIsSaved(
    receivedGiftId = this.receivedGiftId,
    isSaved = this.isSaved
)

fun TdApi.ToggleGroupCallIsMyVideoEnabled.toDomain(): ToggleGroupCallIsMyVideoEnabled = ToggleGroupCallIsMyVideoEnabled(
    groupCallId = this.groupCallId,
    isMyVideoEnabled = this.isMyVideoEnabled
)

fun TdApi.ToggleGroupCallIsMyVideoPaused.toDomain(): ToggleGroupCallIsMyVideoPaused = ToggleGroupCallIsMyVideoPaused(
    groupCallId = this.groupCallId,
    isMyVideoPaused = this.isMyVideoPaused
)

fun TdApi.ToggleGroupCallScreenSharingIsPaused.toDomain(): ToggleGroupCallScreenSharingIsPaused = ToggleGroupCallScreenSharingIsPaused(
    groupCallId = this.groupCallId,
    isPaused = this.isPaused
)

fun TdApi.ToggleHasSponsoredMessagesEnabled.toDomain(): ToggleHasSponsoredMessagesEnabled = ToggleHasSponsoredMessagesEnabled(
    hasSponsoredMessagesEnabled = this.hasSponsoredMessagesEnabled
)

fun TdApi.ToggleSavedMessagesTopicIsPinned.toDomain(): ToggleSavedMessagesTopicIsPinned = ToggleSavedMessagesTopicIsPinned(
    savedMessagesTopicId = this.savedMessagesTopicId,
    isPinned = this.isPinned
)

fun TdApi.ToggleSessionCanAcceptCalls.toDomain(): ToggleSessionCanAcceptCalls = ToggleSessionCanAcceptCalls(
    sessionId = this.sessionId,
    canAcceptCalls = this.canAcceptCalls
)

fun TdApi.ToggleSessionCanAcceptSecretChats.toDomain(): ToggleSessionCanAcceptSecretChats = ToggleSessionCanAcceptSecretChats(
    sessionId = this.sessionId,
    canAcceptSecretChats = this.canAcceptSecretChats
)

fun TdApi.ToggleStoryIsPostedToChatPage.toDomain(): ToggleStoryIsPostedToChatPage = ToggleStoryIsPostedToChatPage(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId,
    isPostedToChatPage = this.isPostedToChatPage
)

fun TdApi.ToggleSupergroupCanHaveSponsoredMessages.toDomain(): ToggleSupergroupCanHaveSponsoredMessages = ToggleSupergroupCanHaveSponsoredMessages(
    supergroupId = this.supergroupId,
    canHaveSponsoredMessages = this.canHaveSponsoredMessages
)

fun TdApi.ToggleSupergroupHasAggressiveAntiSpamEnabled.toDomain(): ToggleSupergroupHasAggressiveAntiSpamEnabled = ToggleSupergroupHasAggressiveAntiSpamEnabled(
    supergroupId = this.supergroupId,
    hasAggressiveAntiSpamEnabled = this.hasAggressiveAntiSpamEnabled
)

fun TdApi.ToggleSupergroupHasAutomaticTranslation.toDomain(): ToggleSupergroupHasAutomaticTranslation = ToggleSupergroupHasAutomaticTranslation(
    supergroupId = this.supergroupId,
    hasAutomaticTranslation = this.hasAutomaticTranslation
)

fun TdApi.ToggleSupergroupHasHiddenMembers.toDomain(): ToggleSupergroupHasHiddenMembers = ToggleSupergroupHasHiddenMembers(
    supergroupId = this.supergroupId,
    hasHiddenMembers = this.hasHiddenMembers
)

fun TdApi.ToggleSupergroupIsAllHistoryAvailable.toDomain(): ToggleSupergroupIsAllHistoryAvailable = ToggleSupergroupIsAllHistoryAvailable(
    supergroupId = this.supergroupId,
    isAllHistoryAvailable = this.isAllHistoryAvailable
)

fun TdApi.ToggleSupergroupIsBroadcastGroup.toDomain(): ToggleSupergroupIsBroadcastGroup = ToggleSupergroupIsBroadcastGroup(
    supergroupId = this.supergroupId
)

fun TdApi.ToggleSupergroupIsForum.toDomain(): ToggleSupergroupIsForum = ToggleSupergroupIsForum(
    supergroupId = this.supergroupId,
    isForum = this.isForum,
    hasForumTabs = this.hasForumTabs
)

fun TdApi.ToggleSupergroupJoinByRequest.toDomain(): ToggleSupergroupJoinByRequest = ToggleSupergroupJoinByRequest(
    supergroupId = this.supergroupId,
    joinByRequest = this.joinByRequest
)

fun TdApi.ToggleSupergroupJoinToSendMessages.toDomain(): ToggleSupergroupJoinToSendMessages = ToggleSupergroupJoinToSendMessages(
    supergroupId = this.supergroupId,
    joinToSendMessages = this.joinToSendMessages
)

fun TdApi.ToggleSupergroupSignMessages.toDomain(): ToggleSupergroupSignMessages = ToggleSupergroupSignMessages(
    supergroupId = this.supergroupId,
    signMessages = this.signMessages,
    showMessageSender = this.showMessageSender
)

fun TdApi.ToggleSupergroupUsernameIsActive.toDomain(): ToggleSupergroupUsernameIsActive = ToggleSupergroupUsernameIsActive(
    supergroupId = this.supergroupId,
    username = this.username,
    isActive = this.isActive
)

fun TdApi.ToggleUsernameIsActive.toDomain(): ToggleUsernameIsActive = ToggleUsernameIsActive(
    username = this.username,
    isActive = this.isActive
)

fun TdApi.ToggleVideoChatEnabledStartNotification.toDomain(): ToggleVideoChatEnabledStartNotification = ToggleVideoChatEnabledStartNotification(
    groupCallId = this.groupCallId,
    enabledStartNotification = this.enabledStartNotification
)

fun TdApi.ToggleVideoChatMuteNewParticipants.toDomain(): ToggleVideoChatMuteNewParticipants = ToggleVideoChatMuteNewParticipants(
    groupCallId = this.groupCallId,
    muteNewParticipants = this.muteNewParticipants
)

